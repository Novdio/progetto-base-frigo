package com.generation.progetto_finale.auth.dto;

import java.util.List;

import com.generation.progetto_finale.auth.model.Alarm;

import lombok.Data;

@Data
public class UserAdditionalInfoDTO {
    private int id;

    private String name;
    private String surname;
    private List<String> ricette;
    private String sex;
    private Integer age;
    private List<Double> weight;
    private Double height;
    private List<Alarm> alarms;

}
