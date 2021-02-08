package com.example.ISAISA.controller;

import com.example.ISAISA.DTO.BooleanDto;
import com.example.ISAISA.DTO.CreateAppointmentDto;
import com.example.ISAISA.DTO.IdDto;
import com.example.ISAISA.model.Appointment;
import com.example.ISAISA.model.Counseling;
import com.example.ISAISA.model.Dermatologist;
import com.example.ISAISA.model.Pharmacist;
import com.example.ISAISA.service.AppointmentService;
import com.example.ISAISA.service.CounselingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping(value="/checkIfCounselingExists", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<IdDto> checkIfCounselingExists(@RequestBody IdDto PatientIdDto) throws Exception {

        //Integer id = Integer.parseInt(idPatient);

        Pharmacist user = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Counseling checkIfPatientHasCounselingNow = counselingService.ifPatientHasCounseling(PatientIdDto.getId(), user);

        if(checkIfPatientHasCounselingNow != null){
            Boolean checkPharmacy = counselingService.checkPharmacy(user);
            if(checkPharmacy){
                IdDto coundelingIdDto = new IdDto(checkIfPatientHasCounselingNow.getId());
                return new ResponseEntity<>(coundelingIdDto, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        /*AppointmentIdDto idDto = new AppointmentIdDto(5);
        return new ResponseEntity<>(idDto, HttpStatus.OK);*/
    }

    @PostMapping(value="/penalPatient", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<IdDto> penalPatient(@RequestBody IdDto counselingIdDto) throws Exception {

        Integer idPatient = counselingService.penalPatient(counselingIdDto.getId());
        IdDto id = new IdDto(idPatient);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping(value="/createCounselingPharmacist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<BooleanDto> createCounselingPharmacist(@RequestBody CreateAppointmentDto createCounselingDto) throws Exception {

        Pharmacist user = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Boolean counseling = counselingService.createCounselingPharmacist(user, createCounselingDto.getId(),  createCounselingDto.getStartOfAppointment(), createCounselingDto.getEndOfAppointment(), createCounselingDto.getPrice());

        BooleanDto booleanDto = new BooleanDto(counseling);

        return new ResponseEntity<>(booleanDto, HttpStatus.OK);
    }

}