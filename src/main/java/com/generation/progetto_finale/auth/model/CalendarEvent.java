package com.generation.progetto_finale.auth.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "calendar_event")
public class CalendarEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate date;
    private boolean checked = false;

    @ElementCollection(targetClass = Meal.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "meals", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "meals")
    private List<Meal> meals; // nome degli spuntini

    @ManyToOne
    @JoinColumn(name = "userAdditionalInfo_id")
    private UserAdditionalInfo user;

}
