package com.example.ISAISA.repository;

import com.example.ISAISA.model.Appointment;
import com.example.ISAISA.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ISAISA.model.Dermatologist;

import java.util.Set;


public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

        Set<Appointment> findAllByDermatologist(Dermatologist dermatologist);
        Appointment findOneById(Integer id);
        Set<Appointment> findAllByPatient(Patient patient);
}
