package com.generation.progetto_finale.controller;

import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.progetto_finale.auth.dto.AlarmDTO;
import com.generation.progetto_finale.auth.model.Alarm;
import com.generation.progetto_finale.auth.model.UserAdditionalInfo;
import com.generation.progetto_finale.auth.repository.AlarmRepository;
import com.generation.progetto_finale.auth.repository.UserAdditionalInfoRepository;



@RestController
@RequestMapping("/alarm")
public class AlarmController 
{
    @Autowired 
    UserAdditionalInfoRepository uRepo;
    @Autowired
    AlarmRepository aRepo;
    
    @PostMapping()
    public Alarm postMethodName(@RequestBody AlarmDTO dto) 
    {
        Alarm alarm = new Alarm();
        alarm.setDays(dto.getDays());
        alarm.setTime(LocalTime.parse(dto.getTime()));
        Optional<UserAdditionalInfo> user = uRepo.findById(dto.getUInfoId());

        if (user.isEmpty()) 
            throw new RuntimeException("Utente non trovato");

        alarm.setUser(user.get());
        aRepo.save(alarm);
        return alarm;
    }
    
    @DeleteMapping("/{id}")
    public void deleteAlarm(@PathVariable int id)
    {
        aRepo.deleteById(id);
    }
}
