package com.example.ISAISA.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("dermatologist")
public class Dermatologist extends User {





    @JsonIgnore
    @OneToMany(mappedBy = "dermatologist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Appointment> appointments = new HashSet<Appointment>();

    @JsonIgnore
    @OneToMany(mappedBy = "dermatologist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Dermatologist_Pharmacyy> dermatologist_pharmacies = new HashSet<Dermatologist_Pharmacyy>();


    public Dermatologist() {
    }

    public Dermatologist(Integer id, String email, String password, String firstName, String lastName, String address, String phone, String city, String country) {
        super(id, email, password, firstName, lastName, address, phone, city, country);
    }
}
