package com.example.ISAISA.DTO;

public class UserPasswordDTO {

    private String password;

    public UserPasswordDTO(String password) {
        this.password = password;
    }

    public UserPasswordDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
