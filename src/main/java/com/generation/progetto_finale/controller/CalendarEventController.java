package com.generation.progetto_finale.controller;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.progetto_finale.auth.dto.CalendarEventDTO;
import com.generation.progetto_finale.auth.dto.mappers.CalendarEventService;
import com.generation.progetto_finale.auth.model.CalendarEvent;
import com.generation.progetto_finale.auth.model.UserAdditionalInfo;
import com.generation.progetto_finale.auth.repository.CalendarEventRepository;
import com.generation.progetto_finale.auth.repository.UserAdditionalInfoRepository;
import com.generation.progetto_finale.auth.repository.UserRepository;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/calendar")
public class CalendarEventController {

    @Autowired
    CalendarEventRepository cRepo;
    @Autowired
    CalendarEventService cServ;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserAdditionalInfoRepository uRepo;

    @GetMapping("{id}")
    public CalendarEventDTO getCalendarEventInfo(@PathVariable Integer id) {
        return cServ.toDTO(uRepo.findById(id).get().getCalendars().get(0));
    }

    @PostMapping("{id}")
    public CalendarEventDTO formCalendarEvent(@RequestBody CalendarEventDTO DTO, @PathVariable int id) {

        CalendarEvent calendarDaCreare = cServ.toEntity(DTO);
        Optional<UserAdditionalInfo> u = uRepo.findById(id);
        if (u.isEmpty())
            throw new RuntimeErrorException(new Error("Non esiste"));

        calendarDaCreare.setUser(u.get());
        calendarDaCreare = cRepo.save(calendarDaCreare);

        return cServ.toDTO(calendarDaCreare);
    }

    @PutMapping("{id}")
    public CalendarEventDTO modifyCalendarEvent(@PathVariable int id, @RequestBody CalendarEventDTO dto) {

        CalendarEvent calendarDaModificare = cServ.toEntity(dto);
        Optional<UserAdditionalInfo> u = uRepo.findById(id);

        if (u.isEmpty())
            throw new RuntimeErrorException(new Error("Non posso modificare, è vuoto"));

        calendarDaModificare.setUser(u.get());
        calendarDaModificare = cRepo.save(calendarDaModificare);
        return cServ.toDTO(calendarDaModificare);
    }

    @DeleteMapping("{id}")
    public void deleteCalendarEvent(@PathVariable int id) {

        Optional<CalendarEvent> calendarDaEliminare = cRepo.findById(id);
        if (calendarDaEliminare.isEmpty())
            throw new RuntimeErrorException(new Error("Non si puo' cancellare, non esiste"));
        cRepo.delete(calendarDaEliminare.get());
    }

}
