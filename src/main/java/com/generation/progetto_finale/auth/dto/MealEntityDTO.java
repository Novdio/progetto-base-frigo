package com.generation.progetto_finale.auth.dto;

import java.util.List;
import lombok.Data;

@Data
public class MealEntityDTO {

    private String meal;
    private List<String> pasti; // ogni mealType ha dei pasti

}
