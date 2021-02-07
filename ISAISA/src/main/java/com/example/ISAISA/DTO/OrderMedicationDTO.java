package com.example.ISAISA.DTO;

import com.example.ISAISA.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OrderMedicationDTO {

    private Integer id;

    //@JsonFormat(pattern="yyyy-MM-dd")
    private Date dateDeadline;

    private Integer amount;

    private Orderr orderr;

    private Medication medication;

    public OrderMedicationDTO() {
    }

    public OrderMedicationDTO(Integer id, Date dateDeadline, Integer amount, Orderr orderr, Medication medication) {
        this.id = id;
        this.dateDeadline = dateDeadline;
        this.amount = amount;
        this.orderr = orderr;
        this.medication = medication;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Date getDateDeadline() { return dateDeadline; }

    public void setDateDeadline(Date dateDeadline) { this.dateDeadline = dateDeadline; }

    public Integer getAmount() { return amount; }

    public void setAmount(Integer amount) { this.amount = amount; }

    public Orderr getOrderr() { return orderr; }

    public void setOrderr(Orderr orderr) { this.orderr = orderr; }

    public Medication getMedication() { return medication; }

    public void setMedication(Medication medication) { this.medication = medication; }
}
