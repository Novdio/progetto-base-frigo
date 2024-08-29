package com.generation.progetto_finale.auth.dto;

import lombok.Data;

@Data
public class IaRequestDTO {

    private String question;

    public IaRequestDTO() {
    }

    public IaRequestDTO(String question) {

        this.question = question;
    }
}
