package com.example.ISAISA.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Orderr {
    @Id
    @SequenceGenerator(name="seq_orderr", sequenceName = "seq_orderr", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_orderr")
    private Integer id;

    private Date dateDeadline;

    private String statusAdmin;

    private String statusSupplier;

    @JsonIgnore
    @OneToMany(mappedBy = "orderr", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Orderr_Medication> orderr_medications = new HashSet<Orderr_Medication>();

    @JsonIgnore
    @OneToMany(mappedBy = "orderr", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Offer> offers = new HashSet<Offer>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private AdminPharmacy adminPharmacy;

    public Orderr(Integer id, Date dateDeadline, String statusAdmin, String statusSupplier) {
        this.id = id;
        this.dateDeadline = dateDeadline;
        this.statusAdmin = statusAdmin;
        this.statusSupplier = statusSupplier;
    }

    public Orderr() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateDeadline() {
        return dateDeadline;
    }

    public void setDateDeadline(Date dateDeadline) {
        this.dateDeadline = dateDeadline;
    }

    public String getStatusAdmin() {
        return statusAdmin;
    }

    public void setStatusAdmin(String statusAdmin) {
        this.statusAdmin = statusAdmin;
    }

    public String getStatusSupplier() {
        return statusSupplier;
    }

    public void setStatusSupplier(String statusSupplier) {
        this.statusSupplier = statusSupplier;
    }

    public AdminPharmacy getAdminPharmacy() {
        return adminPharmacy;
    }

    public void setAdminPharmacy(AdminPharmacy adminPharmacy) {
        this.adminPharmacy = adminPharmacy;
    }

    public Set<Orderr_Medication> getOrderr_medications() {
        return orderr_medications;
    }

    public void setOrderr_medications(Set<Orderr_Medication> orderr_medications) {
        this.orderr_medications = orderr_medications;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }
}
