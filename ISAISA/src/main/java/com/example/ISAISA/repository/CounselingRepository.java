package com.example.ISAISA.repository;

import com.example.ISAISA.model.Counseling;
import com.example.ISAISA.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import com.example.ISAISA.model.Pharmacist;
import java.util.Set;

public interface CounselingRepository extends JpaRepository<Counseling, Integer> {
    List<Counseling> findAllByPatient(Patient patient);

    Set<Counseling> findByPharmacist(Pharmacist pharmacist);

    Counseling findOneById(Integer id);
    List<Counseling> findAllByPharmacist(Pharmacist pharmacist);
    Set<Counseling> findAllByPatientOrderByPrice(Patient patient);
}
