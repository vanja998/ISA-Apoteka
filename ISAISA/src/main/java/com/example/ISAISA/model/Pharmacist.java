package com.example.ISAISA.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("pharmacist")
public class Pharmacist extends User {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;

    public Pharmacist() {
    }

    public Pharmacist(Integer id, String email, String password, String firstName, String lastName, String address, String phone, String city, String country) {
        super(id, email, password, firstName, lastName, address, phone, city, country);
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}
