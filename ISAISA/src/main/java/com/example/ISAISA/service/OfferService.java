package com.example.ISAISA.service;

import com.example.ISAISA.DTO.OfferDTO;
import com.example.ISAISA.model.*;
import com.example.ISAISA.repository.OfferRepository;
import com.example.ISAISA.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    private OfferRepository offerRepository;

    @Autowired
    public void setOfferRepository(OfferRepository offerRepository) {this.offerRepository=offerRepository;}

    public Offer saveOffer(OfferDTO offerDTO, Supplier supplier, Orderr orderr) {
        Offer offer= new Offer();
        offer.setOfferPrice(offerDTO.getOfferPrice());
        offer.setOrderr(orderr);
        offer.setDeliveryDate(offerDTO.getDeliveryDate());
        offer.setSupplier(supplier);
        offer=this.offerRepository.save(offer);
        return offer;

    }
}
