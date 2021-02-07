package com.example.ISAISA.service;


import com.example.ISAISA.DTO.OrderDTO;
import com.example.ISAISA.DTO.OrderrDTO;
import com.example.ISAISA.model.*;
import com.example.ISAISA.repository.MedicationRepository;
import com.example.ISAISA.repository.OrderMedicationRepository;
import com.example.ISAISA.repository.OrderRepository;
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

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setMedicationRepository(MedicationRepository medicationRepository) { this.medicationRepository = medicationRepository; }

    @Autowired
    public void setOrderMedicationRepository(OrderMedicationRepository orderMedicationRepository) { this.orderMedicationRepository = orderMedicationRepository; }

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
        return orderRepository.findByAdminPharmacy(adminPharmacy);
    }

}
