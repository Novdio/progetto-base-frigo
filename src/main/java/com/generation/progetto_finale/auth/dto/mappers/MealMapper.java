package com.generation.progetto_finale.auth.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.generation.progetto_finale.auth.dto.MealEntityDTO;
import com.generation.progetto_finale.auth.model.MealEntity;

@Mapper
public interface MealMapper {

    public static final MealMapper ISTANCE = Mappers.getMapper(MealMapper.class);

    MealEntityDTO toDTO(MealEntity m);

    @Mapping(target = "calendar", ignore = true)
    MealEntity toEntity(MealEntityDTO dto);

}