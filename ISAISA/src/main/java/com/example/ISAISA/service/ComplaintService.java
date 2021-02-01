package com.example.ISAISA.service;


import com.example.ISAISA.DTO.AdminSystemRegDto;
import com.example.ISAISA.DTO.ReplyDTO;
import com.example.ISAISA.model.*;
import com.example.ISAISA.repository.ComplaintsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    private ComplaintsRepository complaintsRepository;

    @Autowired
    public void setComplaintsRepository(ComplaintsRepository complaintsRepository) {
        this.complaintsRepository = complaintsRepository;
    }

    public List<Complaint> findAll(){
        return complaintsRepository.findAll();
    }

    public Complaint saveReply(ReplyDTO replyDTO) {

        Complaint complaint= complaintsRepository.getOne(replyDTO.getComplaintID());
        complaint.setReply(replyDTO.getReply());
        complaint=this.complaintsRepository.save(complaint);
        return complaint;

    }
}
