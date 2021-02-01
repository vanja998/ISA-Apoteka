package com.example.ISAISA.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Counseling {



    @Id
    @SequenceGenerator(name="seq_counseling", sequenceName = "seq_counseling", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_counseling")
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Patient patient;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Pharmacist pharmacist;

    @Column
    private LocalDateTime beginofappointment;

    @Column
    private LocalDateTime endofappointment;

    @Column
    private Integer price;
}
