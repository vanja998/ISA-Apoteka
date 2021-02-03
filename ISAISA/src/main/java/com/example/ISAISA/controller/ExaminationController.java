package com.example.ISAISA.controller;

import com.example.ISAISA.DTO.IdDto;
import com.example.ISAISA.DTO.ReportDto;
import com.example.ISAISA.model.Appointment;
import com.example.ISAISA.model.Dermatologist;
import com.example.ISAISA.model.Examination;
import com.example.ISAISA.model.User;
import com.example.ISAISA.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/examinations")
public class ExaminationController {

    private ExaminationService examinationService;

    @Autowired
    public void setExaminationService(ExaminationService examinationService) {
        this.examinationService = examinationService;
    }

    @GetMapping(value="/getAppointmentId",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<IdDto> getExaminationId() {
        Dermatologist user = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Appointment appointment = examinationService.findAppointment(user);

        Integer examinationId = examinationService.Save(appointment, user);

        //Integer idExamination = examinationService.createNewExamination(appointment, user);

        IdDto ex = new IdDto(examinationId);
        return new ResponseEntity<>(ex, HttpStatus.OK);
    }


    @PostMapping(value="/writeReport", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<IdDto> writeReport(@RequestBody ReportDto reportDto) throws Exception {

        examinationService.writeReport(reportDto.getId(), reportDto.getReport());

        IdDto ex = new IdDto(reportDto.getId());
        return new ResponseEntity<>(ex, HttpStatus.OK);
    }


}
