package com.example.ISAISA.service;

import com.example.ISAISA.model.Appointment;
import com.example.ISAISA.model.Dermatologist;
import com.example.ISAISA.model.Examination;
import com.example.ISAISA.repository.AppointmentRepository;
import com.example.ISAISA.repository.ExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ExaminationService {

    private ExaminationRepository examinationRepository;
    private AppointmentRepository appointmentRepository;

    @Autowired
    public void setExaminationRepository(ExaminationRepository examinationRepository) {
        this.examinationRepository = examinationRepository;
    }

    @Autowired
    public void setAppointmentRepository(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment findAppointment(Dermatologist dermatologist){

        LocalTime now = LocalTime.now();
        LocalDate today = LocalDate.now();

        List<Appointment> appointments = appointmentRepository.findAll();

        for (Appointment i : appointments){
            if(today.isEqual(i.getBeginofappointment().toLocalDate())){
                if(now.isAfter(i.getBeginofappointment().toLocalTime()) && now.isBefore(i.getEndofappointment().toLocalTime())){
                    if(i.getDermatologist().getId() == dermatologist.getId()) {
                        return i;
                    }
                }
            }
        }

        return null;
    }

    public Integer Save(Appointment appointment, Dermatologist user) {
        Examination examination = new Examination();
        examination.setExaminationAppointment(appointment);
        examination = examinationRepository.save(examination);
        return examination.getId();
    }

    /*public Integer createNewExamination(Appointment appointment, Dermatologist dermatologist){

        examinationRepository.

        return null;
    }*/

}
