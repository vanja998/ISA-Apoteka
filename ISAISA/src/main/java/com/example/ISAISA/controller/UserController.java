package com.example.ISAISA.controller;


import com.example.ISAISA.model.User;
import com.example.ISAISA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value="/u",produces = MediaType.APPLICATION_JSON_VALUE)  // tip odgovora
    public ResponseEntity<User> getKorisnik(String email) {
        // Dobavljamo zaposlenog po mejlu
        User korisnik = this.userService.findByEmail(email);

        // Kreiramo objekat klase EmployeeDTO
        User korisnikDTO = new User();
        korisnikDTO.setId(korisnik.getId());
        korisnikDTO.setEmail(korisnik.getEmail());
        korisnikDTO.setFirstName(korisnik.getFirstName());
        korisnikDTO.setLastName(korisnik.getLastName());
        korisnikDTO.setAddress(korisnik.getAddress());
        korisnikDTO.setPassword(korisnik.getPassword());
        korisnikDTO.setPhone(korisnik.getPhone());
        korisnikDTO.setCity(korisnik.getCity());
        korisnikDTO.setCountry(korisnik.getCountry());

        // Vraćamo ResponseEntity
        // ResponseEntity predstavlja ceo HTTP odgovor: status kod, zaglavlja i body.
        // Možemo u potpunosti da kontrolišemo šta će se nalaziti u odgovoru koji želimo da vratimo.
        // Ako je došlo do greške postavljamo drugi HttpStatus npr. HttpStatus.BAD_REQUEST
        return new ResponseEntity<>(korisnikDTO, HttpStatus.OK);
    }
}
