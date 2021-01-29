package com.example.ISAISA.DTO;

import com.example.ISAISA.model.Pharmacy;

import javax.persistence.Column;

public class PharmacistDTO {

    private String firstName;

    private String lastName;

    private Pharmacy pharmacy;

    private Float rating;

    public PharmacistDTO() {
    }

    public PharmacistDTO(String firstName, String lastName, Pharmacy pharmacy, Float rating) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pharmacy = pharmacy;
        this.rating = rating;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public Pharmacy getPharmacy() { return pharmacy; }

    public void setPharmacy(Pharmacy pharmacy) { this.pharmacy = pharmacy; }

    public Float getRating() { return rating; }

    public void setRating(Float rating) { this.rating = rating; }

}