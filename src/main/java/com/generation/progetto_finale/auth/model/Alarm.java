package com.generation.progetto_finale.auth.model;

import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Alarm 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ElementCollection 
    @CollectionTable(name = "alarm_days", joinColumns = @JoinColumn(name = "alarm_id")) 
    @Column(name = "day")
    private List<String> days;
    private LocalTime time;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userAdditionalInfo_id")
    private UserAdditionalInfo user;
}
