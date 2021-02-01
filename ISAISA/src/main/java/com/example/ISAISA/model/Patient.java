package com.example.ISAISA.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("patient")
public class Patient extends User{


    @OneToOne(mappedBy = "patient")
    private Complaint complaint;


    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "allergy_patient", joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "medication_id", referencedColumnName = "id"))
    private Set<Medication> medication = new HashSet<Medication>();
    public Patient() {
    }

    public Patient(String email, String password, String firstName, String lastName, String address, String phone, String city, String country) {
        super(email, password, firstName, lastName, address, phone, city, country);
    }

    public Patient(Integer id,String email, String password, String firstName, String lastName, String address, String phone, String city, String country) {
        super(id,email, password, firstName, lastName, address, phone, city, country);
    }
}
