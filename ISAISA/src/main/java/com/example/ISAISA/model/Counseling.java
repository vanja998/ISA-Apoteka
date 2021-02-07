package com.example.ISAISA.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Counseling {



    @Id
    @SequenceGenerator(name="seq_counseling", sequenceName = "seq_counseling", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_counseling")
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Patient patient;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Pharmacist pharmacist;

    @Column
    private LocalDateTime beginofappointment;

    @Column
    private LocalDateTime endofappointment;

    @Column
    private Integer price;


    public Counseling() {
    }

    public Counseling(Integer id, Patient patient, Pharmacist pharmacist, LocalDateTime beginofappointment, LocalDateTime endofappointment, Integer price) {
        this.id = id;
        this.patient = patient;
        this.pharmacist = pharmacist;
        this.beginofappointment = beginofappointment;
        this.endofappointment = endofappointment;
        this.price = price;
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

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }

    public LocalDateTime getBeginofappointment() {
        return beginofappointment;
    }

    public void setBeginofappointment(LocalDateTime beginofappointment) {
        this.beginofappointment = beginofappointment;
    }

    public LocalDateTime getEndofappointment() {
        return endofappointment;
    }

    public void setEndofappointment(LocalDateTime endofappointment) {
        this.endofappointment = endofappointment;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
