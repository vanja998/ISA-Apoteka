package com.example.ISAISA.model;


import javax.persistence.*;

@Entity
public class Orderr_Medication {
    @Id
    @SequenceGenerator(name="seq_orderr", sequenceName = "seq_orderr", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_orderr")
    private Integer id;

    @Column
    private Integer amount;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Orderr orderr;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Medication medication;

}
