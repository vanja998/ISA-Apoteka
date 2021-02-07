package com.example.ISAISA.DTO;

import java.util.Date;

public class ReservationDto {
    private Integer id;
    private String name;
    private Date dateofreservation;

    public ReservationDto(Integer id, String name, Date dateofreservation) {
        this.id = id;
        this.name = name;
        this.dateofreservation = dateofreservation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateofreservation() {
        return dateofreservation;
    }

    public void setDateofreservation(Date dateofreservation) {
        this.dateofreservation = dateofreservation;
    }
}
