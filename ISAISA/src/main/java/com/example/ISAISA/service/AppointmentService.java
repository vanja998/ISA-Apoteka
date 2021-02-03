package com.example.ISAISA.service;

import com.example.ISAISA.model.Appointment;
import com.example.ISAISA.model.Dermatologist;
import com.example.ISAISA.model.Dermatologist_Pharmacyy;
import com.example.ISAISA.repository.AdminSystemRepository;
import com.example.ISAISA.repository.AppointmentRepository;
import com.example.ISAISA.repository.Dermatologist_PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Set;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private Dermatologist_PharmacyRepository dermatologist_pharmacyRepository;

    @Autowired
    public void setAppointmentRepository(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Autowired
    public void setDermatologist_pharmacyRepository(Dermatologist_PharmacyRepository dermatologist_pharmacyRepository) {
        this.dermatologist_pharmacyRepository = dermatologist_pharmacyRepository;
    }

    public Appointment save(Appointment appointment) throws Exception {
        Dermatologist dermatologist = appointment.getDermatologist();
        Dermatologist_Pharmacyy dermatologist_pharmacy = dermatologist_pharmacyRepository.findByDermatologistAndPharmacy(dermatologist, appointment.getPharmacy_appointment());
        LocalTime dermatologistBeginOfWork = dermatologist_pharmacy.getBeginofwork();
        LocalTime dermatologistEndOfWork = dermatologist_pharmacy.getEndofwork();

        Set<Appointment> existingAppointments = appointmentRepository.findAllByDermatologist(appointment.getDermatologist());
        for (Appointment a : existingAppointments) {
            if((appointment.getBeginofappointment().isAfter(a.getBeginofappointment()) && appointment.getBeginofappointment().isBefore(a.getEndofappointment())) ||
                    (appointment.getEndofappointment().isAfter(a.getBeginofappointment()) && appointment.getEndofappointment().isBefore(a.getEndofappointment())))
            {
                throw new Exception("Postoji zakazan termin u ovo vreme!");
            }
        }
        if (appointment.getBeginofappointment().toLocalTime().isBefore(dermatologistBeginOfWork)
        || appointment.getEndofappointment().toLocalTime().isAfter(dermatologistEndOfWork))
        {
            throw new Exception("Termin nije tokom radnog vremena dermatologa!");
        }

        appointmentRepository.save(appointment);

        return appointment;
    }
}
