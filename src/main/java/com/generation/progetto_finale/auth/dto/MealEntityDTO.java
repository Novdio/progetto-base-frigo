package com.generation.progetto_finale.auth.dto;

import java.util.List;

import com.generation.progetto_finale.auth.model.Meal;

import lombok.Data;

@Data
public class MealEntityDTO {

    private Meal meal;
    private List<String> pasti; // ogni mealType ha dei pasti

}
