package com.example.ISAISA.repository;

import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {

    Pharmacy findByAddress(String address);
}
