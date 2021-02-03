package com.example.ISAISA.repository;

import com.example.ISAISA.model.Appointment;
import com.example.ISAISA.model.Dermatologist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
        Set<Appointment> findAllByDermatologist(Dermatologist dermatologist);
}

