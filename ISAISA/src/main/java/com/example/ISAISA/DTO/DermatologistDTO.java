package com.example.ISAISA.DTO;

import com.example.ISAISA.model.Pharmacy;

public class DermatologistDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    public DermatologistDTO(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public DermatologistDTO() {
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }
}
