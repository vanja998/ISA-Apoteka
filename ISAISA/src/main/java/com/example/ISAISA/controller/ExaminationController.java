package com.example.ISAISA.controller;

import com.example.ISAISA.DTO.*;
import com.example.ISAISA.model.Appointment;
import com.example.ISAISA.model.Dermatologist;
import com.example.ISAISA.model.Medication;
import com.example.ISAISA.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @PostMapping(value="/getMedicationsForPrescription",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<List<MedicationsForPrescriptionDto>> getMedicationsForPrescription(@RequestBody IdDto idDto) {

        List<String> medicationsForPrescription = examinationService.getMedicationsForPrescription(idDto.getId());

        List<MedicationsForPrescriptionDto> listMedications = new ArrayList<>();

        for(String i : medicationsForPrescription){
            MedicationsForPrescriptionDto medicationsForPrescriptionDto = new MedicationsForPrescriptionDto(i);
            listMedications.add(medicationsForPrescriptionDto);
        }

        return new ResponseEntity<>(listMedications, HttpStatus.OK);
    }


    @PostMapping(value="/checkIfMedicationIsAvailable",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<BooleanDto> checkIfMedicationIsAvailable(@RequestBody MedicationExaminationDto medicationExaminationDto) {

        Boolean isMedicationAvailable = examinationService.isMedicationAvailable(medicationExaminationDto.getName(), medicationExaminationDto.getId());

        BooleanDto booleanDto = new BooleanDto(isMedicationAvailable);
        return new ResponseEntity<>(booleanDto, HttpStatus.OK);

    }

    @PostMapping(value="/getAlternativeMedications",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<List<MedicationsForPrescriptionDto>> getAlternativeMedications(@RequestBody MedicationExaminationDto medicationExaminationDto) {

        List<String> alternativeMedications = examinationService.getAlternativeMedications(medicationExaminationDto.getId(), medicationExaminationDto.getName());

        List<MedicationsForPrescriptionDto> listMedications = new ArrayList<>();

        for(String i : alternativeMedications){
            MedicationsForPrescriptionDto medicationsForPrescriptionDto = new MedicationsForPrescriptionDto(i);
            listMedications.add(medicationsForPrescriptionDto);
        }

        return new ResponseEntity<>(listMedications, HttpStatus.OK);
    }


    @PostMapping(value="/savePrescription", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<BooleanDto> savePrescription(@RequestBody SavePrescriptionDto savePrescriptionDto) throws Exception {

        Boolean isDone = examinationService.savePrescription(savePrescriptionDto.getId(), savePrescriptionDto.getName(), savePrescriptionDto.getDuration());

        BooleanDto booleanDto = new BooleanDto(isDone);
        return new ResponseEntity<>(booleanDto, HttpStatus.OK);
    }


}
