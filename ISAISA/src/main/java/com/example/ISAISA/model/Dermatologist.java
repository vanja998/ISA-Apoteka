package com.example.ISAISA.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("dermatologist")
public class Dermatologist extends User {


    @Column
    private LocalTime beginofwork;

    @Column
    private LocalTime endofwork;

    public Dermatologist(String email, String password, String firstName, String lastName, String address, String phone, String city, String country, LocalTime beginofwork, LocalTime endofwork, Set<Pharmacy> pharmacies) {
        super(email, password, firstName, lastName, address, phone, city, country);
        this.beginofwork = beginofwork;
        this.endofwork = endofwork;
        this.pharmacies = pharmacies;
    }

    public LocalTime getBeginofwork() {
        return beginofwork;
    }

    public void setBeginofwork(LocalTime beginofwork) {
        this.beginofwork = beginofwork;
    }

    public LocalTime getEndofwork() {
        return endofwork;
    }

    public void setEndofwork(LocalTime endofwork) {
        this.endofwork = endofwork;
    }

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
