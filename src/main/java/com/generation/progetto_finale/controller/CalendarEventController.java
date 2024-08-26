package com.generation.progetto_finale.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.progetto_finale.auth.dto.CalendarEventDTO;
import com.generation.progetto_finale.auth.dto.mappers.CalendarEventService;
import com.generation.progetto_finale.auth.model.CalendarEvent;
import com.generation.progetto_finale.auth.model.UserAdditionalInfo;
import com.generation.progetto_finale.auth.repository.CalendarEventRepository;
import com.generation.progetto_finale.auth.repository.UserAdditionalInfoRepository;
import com.generation.progetto_finale.auth.repository.UserRepository;

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
        return cRepo.findById(id)
                .map(cServ::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
    }

    @PostMapping
    public CalendarEventDTO formCalendarEvent(@RequestBody CalendarEventDTO DTO) {

        CalendarEvent calendarDaCreare = cServ.toEntity(DTO);
        Optional<UserAdditionalInfo> u = uRepo.findById(DTO.getId());

        if (u.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");

        calendarDaCreare.setUser(u.get());
        calendarDaCreare = cRepo.save(calendarDaCreare);

        return cServ.toDTO(calendarDaCreare);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCalendarEvent(@PathVariable int id) {
        Optional<CalendarEvent> calendarDaEliminare = cRepo.findById(id);
        if (calendarDaEliminare.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");

        cRepo.delete(calendarDaEliminare.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public CalendarEventDTO modifyCalendarEvent(@PathVariable int id, @RequestBody CalendarEventDTO dto) {
        CalendarEvent calendarDaModificare = cServ.toEntity(dto);
        Optional<UserAdditionalInfo> u = uRepo.findById(id);

        if (u.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");

        calendarDaModificare.setUser(u.get());
        calendarDaModificare = cRepo.save(calendarDaModificare);
        return cServ.toDTO(calendarDaModificare);
    }
}
