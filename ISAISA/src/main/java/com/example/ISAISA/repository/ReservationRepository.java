package com.example.ISAISA.repository;

import com.example.ISAISA.model.Promotion;
import com.example.ISAISA.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

}
