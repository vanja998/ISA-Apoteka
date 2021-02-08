package com.example.ISAISA.controller;

import com.example.ISAISA.DTO.OrderDTO;
import com.example.ISAISA.DTO.OrderMedicationDTO;
import com.example.ISAISA.DTO.OrderrDTO;
import com.example.ISAISA.DTO.UserChangeDTO;
import com.example.ISAISA.model.AdminPharmacy;
import com.example.ISAISA.model.Orderr;
import com.example.ISAISA.model.Orderr_Medication;
import com.example.ISAISA.service.AdminPharmacyService;
import com.example.ISAISA.service.MedicationService;
import com.example.ISAISA.service.OrderService;
import com.example.ISAISA.service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping(value="/createOrder", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<Orderr> createOrder(@RequestBody OrderDTO orderDTO) throws Exception {

        AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderDTO.setAdminPharmacy(user);
        orderDTO.setStatusAdmin("ceka_ponude");

        Orderr order = orderService.createOrder(orderDTO);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping(value="/ordersByPharmacy", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINPHARMACY')")
    public ResponseEntity<Set<OrderMedicationDTO>> getOrdersByPharmacy(){
        AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Orderr> orderrs = orderService.getOrdersByPharmacy(user);
        Set<OrderMedicationDTO> orderMedicationDTOS = new HashSet<>();

        for (Orderr orderr : orderrs) {
            OrderMedicationDTO om = new OrderMedicationDTO(orderr.getId(), orderr.getDateDeadline());
            orderMedicationDTOS.add(om);
        }

        return new ResponseEntity<>(orderMedicationDTOS, HttpStatus.OK);
    }

}
