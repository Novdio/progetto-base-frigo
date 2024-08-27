package com.generation.progetto_finale.auth.dto;

import java.util.List;

import com.generation.progetto_finale.auth.model.MealEntity;

import lombok.Data;

@Data
public class CalendarEventDTO {

    private Integer id;
    private String date;
    private boolean checked = false;
    private List<MealEntity> meals;
}
