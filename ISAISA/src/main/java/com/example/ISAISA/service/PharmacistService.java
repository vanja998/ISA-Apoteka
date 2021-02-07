package com.example.ISAISA.service;

import com.example.ISAISA.DTO.PharmacistDTO;
import com.example.ISAISA.DTO.UserChangeDTO;
import com.example.ISAISA.model.Pharmacist;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.repository.AppointmentRepository;
import com.example.ISAISA.repository.CounselingRepository;
import com.example.ISAISA.repository.PharmacistRepository;
import org.apache.tomcat.websocket.pojo.PojoMessageHandlerPartialText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.ISAISA.model.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PharmacistService {

    private PharmacistRepository pharmacistRepository;
    private AuthorityService authService;
    private PasswordEncoder passwordEncoder;
    private CounselingRepository counselingRepository;

    @Autowired
    public void setPharmacistRepository(PharmacistRepository pharmacistRepository) {
        this.pharmacistRepository = pharmacistRepository;
    }

    @Autowired
    public void setAuthService(AuthorityService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setCounselingRepository(CounselingRepository counselingRepository) { this.counselingRepository = counselingRepository; }

    public Set<PharmacistDTO> getPharmacistsByPharmacy(Pharmacy pharmacy) {

        Set<Pharmacist> pharmacists = pharmacistRepository.findAllByPharmacy(pharmacy);

        Set<PharmacistDTO> pharmacistDTOS = new HashSet<>();
        for(Pharmacist p : pharmacists) {
            PharmacistDTO pharmacistDTO = new PharmacistDTO(p.getId(), p.getFirstName(), p.getLastName(), p.getPharmacy(), p.getRating());
            pharmacistDTOS.add(pharmacistDTO);
        }

        return pharmacistDTOS;
    }

    public Set<PharmacistDTO> getPharmacistsByPharmacyAndFirstNameAndLastName(Pharmacy pharmacy, String firstName, String lastName) {

        Set<Pharmacist> pharmacists = pharmacistRepository.findAllByPharmacyAndFirstNameIgnoreCaseAndLastNameIgnoreCase(pharmacy, firstName, lastName);

        Set<com.example.ISAISA.DTO.PharmacistDTO> pharmacistDTOS = new HashSet<>();
        for(Pharmacist p : pharmacists) {
            com.example.ISAISA.DTO.PharmacistDTO pharmacistDTO = new com.example.ISAISA.DTO.PharmacistDTO(p.getId(), p.getFirstName(), p.getLastName(), p.getPharmacy(), p.getRating());
            pharmacistDTOS.add(pharmacistDTO);
        }

        return pharmacistDTOS;
    }

    public Pharmacist save(PharmacistDTO pharmacistDTO) {
        Pharmacist pharmacist = new Pharmacist();
        pharmacist.setFirstName(pharmacistDTO.getFirstName());
        pharmacist.setLastName(pharmacistDTO.getLastName());
        pharmacist.setEmail(pharmacistDTO.getEmail());
        pharmacist.setPassword(passwordEncoder.encode(pharmacistDTO.getPassword()));
        pharmacist.setAddress(pharmacistDTO.getAddress());
        pharmacist.setPhone(pharmacistDTO.getPhone());
        pharmacist.setCity(pharmacistDTO.getCity());
        pharmacist.setCountry(pharmacistDTO.getCountry());
        pharmacist.setPharmacy(pharmacistDTO.getPharmacy());
        pharmacist.setBeginofwork(pharmacistDTO.getBeginofwork());
        pharmacist.setEndofwork(pharmacistDTO.getEndofwork());

        pharmacist.setEnabled(true);

        List<Authority> auth = authService.findByname("ROLE_PHARMACIST");
        pharmacist.setAuthorities(auth);

        pharmacist = this.pharmacistRepository.save(pharmacist);
        return pharmacist;
    }

    public Set<PharmacistDTO> getAll() {

        List<Pharmacist> pharmacists = pharmacistRepository.findAll();

        Set<com.example.ISAISA.DTO.PharmacistDTO> pharmacistDTOS = new HashSet<>();

        for(Pharmacist p : pharmacists) {
            com.example.ISAISA.DTO.PharmacistDTO pharmacistDTO = new com.example.ISAISA.DTO.PharmacistDTO(p.getId(), p.getFirstName(), p.getLastName(), p.getPharmacy(), p.getRating());
            pharmacistDTOS.add(pharmacistDTO);
        }

        return pharmacistDTOS;
    }

    public Pharmacist changePharmacistInfo(UserChangeDTO userDTO) {

        Pharmacist user = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAddress(userDTO.getAddress());
        user.setPhone(userDTO.getPhone());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());

        pharmacistRepository.save(user);

        return user;
    }

    public Set<PharmacistDTO> getPharmacistsByFirstNameAndLastName(String firstName, String lastName) {

        Set<Pharmacist> pharmacists = pharmacistRepository.findAllByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName);

        Set<com.example.ISAISA.DTO.PharmacistDTO> pharmacistDTOS = new HashSet<>();
        for(Pharmacist p : pharmacists) {
            com.example.ISAISA.DTO.PharmacistDTO pharmacistDTO = new com.example.ISAISA.DTO.PharmacistDTO(p.getId(), p.getFirstName(), p.getLastName(), p.getPharmacy(), p.getRating());
            pharmacistDTOS.add(pharmacistDTO);
        }

        return pharmacistDTOS;
    }

    public Set<PharmacistDTO> filterPharmacists(Float ratingUnder, Float ratingOver, Pharmacy pharmacy) {
        if (pharmacy == null) {
            return getPharmacistsByRatingBetween(ratingOver, ratingUnder);
        } else {
            return getPharmacistsByRatingBetweenAndPharmacyName(ratingOver, ratingUnder, pharmacy);
        }
    }

    public Set<PharmacistDTO> getPharmacistsByRatingBetweenAndPharmacyName(Float ratingOver, Float ratingUnder, Pharmacy pharmacy) {

        Set<Pharmacist> pharmacists = pharmacistRepository.findAllByRatingBetweenAndPharmacy(ratingOver, ratingUnder, pharmacy);

        Set<com.example.ISAISA.DTO.PharmacistDTO> pharmacistDTOS = new HashSet<>();
        for(Pharmacist p : pharmacists) {
            com.example.ISAISA.DTO.PharmacistDTO pharmacistDTO = new com.example.ISAISA.DTO.PharmacistDTO(p.getId(), p.getFirstName(), p.getLastName(), p.getPharmacy(), p.getRating());
            pharmacistDTOS.add(pharmacistDTO);
        }

        return pharmacistDTOS;
    }

    public Set<PharmacistDTO> getPharmacistsByRatingBetween(Float ratingOver, Float ratingUnder) {

        Set<Pharmacist> pharmacists = pharmacistRepository.findAllByRatingBetween(ratingOver, ratingUnder);

        Set<com.example.ISAISA.DTO.PharmacistDTO> pharmacistDTOS = new HashSet<>();
        for(Pharmacist p : pharmacists) {
            com.example.ISAISA.DTO.PharmacistDTO pharmacistDTO = new com.example.ISAISA.DTO.PharmacistDTO(p.getId(), p.getFirstName(), p.getLastName(), p.getPharmacy(), p.getRating());
            pharmacistDTOS.add(pharmacistDTO);
        }

        return pharmacistDTOS;
    }

    public void deletePharmacist(Integer id) throws Exception {
        Pharmacist pharmacist = pharmacistRepository.findOneById(id);

        Set<Counseling> counselings = counselingRepository.findByPharmacist(pharmacist);
        Set<Counseling> reservedCounselings = new HashSet<>();

        for (Counseling counseling : counselings) {
            if (counseling.getPatient() != null && counseling.getBeginofappointment().isAfter(LocalDateTime.now())) {
                reservedCounselings.add(counseling);
            }
        }

        if(reservedCounselings.isEmpty()) {
            this.pharmacistRepository.delete(pharmacist);
        } else {
            throw new Exception("Farmaceut ima zakazane termine, nije moguce ukloniti ga!");
        }

    }

}
