package com.example.ISAISA.DTO;

import com.example.ISAISA.model.AdminPharmacy;
import com.example.ISAISA.model.Offer;
import com.example.ISAISA.model.Orderr_Medication;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OrderDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date dateDeadline;

    private String statusAdmin;

    private Set<Integer> med_ids = new HashSet<>();

    private Set<Integer> amounts;

    private AdminPharmacy adminPharmacy;

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

    public Set<Integer> getMed_ids() { return med_ids; }

    public void setMed_ids(Set<Integer> med_ids) { this.med_ids = med_ids; }

    public AdminPharmacy getAdminPharmacy() {
        return adminPharmacy;
    }

    public void setAdminPharmacy(AdminPharmacy adminPharmacy) {
        this.adminPharmacy = adminPharmacy;
    }

    public Set<Integer> getAmounts() { return amounts; }

    public void setAmounts(Set<Integer> amounts) { this.amounts = amounts; }
}
