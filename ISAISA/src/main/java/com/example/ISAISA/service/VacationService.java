package com.example.ISAISA.service;

import com.example.ISAISA.model.Vacation;
import com.example.ISAISA.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class VacationService {

    private VacationRepository vacationRepository;
    private EmailSenderService emailSenderService;

    @Autowired
    public void setVacationRepository(VacationRepository vacationRepository) {this.vacationRepository = vacationRepository; }

    @Autowired
    public void setEmailSenderService(EmailSenderService emailSenderService) {this.emailSenderService = emailSenderService; }

    public Set<Vacation> findAllByPharmacistNotNull() {
        return this.vacationRepository.findAllByPharmacistNotNullAndApprovedNullAndDateBeginVacationAfter(LocalDate.now());
    }

    public Set<Vacation> findAllByDermatologistNotNull() {
        return this.vacationRepository.findAllByDermatologistNotNullAndApprovedNullAndDateBeginVacationAfter(LocalDate.now());
    }

    public Vacation findById(Integer id) { return this.vacationRepository.findOneById(id); }

    public Vacation approveVacationPharmacist(Vacation vacation) {
        vacation.setApproved(true);
        vacation = this.vacationRepository.save(vacation);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(vacation.getPharmacist().getEmail());
        mailMessage.setSubject("Zahtev za godisnjim odmorom");
        mailMessage.setFrom("isaverifikacija@gmail.com");
        mailMessage.setText("Vas zahtev za godisnjim odmorom u periodu od " + vacation.getDateBeginVacation() +
                " do " + vacation.getDateEndVacation() + " je prihvacen.");

        emailSenderService.sendEmail(mailMessage);

        return vacation;
    }

    public Vacation approveVacationDermatologist(Vacation vacation) {
        vacation.setApproved(false);
        vacation = this.vacationRepository.save(vacation);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(vacation.getDermatologist().getEmail());
        mailMessage.setSubject("Zahtev za godisnjim odmorom");
        mailMessage.setFrom("isaverifikacija@gmail.com");
        mailMessage.setText("Vas zahtev za godisnjim odmorom u periodu od " + vacation.getDateBeginVacation() +
                " do " + vacation.getDateEndVacation() + " je prihvacen.");

        emailSenderService.sendEmail(mailMessage);

        return vacation;
    }

    public Vacation denyVacationPharmacist(Vacation vacation, String reasonDenied) {
        vacation.setApproved(false);
        vacation = this.vacationRepository.save(vacation);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(vacation.getPharmacist().getEmail());
        mailMessage.setSubject("Zahtev za godisnjim odmorom");
        mailMessage.setFrom("isaverifikacija@gmail.com");
        mailMessage.setText("Vas zahtev za godisnjim odmorom u periodu od " + vacation.getDateBeginVacation() +
                " do " + vacation.getDateEndVacation() + " je odbijen.\n\nRazlog za odbijanje:\n" +
                reasonDenied);

        emailSenderService.sendEmail(mailMessage);

        return vacation;
    }

    public Vacation denyVacationDermatologist(Vacation vacation, String reasonDenied) {
        vacation.setApproved(false);
        vacation = this.vacationRepository.save(vacation);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(vacation.getDermatologist().getEmail());
        mailMessage.setSubject("Zahtev za godisnjim odmorom");
        mailMessage.setFrom("isaverifikacija@gmail.com");
        mailMessage.setText("Vas zahtev za godisnjim odmorom u periodu od " + vacation.getDateBeginVacation() +
                " do " + vacation.getDateEndVacation() + " je odbijen.\n\nRazlog za odbijanje:\n" +
                reasonDenied);

        emailSenderService.sendEmail(mailMessage);

        return vacation;
    }

}
