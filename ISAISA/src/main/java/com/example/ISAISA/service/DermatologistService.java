package com.example.ISAISA.service;

import com.example.ISAISA.repository.DermatologistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DermatologistService {

    private DermatologistRepository dermatologistRepository;

    @Autowired
    public void setDermatologistRepository(DermatologistRepository dermatologistRepository) {
        this.dermatologistRepository = dermatologistRepository;
    }
}
