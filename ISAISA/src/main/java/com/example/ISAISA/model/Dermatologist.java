package com.example.ISAISA.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("dermatologist")
public class Dermatologist extends User {

    @JsonIgnore
    @ManyToMany(mappedBy = "dermatologists")
    private Set<Pharmacy> pharmacies = new HashSet<Pharmacy>();

    public Set<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(Set<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
    }

    public Dermatologist() {
    }

    public Dermatologist(Integer id, String email, String password, String firstName, String lastName, String address, String phone, String city, String country) {
        super(id, email, password, firstName, lastName, address, phone, city, country);
    }
}
