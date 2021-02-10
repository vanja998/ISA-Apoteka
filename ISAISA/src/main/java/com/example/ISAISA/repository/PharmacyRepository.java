package com.example.ISAISA.repository;

import com.example.ISAISA.model.Medication;
import com.example.ISAISA.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {

    Pharmacy findByAddress(String address);
    Pharmacy findByName(String name);
    Pharmacy findByRating(Float rating);
    Pharmacy findOneById(Integer id);
    List<Pharmacy> findAll();
    Set<Pharmacy> findAllByName(String name);
    //List<Pharmacy> findAllByMedication(Medication medication);

}
