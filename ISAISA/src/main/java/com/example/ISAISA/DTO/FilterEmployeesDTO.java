package com.example.ISAISA.DTO;

public class FilterEmployeesDTO {

    private Integer ratingOver;
    private Integer ratingUnder;
    private String pharmacyName;

    public FilterEmployeesDTO() {
    }

    public FilterEmployeesDTO(Integer ratingOver, Integer ratingUnder, String pharmacyName) {
        this.ratingOver = ratingOver;
        this.ratingUnder = ratingUnder;
        this.pharmacyName = pharmacyName;
    }

    public FilterEmployeesDTO(Integer ratingOver, Integer ratingUnder) {
        this.ratingOver = ratingOver;
        this.ratingUnder = ratingUnder;
    }

    public Integer getRatingOver() { return ratingOver; }

    public void setRatingOver(Integer ratingOver) { this.ratingOver = ratingOver; }

    public Integer getRatingUnder() { return ratingUnder; }

    public void setRatingUnder(Integer ratingUnder) { this.ratingUnder = ratingUnder; }

    public String getPharmacyName() { return pharmacyName; }

    public void setPharmacyName(String pharmacyName) { this.pharmacyName = pharmacyName; }
}
