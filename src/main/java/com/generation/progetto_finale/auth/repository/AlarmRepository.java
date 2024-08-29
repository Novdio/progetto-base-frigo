package com.generation.progetto_finale.auth.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.progetto_finale.auth.model.Alarm;

public interface AlarmRepository extends JpaRepository<Alarm,Integer>
{
    List<Alarm> findByTimeAndDaysContains(LocalTime time, String day);
}
