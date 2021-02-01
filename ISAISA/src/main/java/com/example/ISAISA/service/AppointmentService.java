package com.example.ISAISA.service;

import com.example.ISAISA.repository.AdminSystemRepository;
import com.example.ISAISA.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;

    @Autowired
    public void setAppointmentRepository(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
}
