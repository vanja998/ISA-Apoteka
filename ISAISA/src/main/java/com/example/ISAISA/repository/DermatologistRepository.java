package com.example.ISAISA.repository;

import com.example.ISAISA.model.Dermatologist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DermatologistRepository extends JpaRepository<Dermatologist, Integer> {

    Dermatologist findOneById(Integer id);
}
