package com.example.ISAISA.repository;

import com.example.ISAISA.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintsRepository extends JpaRepository<Complaint, Integer> {
}
