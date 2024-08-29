package com.generation.progetto_finale.auth.dto;

import lombok.Data;

@Data
public class PutInfoDTO {

    private String name;
    private String surname;
    private String sex;
    private Integer age;
    private Double height;

}
