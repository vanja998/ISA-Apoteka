package com.example.ISAISA.controller;

import com.example.ISAISA.service.AdminSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/systemadmins")
public class AdminSystemController {

    private AdminSystemService adminSystemService;

    @Autowired
    public void setAdminSystemService(AdminSystemService adminSystemService) {
        this.adminSystemService = adminSystemService;
    }
}
