package com.example.ISAISA.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("patient")
public class Patient extends User{

    @Column
    private Integer penalty;
    @JsonIgnore
    @OneToOne(mappedBy = "patient")
    private Complaint complaint;

    @JsonIgnore
    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Appointment> appointments = new HashSet<Appointment>();

    @JsonIgnore
    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<Reservation>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "allergy_patient", joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "medication_id", referencedColumnName = "id"))
    private Set<Medication> medication = new HashSet<Medication>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "promotion_patients", joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"))
    private Set<Promotion> pharmacies_promotions = new HashSet<Promotion>();

    public Patient() {
    }

    public Patient(String email, String password, String firstName, String lastName, String address, String phone, String city, String country) {
        super(email, password, firstName, lastName, address, phone, city, country);
    }

    public Patient(Integer id,String email, String password, String firstName, String lastName, String address, String phone, String city, String country) {
        super(id,email, password, firstName, lastName, address, phone, city, country);
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Set<Promotion> getPharmacies_promotions() {
        return pharmacies_promotions;
    }

    public void setPharmacies_promotions(Set<Promotion> pharmacies_promotions) {
        this.pharmacies_promotions = pharmacies_promotions;
    }

    public Integer getPenalty() {
        return penalty;
    }

    public void setPenalty(Integer penalty) {
        this.penalty = penalty;
    }

    public Set<Medication> getMedication() {
        return medication;
    }

    public void setMedication(Set<Medication> medication) {
        this.medication = medication;
    }
}
