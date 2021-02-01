package com.example.ISAISA.controller;

import com.example.ISAISA.service.AppointmentService;
import com.example.ISAISA.service.CounselingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/counselings")
public class CounselingController {

    private CounselingService counselingService;

    @Autowired
    public void setCounselingService(CounselingService counselingService) {
        this.counselingService = counselingService;
    }
}