package com.example.ISAISA.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("pharmacist")
public class Pharmacist extends User {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;

    @Column
    private Float rating;

    public Pharmacist() {
    }

    public Pharmacist(Integer id, String email, String password, String firstName, String lastName, String address, String phone, String city, String country, Pharmacy pharmacy, Float rating) {
        super(id, email, password, firstName, lastName, address, phone, city, country);
        this.pharmacy = pharmacy;
        this.rating = rating;
    }

    public Float getRating() { return rating; }

    public void setRating(Float rating) { this.rating = rating; }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }


}
