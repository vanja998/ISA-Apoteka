package com.example.ISAISA.controller;

import com.example.ISAISA.DTO.*;
import com.example.ISAISA.model.*;
import com.example.ISAISA.service.AppointmentService;
import com.example.ISAISA.service.CounselingService;
import com.example.ISAISA.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value="/counselings")
public class CounselingController {

    private CounselingService counselingService;

    @Autowired
    private EmailSenderService emailSenderService;


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


    @GetMapping(value="/getCounselingsWeek",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<List<CalendarDTO>> getCounselingsWeek() {

        Pharmacist user = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<CalendarDTO> calendarDTOS = counselingService.getCounselingsWeek(user);

        return new ResponseEntity<>(calendarDTOS, HttpStatus.OK);
    }

    @GetMapping(value="/getCounselingsMonth",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<List<CalendarDTO>> getCounselingsMonth() {

        Pharmacist user = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<CalendarDTO> calendarDTOS = counselingService.getCounselingsMonth(user);

        return new ResponseEntity<>(calendarDTOS, HttpStatus.OK);
    }

    @GetMapping(value="/getCounselingsYear",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PHARMACIST')")
    public ResponseEntity<List<CalendarDTO>> getCounselingsYear() {

        Pharmacist user = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<CalendarDTO> calendarDTOS = counselingService.getCounselingsYear(user);

        return new ResponseEntity<>(calendarDTOS, HttpStatus.OK);
    }

    @GetMapping(value="/getCounselingsPharmacy",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<Set<Counseling>> getCounselingsByPharmacy() {

        AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Counseling> counselings = counselingService.getCounselingByPharmacyAfterNow(user.getPharmacy());

        return new ResponseEntity<>(counselings, HttpStatus.OK);
    }

    @PostMapping(value="/changeCounselingPrice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<Counseling> changeCounselingPrice(@RequestBody IdPriceDTO idPriceDTO){

        Counseling counseling = counselingService.changeCounselingPrice(idPriceDTO.getPrice(), idPriceDTO.getId());

        return new ResponseEntity<>(counseling, HttpStatus.OK);
    }
}