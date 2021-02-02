package com.example.ISAISA.controller;

import com.example.ISAISA.DTO.AppointmentIdDto;
import com.example.ISAISA.DTO.UserChangeDTO;
import com.example.ISAISA.model.AdminPharmacy;
import com.example.ISAISA.model.Appointment;
import com.example.ISAISA.model.Dermatologist;
import com.example.ISAISA.service.AdminSystemService;
import com.example.ISAISA.service.AppointmentService;
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
@RequestMapping(value="/appointments")
public class AppointmentController {

    private AppointmentService appointmentService;

    @Autowired
    public void setAppointmentService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }


    @PostMapping(value="/checkIfAppointmentExists", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DERMATOLOGIST')")
    public ResponseEntity<AppointmentIdDto> checkIfAppointmentExists(@RequestBody AppointmentIdDto appointmentIdDto) throws Exception {

        //Integer id = Integer.parseInt(idPatient);

        Dermatologist user = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Appointment checkIfPatientHasAppointmentNow = appointmentService.ifPatientHasAppointment(appointmentIdDto.getId(), user);

        if(checkIfPatientHasAppointmentNow != null){
            Boolean checkPharmacy = appointmentService.checkPharmacy(checkIfPatientHasAppointmentNow, user);
            if(checkPharmacy){
                AppointmentIdDto idDto = new AppointmentIdDto(checkIfPatientHasAppointmentNow.getId());
                return new ResponseEntity<>(idDto, HttpStatus.OK);
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
}
