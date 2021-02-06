package com.example.ISAISA.repository;

import com.example.ISAISA.model.Medication;
import com.example.ISAISA.model.Pharmacist;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {

    Pharmacy findByAddress(String address);
    Pharmacy findByName(String name);
    Pharmacy findByRating(Float rating);


    Set<Pharmacy> findAllByName(String name);
}
