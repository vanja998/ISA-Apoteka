package com.example.ISAISA.repository;

import com.example.ISAISA.model.Dermatologist;
import com.example.ISAISA.model.Dermatologist_Pharmacyy;
import com.example.ISAISA.model.Pharmacist;
import com.example.ISAISA.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface DermatologistRepository extends JpaRepository<Dermatologist, Integer> {

    Dermatologist findOneById(Integer id);

    //Set<Dermatologist> findAllByDermatologist_pharmacies (Dermatologist_Pharmacyy dermatologist_pharmacyy);

    @Query("select dermatologist from Dermatologist dermatologist where dermatologist.dermatologist_pharmacies = ?1 and dermatologist.firstName = ?2 and dermatologist.lastName = ?3")
    Set<Dermatologist> findAllByDermatologist_pharmacyysAndFirstNameIgnoreCaseAndLastNameIgnoreCase (Dermatologist_Pharmacyy dermatologist_pharmacyy, String firstName, String lastName);

    Set<Dermatologist> findAllByFirstNameIgnoreCaseAndLastNameIgnoreCase (String firstName, String lastName);

    //Set<Dermatologist> findAllByRatingBetweenAndDermatologist_pharmacies(Float ratingOver, Float ratingUnder, Dermatologist_Pharmacyy dermatologist_pharmacyy);

    Set<Dermatologist> findAllByRatingBetween(Float ratingOver, Float ratingUnder);
}
