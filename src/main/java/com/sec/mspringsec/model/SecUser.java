package com.sec.mspringsec.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = SecUser.Table_name)
@Data
public class SecUser {

    public static final String Table_name = "SEC_USER";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String email;
    private String pwd;
    private String role;
    private String fullName;



}
