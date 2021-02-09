package com.example.ISAISA.service;

import com.example.ISAISA.DTO.OfferDTO;
import com.example.ISAISA.DTO.UserChangeDTO;
import com.example.ISAISA.model.*;
import com.example.ISAISA.repository.OfferRepository;
import com.example.ISAISA.repository.OrderRepository;
import com.example.ISAISA.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

@Service
public class OfferService {

    private OfferRepository offerRepository;

    @Autowired
    public void setOfferRepository(OfferRepository offerRepository) {this.offerRepository=offerRepository;}

    private OrderRepository orderRepository;

    @Autowired
    public void setOfferRepository(OrderRepository orderRepository) {this.orderRepository=orderRepository;}

    private EmailSenderService emailSenderService;

    @Autowired
    public void setEmailSenderService(EmailSenderService emailSenderService) { this.emailSenderService = emailSenderService; }


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

   public Set<Offer> findByOrderr(Orderr orderr) {
        return this.offerRepository.findByOrderr(orderr);
   }

   public Offer acceptOffer(Offer offer) throws Exception {
        AdminPharmacy adminPharmacy = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (offer.getOrderr().getAdminPharmacy().getId() == adminPharmacy.getId()) {
            Date now = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

            Set<Offer> offers = offerRepository.findAll();

            for (Offer o : offers) {
                if (o.getId().equals(offer.getId())) {
                    if (now.after(offer.getOrderr().getDateDeadline())) {
                        offer.setStatusSupplier("prihvacena");
                    }
                } else {
                    o.setStatusSupplier("odbijena");
                    this.offerRepository.save(o);
                    SimpleMailMessage mailMessage = new SimpleMailMessage();
                    mailMessage.setTo(o.getSupplier().getEmail());
                    mailMessage.setSubject("Ponuda za narudzbenicu " + o.getId());
                    mailMessage.setFrom("isaverifikacija@gmail.com");
                    mailMessage.setText("Vasa ponuda za narudzbenicu " + o.getId() + "je odbijena.");
                }
            }

            offer = offerRepository.save(offer);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(offer.getSupplier().getEmail());
            mailMessage.setSubject("Ponuda za narudzbenicu " + offer.getOrderr().getId());
            mailMessage.setFrom("isaverifikacija@gmail.com");
            mailMessage.setText("Vasa ponuda za narudzbenicu " + offer.getOrderr().getId() + " je prihvacena."
                    + "\nNaznacen rok isporuke je: " + offer.getDeliveryDate() + ", a cena je: " + offer.getOfferPrice());

            emailSenderService.sendEmail(mailMessage);

            return offer;
        } else {
            throw new Exception("Niste tvorac ove narudzbenice, pa ne mozete odabrati ponudu za nju!");
        }
   }

   public Offer findById(Integer id) { return this.offerRepository.findOneById(id); }
}
