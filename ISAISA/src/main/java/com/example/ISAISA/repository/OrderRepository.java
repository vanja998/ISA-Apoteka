package com.example.ISAISA.repository;

import com.example.ISAISA.model.AdminPharmacy;
import com.example.ISAISA.model.Orderr;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Orderr, Integer>{

    List<Orderr> findAllByStatusSupplier(String status);

    Set<Orderr> findByAdminPharmacy(AdminPharmacy adminPharmacy);

}
