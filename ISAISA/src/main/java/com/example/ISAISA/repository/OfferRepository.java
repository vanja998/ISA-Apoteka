package com.example.ISAISA.repository;


import com.example.ISAISA.model.*;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface OfferRepository extends CrudRepository<Offer, String> {
    Offer findOneByOrderrAndSupplier (Orderr orderr, Supplier supplier);
}
