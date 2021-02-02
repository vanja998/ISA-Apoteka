package com.example.ISAISA.service;


import com.example.ISAISA.model.Orderr;
import com.example.ISAISA.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public List<Orderr> findAll(){
        return orderRepository.findAll();
    }
}
