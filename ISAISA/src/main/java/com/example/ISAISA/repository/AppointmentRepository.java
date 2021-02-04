package com.example.ISAISA.repository;

import com.example.ISAISA.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

       Appointment findOneById(Integer id);
        }

