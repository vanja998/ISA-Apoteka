package com.example.ISAISA.DTO;

import java.util.Date;

public class OrderrDTO {
    private Integer id;

    private Date dateDeadline;

    private String statusSupplier;

    private String adminEmail;

    private String pharmacyName;

    private String pharmacyAddress;

    public OrderrDTO(Integer id, Date dateDeadline, String statusSupplier, String adminEmail, String pharmacyName, String pharmacyAddress) {
        this.id = id;
        this.dateDeadline = dateDeadline;
        this.statusSupplier = statusSupplier;
        this.adminEmail = adminEmail;
        this.pharmacyName = pharmacyName;
        this.pharmacyAddress = pharmacyAddress;
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

    public String getStatusSupplier() {
        return statusSupplier;
    }

    public void setStatusSupplier(String statusSupplier) {
        this.statusSupplier = statusSupplier;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getPharmacyAddress() {
        return pharmacyAddress;
    }

    public void setPharmacyAddress(String pharmacyAddress) {
        this.pharmacyAddress = pharmacyAddress;
    }

    public OrderrDTO() {
    }
}
