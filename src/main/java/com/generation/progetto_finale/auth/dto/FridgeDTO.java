package com.generation.progetto_finale.auth.dto;

import java.util.List;

import lombok.Data;

@Data
public class FridgeDTO {

    private List<String> ricetta;
    private List<String> ingredienti;
    private List<String> diet;
}
