package com.generation.progetto_finale.auth.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.generation.progetto_finale.auth.dto.CalendarEventDTO;
import com.generation.progetto_finale.auth.model.CalendarEvent;

@Mapper
public interface CalendarEventMapper {

    public static final CalendarEventMapper ISTANCE = Mappers.getMapper(CalendarEventMapper.class);

    @Mapping(target = "date", source = "date", dateFormat = "yyyy-MM-dd")
    CalendarEventDTO toDTO(CalendarEvent u);

    @Mapping(target = "user", ignore = true)
    CalendarEvent toEntity(CalendarEventDTO dto);

}
