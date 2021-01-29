package com.example.ISAISA.service;

import com.example.ISAISA.DTO.UserChangeDTO;
import com.example.ISAISA.DTO.UserPasswordDTO;
import com.example.ISAISA.model.AdminPharmacy;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.repository.AdminPharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AdminPharmacyService {

    private AdminPharmacyRepository adminPharmacyRepository;

    @Autowired
    public void setAdminPharmacyRepository(AdminPharmacyRepository adminPharmacyRepository) {
        this.adminPharmacyRepository = adminPharmacyRepository;
    }

    public AdminPharmacy changeAdminInfo(UserChangeDTO userDTO) {

        AdminPharmacy user = (AdminPharmacy) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAddress(userDTO.getAddress());
        user.setPhone(userDTO.getPhone());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());

        adminPharmacyRepository.save(user);

        return user;
    }
}
