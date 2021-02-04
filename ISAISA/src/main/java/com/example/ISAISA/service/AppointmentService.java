package com.example.ISAISA.service;

import com.example.ISAISA.model.Appointment;
import com.example.ISAISA.model.Complaint;
import com.example.ISAISA.repository.AdminSystemRepository;
import com.example.ISAISA.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }
    public Appointment findById(Integer id){return appointmentRepository.findOneById(id);}
    public Appointment save(Appointment appointment){return appointmentRepository.save(appointment);}
    @Autowired
    public void setAppointmentRepository(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
}
