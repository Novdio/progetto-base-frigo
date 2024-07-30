package com.generation.progetto_finale.auth.dto.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.generation.progetto_finale.auth.dto.CalendarEventDTO;
import com.generation.progetto_finale.auth.model.CalendarEvent;

@Service
public class CalendarEventService {
    private CalendarEventMapper mapper = CalendarEventMapper.ISTANCE;

    public CalendarEvent toEntity(CalendarEventDTO dto) {
        return mapper.toEntity(dto);
    }

    public List<CalendarEvent> toEntity(List<CalendarEventDTO> dtos) {
        List<CalendarEvent> res = new ArrayList<>();
        for (CalendarEventDTO dto : dtos) {
            res.add(mapper.toEntity(dto));
        }
        return res;
    }

    public CalendarEventDTO toDTO(CalendarEvent user) {
        return mapper.toDTO(user);
    }

    public List<CalendarEventDTO> toDTO(List<CalendarEvent> users) {
        List<CalendarEventDTO> res = new ArrayList<>();
        for (CalendarEvent user : users) {
            res.add(mapper.toDTO(user));
        }
        return res;
    }
}
