package com.generation.progetto_finale.auth.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private Integer id;
    private String email;
    private String accessToken;
    private String tokenType = "Bearer ";

    public AuthResponseDto(String accessToken, String email,Integer id) 
    {
        this.id = id;
        this.email = email;
        this.accessToken = accessToken;
    }
}
