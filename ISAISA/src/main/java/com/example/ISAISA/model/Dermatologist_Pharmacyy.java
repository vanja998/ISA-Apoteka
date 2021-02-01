package com.example.ISAISA.model;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class Dermatologist_Pharmacyy {


    @Id
    @SequenceGenerator(name="seq_dermatologist_pharmacy", sequenceName = "seq_dermatologist_pharmacy", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_dermatologist_pharmacy")
    private Integer id;

    @Column
    private LocalTime beginofwork;

    @Column
    private LocalTime endofwork;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Pharmacy pharmacy_id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Dermatologist dermatologist_id;



}
