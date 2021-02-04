package com.example.ISAISA.controller;

import com.example.ISAISA.DTO.IdDto;
import com.example.ISAISA.model.Appointment;
import com.example.ISAISA.model.Dermatologist;
import com.example.ISAISA.DTO.AppointmentDTO;
import com.example.ISAISA.model.AdminPharmacy;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.service.AppointmentService;
import com.example.ISAISA.service.DermatologistService;
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

import java.time.LocalDateTime;

@RestController
@RequestMapping(value="/appointments")
public class AppointmentController {

    private AppointmentService appointmentService;
    private DermatologistService dermatologistService;

    @Autowired
    public void setAppointmentService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping(value="/checkIfAppointmentExists", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<IdDto> checkIfAppointmentExists(@RequestBody IdDto PatientIdDto) throws Exception {

        //Integer id = Integer.parseInt(idPatient);

        Dermatologist user = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Appointment checkIfPatientHasAppointmentNow = appointmentService.ifPatientHasAppointment(PatientIdDto.getId(), user);

        if(checkIfPatientHasAppointmentNow != null){
            Boolean checkPharmacy = appointmentService.checkPharmacy(checkIfPatientHasAppointmentNow, user);
            if(checkPharmacy){
                IdDto appointmentIdDto = new IdDto(checkIfPatientHasAppointmentNow.getId());
                return new ResponseEntity<>(appointmentIdDto, HttpStatus.OK);
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
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<IdDto> penalPatient(@RequestBody IdDto AppointmentIdDto) throws Exception {

        Integer idPatient = appointmentService.penalPatient(AppointmentIdDto.getId());
        IdDto id = new IdDto(idPatient);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @Autowired
    public void setDermatologistService(DermatologistService dermatologistService) {
        this.dermatologistService = dermatologistService;
    }

    @PostMapping(value="/createAvailableAppointment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<Appointment> createAvailableAppointment(@RequestBody AppointmentDTO appointment) throws Exception {

        AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pharmacy pharmacy = user.getPharmacy();
        Integer dermatologist_id =appointment.getDermatologist();
        Dermatologist dermatologist = dermatologistService.findById(dermatologist_id);

        LocalDateTime endofwork = appointment.getBeginofappointment().plusMinutes(appointment.getDuration());

        Appointment appointment1 = new Appointment();
        appointment1.setBeginofappointment(appointment.getBeginofappointment());
        appointment1.setEndofappointment(endofwork);
        appointment1.setDermatologist(dermatologist);
        appointment1.setPrice(appointment.getPrice());
        appointment1.setPharmacy_appointment(pharmacy);

        appointment1 = appointmentService.save(appointment1);

        return new ResponseEntity<>(appointment1, HttpStatus.OK);

    }
}
