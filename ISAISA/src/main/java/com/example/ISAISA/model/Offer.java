package com.example.ISAISA.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Offer {


    @Id
    @SequenceGenerator(name="seq_offer", sequenceName = "seq_offer", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_offer")
    private Integer id;

    @Column
    private Integer offerPrice;

    @Column
    private Date deliveryDate;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Orderr orderr;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Supplier supplier;
}
