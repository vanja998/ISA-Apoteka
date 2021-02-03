package com.example.ISAISA.controller;

import com.example.ISAISA.DTO.AppointmentDTO;
import com.example.ISAISA.DTO.UserChangeDTO;
import com.example.ISAISA.model.AdminPharmacy;
import com.example.ISAISA.model.Appointment;
import com.example.ISAISA.model.Dermatologist;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.service.AdminSystemService;
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

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(value="/appointments")
public class AppointmentController {

    private AppointmentService appointmentService;
    private DermatologistService dermatologistService;

    @Autowired
    public void setAppointmentService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
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
