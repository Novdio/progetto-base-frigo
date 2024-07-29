package com.generation.progetto_finale.auth.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CalendarEvent {

    // controllo anno bisestile, year non deve essere prima dell'inizio della dieta
    private LocalDate date;
    private boolean isChecked = false; // se all'inizio è checato
    private List<Meal> meal; // nome degli spuntini

    @ManyToOne
    @JoinColumn(name = "userAdditionalInfo_id")
    private UserAdditionalInfo u;

}
