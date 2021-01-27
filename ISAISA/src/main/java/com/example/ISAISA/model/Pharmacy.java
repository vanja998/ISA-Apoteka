package com.example.ISAISA.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pharmacy {

    @Id
    @SequenceGenerator(name="seq_pharmacy", sequenceName = "seq_pharmacy", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pharmacy")
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String address;

    @Column
    private String description;

    @Column
    private Float rating;

    @JsonIgnore
    @OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AdminPharmacy> adminPharmacySet = new HashSet<AdminPharmacy>();

    public Pharmacy() {
    }

    public Pharmacy(Integer id, String name, String address, String description, Float rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.rating = rating;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Set<AdminPharmacy> getAdminPharmacySet() { return adminPharmacySet; }

    public void setAdminPharmacySet(Set<AdminPharmacy> adminPharmacySet) { this.adminPharmacySet = adminPharmacySet; }

    public Float getRating() { return rating; }

    public void setRating(Float rating) { this.rating = rating; }
}
