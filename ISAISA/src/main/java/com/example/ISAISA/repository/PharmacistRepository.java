package com.example.ISAISA.repository;

import com.example.ISAISA.model.Pharmacist;
import com.example.ISAISA.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Integer> {

    Set<Pharmacist> findAllByPharmacy (Pharmacy pharmacy);

    Set<Pharmacist> findAllByPharmacyAndFirstNameIgnoreCaseAndLastNameIgnoreCase (Pharmacy pharmacy, String firstName, String lastName);
}
