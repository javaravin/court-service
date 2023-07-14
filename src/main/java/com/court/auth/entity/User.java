package com.court.auth.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
