package com.example.ISAISA.service;

import com.example.ISAISA.DTO.BooleanDto;
import com.example.ISAISA.model.Pharmacy;
import com.example.ISAISA.model.Reservation;
import com.example.ISAISA.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;

    @Autowired
    public void setReservationRepository(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation save(Reservation reservation) {return reservationRepository.save(reservation);}

    public void  deleteById(Integer id) {reservationRepository.deleteById(id);}
    public void delete(Reservation reservation){reservationRepository.delete(reservation);}

    public List<Reservation> findAll() {return reservationRepository.findAll();}

    public Reservation findById(Integer id) {return reservationRepository.findOneByid(id);}

    public Boolean checkPharmacy(Pharmacy reservationPharmacy, Pharmacy pharmacistPharmacy){
        if(reservationPharmacy.getId() == pharmacistPharmacy.getId()){
            return true;
        }else {
            return false;
        }
    }

    public void giveMedication(Integer reservationId){

        Reservation reservation = reservationRepository.findOneByid(reservationId);

        reservation.setMedicationtaken(true);
        reservationRepository.save(reservation);

    }

    public Reservation findReservation(Integer id) {

        Reservation reservation = reservationRepository.findOneByid(id);
        if(reservation.getMedicationtaken()){
            return null;
        }else {
            return reservation;
        }

    }

}
