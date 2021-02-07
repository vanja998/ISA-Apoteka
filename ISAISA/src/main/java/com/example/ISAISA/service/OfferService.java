package com.example.ISAISA.service;

import com.example.ISAISA.DTO.OfferDTO;
import com.example.ISAISA.DTO.UserChangeDTO;
import com.example.ISAISA.model.*;
import com.example.ISAISA.repository.OfferRepository;
import com.example.ISAISA.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    private OfferRepository offerRepository;

    @Autowired
    public void setOfferRepository(OfferRepository offerRepository) {this.offerRepository=offerRepository;}

    private OrderRepository orderRepository;

    @Autowired
    public void setOfferRepository(OrderRepository orderRepository) {this.orderRepository=orderRepository;}

    public Offer saveOffer(OfferDTO offerDTO, Supplier supplier, Orderr orderr) {
        Offer offer= new Offer();
        offer.setOfferPrice(offerDTO.getOfferPrice());
        offer.setOrderr(orderr);
        offer.setDeliveryDate(offerDTO.getDeliveryDate());
        offer.setSupplier(supplier);
        offer.setStatusSupplier("ceka_na_odgovor");
        offer=this.offerRepository.save(offer);
        return offer;

    }


    public Offer changeOfferInfo(OfferDTO offerDTO) {

        Supplier user = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Orderr order= orderRepository.findById(offerDTO.getOrderID()).get();
        Offer offer = offerRepository.findOneByOrderrAndSupplier(order,user);
        offer.setDeliveryDate(offerDTO.getDeliveryDate());
        offer.setOfferPrice(offerDTO.getOfferPrice());

        offer=offerRepository.save(offer);

        return offer;
    }
}
