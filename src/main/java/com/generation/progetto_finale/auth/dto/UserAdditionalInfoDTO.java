package com.generation.progetto_finale.auth.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserAdditionalInfoDTO 
{
    private int id;

    private String name;
    private String surname;

    private String phoneNumber;
    private String email;

    private Integer age;
    private Double weight;
    private Double height;
    
    private List<String> diet;
}
