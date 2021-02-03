package com.example.ISAISA.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Entity
public class Appointment {

    @Id
    @SequenceGenerator(name="seq_appointment", sequenceName = "seq_appointment", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_appointment")
    private Integer id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Patient patient;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Dermatologist dermatologist;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Pharmacy pharmacy_appointment;

    @Column
    @JsonFormat(pattern="MM-dd-yyyy'T'HH:mm'Z'")
    private LocalDateTime beginofappointment;

    @Column
    @JsonFormat(pattern="MM-dd-yyyy'T'HH:mm'Z'")
    private LocalDateTime endofappointment;

    @Column
    private Integer price;

    public Appointment() {
    }

    public Appointment(Integer id, Patient patient, Dermatologist dermatologist, Pharmacy pharmacy_appointment, LocalDateTime beginofappointment, LocalDateTime endofappointment, Integer price) {
        this.id = id;
        this.patient = patient;
        this.dermatologist = dermatologist;
        this.pharmacy_appointment = pharmacy_appointment;
        this.beginofappointment = beginofappointment;
        this.endofappointment = endofappointment;
        this.price = price;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Patient getPatient() { return patient; }

    public void setPatient(Patient patient) { this.patient = patient; }

    public Dermatologist getDermatologist() { return dermatologist; }

    public void setDermatologist(Dermatologist dermatologist) { this.dermatologist = dermatologist; }

    public Pharmacy getPharmacy_appointment() { return pharmacy_appointment; }

    public void setPharmacy_appointment(Pharmacy pharmacy_appointment) { this.pharmacy_appointment = pharmacy_appointment; }

    public LocalDateTime getBeginofappointment() { return beginofappointment; }

    public void setBeginofappointment(LocalDateTime beginofappointment) { this.beginofappointment = beginofappointment; }

    public LocalDateTime getEndofappointment() { return endofappointment; }

    public void setEndofappointment(LocalDateTime endofappointment) { this.endofappointment = endofappointment; }

    public Integer getPrice() { return price; }

    public void setPrice(Integer price) { this.price = price; }
}
