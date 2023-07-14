package com.court.auth.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {



    private String userType; //User Type  - Admin / Member / Non member / VIP / TopMgmt
    private String mobileNo;
    private String email;
    private String userName;
    private String password;
    private String location;
    private String country; // Countries -
    private String referral = "";
    private LocalDateTime lastLogin;

    private String roles;
}