package com.example.ISAISA.repository;

import com.example.ISAISA.model.Dermatologist_Pharmacyy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Dermatologist_PharmacyyRepository extends JpaRepository<Dermatologist_Pharmacyy, Integer> {

    //List<Dermatologist_Pharmacyy> findAllBy();
}
