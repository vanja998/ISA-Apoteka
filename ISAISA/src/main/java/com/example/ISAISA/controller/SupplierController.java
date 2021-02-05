package com.example.ISAISA.controller;

import com.example.ISAISA.DTO.OfferDTO;
import com.example.ISAISA.DTO.OrderrDTO;
import com.example.ISAISA.DTO.StatusDTO;
import com.example.ISAISA.model.*;
import com.example.ISAISA.repository.*;
import com.example.ISAISA.service.OfferService;
import com.example.ISAISA.service.OrderService;
import com.example.ISAISA.service.SupplierService;
import com.example.ISAISA.service.UserService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.*;

@RestController
@RequestMapping(value = "/suppliers")
public class SupplierController {

    private SupplierService supplierService;
    @Autowired
    private OfferService offerService;

    @Autowired
    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UserService userService;


    @GetMapping(value="/allorders",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('SUPPLIER')")// value nije naveden, jer koristimo bazni url
    public ResponseEntity<List<OrderrDTO>> getOrders() {
        List<Orderr> orderList = this.orderService.findAll();


        List<OrderrDTO> orderrDTOS = new ArrayList<>();

        for (Orderr orderr : orderList) {
            AdminPharmacy adminPharmacy= orderr.getAdminPharmacy();
            Pharmacy pharmacy=adminPharmacy.getPharmacy();
            String emailAdmin=adminPharmacy.getEmail();
            String pharmacyName=pharmacy.getName();
            String address=pharmacy.getAddress();

            Supplier user = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Offer offer= offerRepository.findOneByOrderrAndSupplier(orderr,user);
            Integer ponuda=0;
            if(offer!=null) {
                ponuda=offer.getOfferPrice();
            }
            OrderrDTO orderrDTO = new OrderrDTO(orderr.getId(),orderr.getDateDeadline(),orderr.getStatusSupplier(),emailAdmin,pharmacyName,address,ponuda,user);
            orderrDTOS.add(orderrDTO);
        }
        return new ResponseEntity<>(orderrDTOS, HttpStatus.OK);
    }

    @PostMapping(value="/filter",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('SUPPLIER')")
    public ResponseEntity<List<OrderrDTO>> filterOrders(@RequestBody StatusDTO statusDTO) {
        List<Orderr> orderList = this.orderRepository.findAllByStatusSupplier(statusDTO.getStatus());


        List<OrderrDTO> orderrDTOS = new ArrayList<>();

        for (Orderr orderr : orderList) {
            AdminPharmacy adminPharmacy= orderr.getAdminPharmacy();
            Pharmacy pharmacy=adminPharmacy.getPharmacy();
            String emailAdmin=adminPharmacy.getEmail();
            String pharmacyName=pharmacy.getName();
            String address=pharmacy.getAddress();

            Supplier user = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Offer offer= offerRepository.findOneByOrderrAndSupplier(orderr,user);
            Integer ponuda=0;
            if(offer!=null) {
                ponuda=offer.getOfferPrice();
            }
            OrderrDTO orderrDTO = new OrderrDTO(orderr.getId(),orderr.getDateDeadline(),orderr.getStatusSupplier(),emailAdmin,pharmacyName,address,ponuda,user);
            orderrDTOS.add(orderrDTO);
        }
        return new ResponseEntity<>(orderrDTOS, HttpStatus.OK);
    }

    @PostMapping("/addOffer")
    public ResponseEntity<Offer> addOffer(@RequestBody OfferDTO offerDTO, UriComponentsBuilder ucBuilder) throws ResourceConflictException, Exception {

        Orderr order= orderRepository.findById(offerDTO.getOrderID()).get();
        Supplier user = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Medication> medication = user.getMedication();
        Set<Orderr_Medication> orderr_medication= order.getOrderr_medications();
        List<Medication> listmedication= new ArrayList<>();
        for(Orderr_Medication orderr_medication1: orderr_medication){
            Medication med=orderr_medication1.getMedication();
            listmedication.add(med);
        }
        Integer n=0;
        for(Medication medication1: listmedication){
            Iterator<Medication> it=medication.iterator();
            while(it.hasNext()){
                Medication med=it.next();
                if(medication1.getId()==med.getId()){
                    n=n+1;
                }

            }

            if (n==0){
                throw new Exception("Nedostaju lekovi");
            }
            n=0;


        }

        Date date= new Date();
        Date date1=order.getDateDeadline();

        if (date.after(date1)){
            throw new Exception("Zakasnili ste sa ponudom,istekao je rok za ponudu");
        }





        Offer offer= this.offerService.saveOffer(offerDTO,user,order);



        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Offer>(offer, HttpStatus.CREATED);
    }





    @PostMapping(value = "/changeoffer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('SUPPLIER')")
    public ResponseEntity<?> changeOffer(@RequestBody OfferDTO offerDTO) {

            Offer offer = offerService.changeOfferInfo(offerDTO);

            return new ResponseEntity<>(offer, HttpStatus.OK);
        }


}
