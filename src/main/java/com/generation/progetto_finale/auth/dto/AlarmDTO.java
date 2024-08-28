package com.generation.progetto_finale.auth.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class AlarmDTO 
{
    private Integer uInfoId;
    private List<String> days = new ArrayList<>();
    private String time;
}
