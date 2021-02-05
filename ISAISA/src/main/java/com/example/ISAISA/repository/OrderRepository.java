package com.example.ISAISA.repository;


import com.example.ISAISA.model.Orderr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orderr, Integer>{

    List<Orderr> findAllByStatusSupplier(String status);

}
