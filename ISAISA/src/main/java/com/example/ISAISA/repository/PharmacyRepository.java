package com.example.ISAISA.repository;

import com.example.ISAISA.model.Pharmacist;
import com.example.ISAISA.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {

    Pharmacy findByName(String name);
}
