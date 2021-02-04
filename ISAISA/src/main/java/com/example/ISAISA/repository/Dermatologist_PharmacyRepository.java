package com.example.ISAISA.repository;

import com.example.ISAISA.model.Dermatologist;
import com.example.ISAISA.model.Dermatologist_Pharmacyy;
import com.example.ISAISA.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface Dermatologist_PharmacyRepository  extends JpaRepository<Dermatologist_Pharmacyy, Integer> {
    Set<Dermatologist_Pharmacyy> findAllByPharmacy(Pharmacy pharmacy);

    Dermatologist_Pharmacyy findByDermatologistAndPharmacy(Dermatologist dermatologist, Pharmacy pharmacy);
}
