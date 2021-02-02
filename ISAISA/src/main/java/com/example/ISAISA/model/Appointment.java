package com.example.ISAISA.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
@Entity
public class Appointment {

    @Id
    @SequenceGenerator(name="seq_appointment", sequenceName = "seq_appointment", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_appointment")
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Patient patient;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Dermatologist dermatologist;


    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Pharmacy pharmacy_appointment;

    @Column
    private LocalTime beginofappointment;

    @Column
    private LocalTime endofappointment;

    @Column
    private Integer price;

    public Appointment() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public Pharmacy getPharmacy_appointment() {
        return pharmacy_appointment;
    }

    public void setPharmacy_appointment(Pharmacy pharmacy_appointment) {
        this.pharmacy_appointment = pharmacy_appointment;
    }

    public LocalTime getBeginofappointment() {
        return beginofappointment;
    }

    public void setBeginofappointment(LocalTime beginofappointment) {
        this.beginofappointment = beginofappointment;
    }

    public LocalTime getEndofappointment() {
        return endofappointment;
    }

    public void setEndofappointment(LocalTime endofappointment) {
        this.endofappointment = endofappointment;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


}
