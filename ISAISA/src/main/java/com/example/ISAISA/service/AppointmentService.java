package com.example.ISAISA.service;

import com.example.ISAISA.model.Appointment;
import com.example.ISAISA.model.Dermatologist;
import com.example.ISAISA.model.Dermatologist_Pharmacyy;
import com.example.ISAISA.model.Patient;
import com.example.ISAISA.repository.AppointmentRepository;
import com.example.ISAISA.repository.Dermatologist_PharmacyyRepository;
import com.example.ISAISA.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;


@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private Dermatologist_PharmacyyRepository dermafarmaRepository;
    private PatientRepository patientRepository;

    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }
    public Appointment findById(Integer id){return appointmentRepository.findOneById(id);}
    public Appointment save(Appointment appointment){return appointmentRepository.save(appointment);}

    @Autowired
    public void setAppointmentRepository(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Autowired
    public void setDermafarmaRepository(Dermatologist_PharmacyyRepository dermafarmaRepository) {
        this.dermafarmaRepository = dermafarmaRepository;
    }

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Appointment ifPatientHasAppointment(Integer idPatient, Dermatologist dermatologist){

        LocalTime now = LocalTime.now();
        LocalDate today = LocalDate.now();

        List<Appointment> appointments = appointmentRepository.findAll();


        for (Appointment i : appointments){
            if(i.getPatient() != null) {
                if (i.getPatient().getId().equals(idPatient)) {
                    if (today.isEqual(i.getBeginofappointment().toLocalDate())) {
                        if (now.isAfter(i.getBeginofappointment().toLocalTime()) && now.isBefore(i.getEndofappointment().toLocalTime())) {
                            if (i.getDermatologist().getId() == dermatologist.getId()) {
                                return i;
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    public Boolean checkPharmacy(Appointment appointment, Dermatologist dermatologist){

        LocalTime now = LocalTime.now();

        List<Dermatologist_Pharmacyy> dermaFarma = dermafarmaRepository.findAll();
        for (Dermatologist_Pharmacyy i : dermaFarma){
            if(i.getDermatologist().getId() == dermatologist.getId()){
                if(i.getPharmacy().getId() == appointment.getPharmacy_appointment().getId()){
                    if(now.isAfter(i.getBeginofwork()) && now.isBefore(i.getEndofwork())){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public Integer penalPatient(Integer appointmentId){

        Appointment appointment = appointmentRepository.getOne(appointmentId);
        Patient patient = appointment.getPatient();
        patient.setPenalty(patient.getPenalty()+1);
        patient = patientRepository.save(patient);
        return patient.getId();

    }

    public Appointment saveAvailable(Appointment appointment) throws Exception {
        Dermatologist dermatologist = appointment.getDermatologist();
        Dermatologist_Pharmacyy dermatologist_pharmacy = dermafarmaRepository.findByDermatologistAndPharmacy(dermatologist, appointment.getPharmacy_appointment());
        LocalTime dermatologistBeginOfWork = dermatologist_pharmacy.getBeginofwork();
        LocalTime dermatologistEndOfWork = dermatologist_pharmacy.getEndofwork();

        //Ako pokusa da zakaze za proslo vreme
        if (appointment.getBeginofappointment().isBefore(LocalDateTime.now())) {
            throw new Exception("Nije moguce zakazati ovaj termin!");
        }

        //Ako dermatolog vec ima zakazan termin tada
        Set<Appointment> existingDermAppointments = appointmentRepository.findAllByDermatologist(appointment.getDermatologist());
        for (Appointment a : existingDermAppointments) {
            if((appointment.getBeginofappointment().isAfter(a.getBeginofappointment()) && appointment.getBeginofappointment().isBefore(a.getEndofappointment())) ||
                    (appointment.getEndofappointment().isAfter(a.getBeginofappointment()) && appointment.getEndofappointment().isBefore(a.getEndofappointment())))
            {
                throw new Exception("Postoji zakazan termin u ovo vreme!");
            }
        }

        //Ako dermatolog ne radi tada
        if (appointment.getBeginofappointment().toLocalTime().isBefore(dermatologistBeginOfWork)
        || appointment.getEndofappointment().toLocalTime().isAfter(dermatologistEndOfWork))
        {
            throw new Exception("Termin nije tokom radnog vremena dermatologa!");
        }

        appointmentRepository.save(appointment);

        return appointment;
    }
}
