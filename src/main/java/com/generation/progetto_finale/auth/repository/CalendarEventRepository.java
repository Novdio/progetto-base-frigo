package com.generation.progetto_finale.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.progetto_finale.auth.model.CalendarEvent;

public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Integer> {

}
