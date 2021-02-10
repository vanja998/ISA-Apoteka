package com.example.ISAISA.service;


import com.example.ISAISA.DTO.OrderDTO;
import com.example.ISAISA.DTO.OrderrDTO;
import com.example.ISAISA.model.*;
import com.example.ISAISA.repository.*;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private MedicationRepository medicationRepository;
    private OrderMedicationRepository orderMedicationRepository;
    private AuthorityService authService;
    private AdminPharmacyRepository adminPharmacyRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setMedicationRepository(MedicationRepository medicationRepository) { this.medicationRepository = medicationRepository; }

    @Autowired
    public void setOrderMedicationRepository(OrderMedicationRepository orderMedicationRepository) { this.orderMedicationRepository = orderMedicationRepository; }

    @Autowired
    public void setAuthService(AuthorityService authService) {this.authService = authService;}

    @Autowired
    public void setAdminPharmacyRepository(AdminPharmacyRepository adminPharmacyRepository) { this.adminPharmacyRepository = adminPharmacyRepository; }

    public List<Orderr> findAll(){
        return orderRepository.findAll();
    }

    public Orderr createOrder(OrderDTO orderDTO) throws Exception {
        Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        if (orderDTO.getDateDeadline().before(date)){
            throw new Exception("Nije moguce postaviti ovakav rok isporuke!");
        }
        Set<Orderr_Medication> orderr_medications = new HashSet<>();
        List<Integer> amounts = new ArrayList<>();
        amounts.addAll(orderDTO.getAmounts());

        int counter = 0;
        for (Integer i : orderDTO.getMed_ids()) {
            Medication medication = medicationRepository.findOneById(i);

            Orderr_Medication om = new Orderr_Medication(amounts.get(counter), medication);

            orderr_medications.add(om);
            counter++;
        }

        Orderr orderr = new Orderr(orderDTO.getDateDeadline(), orderr_medications , orderDTO.getAdminPharmacy(), orderDTO.getStatusAdmin());
        orderr = orderRepository.save(orderr);

        for (Orderr_Medication om : orderr_medications) {
            om.setOrderr(orderr);
            om = orderMedicationRepository.save(om);
        }

        return orderr;
    }

    public Set<Orderr> getOrdersByPharmacy(AdminPharmacy adminPharmacy) {
        Pharmacy pharmacy = adminPharmacy.getPharmacy();
        Set<AdminPharmacy> adminPharmacies = adminPharmacyRepository.findAllByPharmacy(pharmacy);

        Set<Orderr> orderrs = new HashSet<>();

        for (AdminPharmacy ap : adminPharmacies) {
            orderrs.addAll(orderRepository.findByAdminPharmacy(ap));
        }

        return orderrs;
    }

    public Orderr findById(Integer id) {
        return this.orderRepository.findOneById(id);
    }

    public void deleteOrder(Orderr orderr) throws Exception {
        if (orderr.getOffers().isEmpty()) {
            for (Orderr_Medication om : orderr.getOrderr_medications()) {
                this.orderMedicationRepository.delete(om);
            }
            this.orderRepository.delete(orderr);
        }
        else
            throw new Exception("Nije moguce obrisati narudzbenicu nakon sto je primljena ponuda!");
    }

    public Orderr changeOrder(Orderr orderr, Set<Orderr_Medication> orderr_medications) throws Exception {
        if (orderr.getOffers().isEmpty()) {
            Orderr orderr1 = orderRepository.findOneById(orderr.getId());
            orderr1.setDateDeadline(orderr.getDateDeadline());

            orderr1 = orderRepository.save(orderr1);

            for(Orderr_Medication om : orderr_medications) {
                om = orderMedicationRepository.save(om);
            }

            return orderr1;
        }
        else
            throw new Exception("Nije moguce izmeniti narudzbenicu nakon sto je primljena ponuda!");


    }
}
