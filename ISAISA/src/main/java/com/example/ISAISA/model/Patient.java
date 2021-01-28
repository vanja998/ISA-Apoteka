package com.example.ISAISA.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("patient")
public class Patient extends User{

    public Patient() {
    }

    public Patient(String email, String password, String firstName, String lastName, String address, String phone, String city, String country) {
        super(email, password, firstName, lastName, address, phone, city, country);
    }

    public Patient(Integer id,String email, String password, String firstName, String lastName, String address, String phone, String city, String country) {
        super(id,email, password, firstName, lastName, address, phone, city, country);
    }
}
