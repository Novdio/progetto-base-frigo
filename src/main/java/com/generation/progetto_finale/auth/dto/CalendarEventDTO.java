package com.generation.progetto_finale.auth.dto;

import java.util.List;

import com.generation.progetto_finale.auth.model.Meal;

import lombok.Data;

@Data
public class CalendarEventDTO {

    private String date;
    private boolean checked = false;
    private List<Meal> meals;
}
