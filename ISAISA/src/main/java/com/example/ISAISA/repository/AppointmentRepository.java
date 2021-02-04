package com.example.ISAISA.repository;

import com.example.ISAISA.model.Appointment;
import com.example.ISAISA.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

       //List<Appointment> findAll();

}

