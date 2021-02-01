package com.example.ISAISA.service;

import com.example.ISAISA.repository.AppointmentRepository;
import com.example.ISAISA.repository.CounselingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounselingService {


    private CounselingRepository counselingRepository;

    @Autowired
    public void setCounselingRepository(CounselingRepository counselingRepository) {
        this.counselingRepository = counselingRepository;
    }
}
