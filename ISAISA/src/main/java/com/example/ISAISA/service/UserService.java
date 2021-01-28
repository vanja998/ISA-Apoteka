package com.example.ISAISA.service;

import com.example.ISAISA.model.*;
import com.example.ISAISA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authService;

    public User findById(Integer id) throws AccessDeniedException {
        User u = userRepository.findById(id).orElseGet(null);
        return u;
    }

    public List<User> findAll() throws AccessDeniedException {
        List<User> result = userRepository.findAll();
        return result;
    }

    public User save(UserRequest userRequest) {
        User u = new User();
        u.setEmail(userRequest.getEmail());
        // pre nego sto postavimo lozinku u atribut hesiramo je
        u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        u.setFirstName(userRequest.getFirstname());
        u.setLastName(userRequest.getLastname());
        u.setEnabled(true);

        List<Authority> auth = authService.findByname("ROLE_USER");
        // u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
        u.setAuthorities(auth);

        u = this.userRepository.save(u);
        return u;
    }

    public User savePatient(PatientDto userRequest) {
        Patient p = new Patient();
        p.setEmail(userRequest.getEmail());
        // pre nego sto postavimo lozinku u atribut hesiramo je
        p.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        p.setFirstName(userRequest.getNamee());
        p.setLastName(userRequest.getLastName());
        p.setAddress(userRequest.getAdress());
        p.setCountry(userRequest.getCountry());
        p.setCity(userRequest.getCity());
        p.setPhone(userRequest.getPhoneNumber());

        p.setEnabled(false);

        List<Authority> auth = authService.findByname("ROLE_PATIENT");
        // u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
        p.setAuthorities(auth);

        p = this.userRepository.save(p);
        return p;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
