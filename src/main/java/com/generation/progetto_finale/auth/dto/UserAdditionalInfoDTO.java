package com.generation.progetto_finale.auth.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserAdditionalInfoDTO {
    private int id;

    private String name;
    private String surname;

    private String sex;
    private Integer age;
    private Double weight;
    private Double height;

    // Aggiungere BMI e IBW
    public Double getIdealWeight() {
        switch (sex) {
            case "M":
                return 50 + (0.91 * (height - 152.4));
            case "F":
                return 45.5 + (0.91 * (height - 152.4));
            default:
                throw new RuntimeException();

        }
    }

    public Double getBmi()
    {
        return weight/(Math.pow(height, 2));
    }
}
