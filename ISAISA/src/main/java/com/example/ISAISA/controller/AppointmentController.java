package com.example.ISAISA.controller;

import com.example.ISAISA.DTO.IdDto;
import com.example.ISAISA.DTO.PharmacyDTO;
import com.example.ISAISA.model.Appointment;
import com.example.ISAISA.model.Dermatologist;
import com.example.ISAISA.model.Patient;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.service.AdminSystemService;
import com.example.ISAISA.service.AppointmentService;
import com.example.ISAISA.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value="/appointments")
public class AppointmentController {

    private AppointmentService appointmentService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    public void setAppointmentService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping(value="/unreservedappointment",produces = MediaType.APPLICATION_JSON_VALUE)// value nije naveden, jer koristimo bazni url
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<Appointment>> getUnreservedAppointments() {
        List<Appointment> appointmentList = this.appointmentService.findAll();

        // Kreiramo listu DTO objekata
        List<Appointment> pharmaciesDTOS = new ArrayList<>();

        for (Appointment appointment : appointmentList) {
            Appointment pharmacyDTO = new Appointment(appointment.getId(),appointment.getPatient(),appointment.getDermatologist(),appointment.getBeginofappointment(),appointment.getEndofappointment(),appointment.getPrice());
            if(pharmacyDTO.getPatient()==null) {
                pharmaciesDTOS.add(pharmacyDTO);
            }
        }
        return new ResponseEntity<>(pharmaciesDTOS, HttpStatus.OK);
    }

    @PostMapping(value="/reserveappointment", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<Appointment> Reserveappointment(@RequestBody IdDto idDto) {
        Patient user = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Appointment appointment=appointmentService.findById(idDto.getId());
        appointment.setPatient(user);
        appointment=appointmentService.save(appointment);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Rezervacija termina");
        mailMessage.setFrom("isaverifikacija@gmail.com");
        mailMessage.setText("uspesno ste zakazali pregled");

        emailSenderService.sendEmail(mailMessage);
        return new ResponseEntity(appointment, HttpStatus.OK);
    }


}
