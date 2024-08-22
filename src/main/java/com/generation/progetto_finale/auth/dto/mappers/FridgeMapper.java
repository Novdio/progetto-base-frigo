package com.generation.progetto_finale.auth.dto.mappers;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.generation.progetto_finale.auth.dto.FridgeDTO;
import com.generation.progetto_finale.auth.model.Fridge;

public interface FridgeMapper {

    public static final FridgeMapper ISTANCE = Mappers.getMapper(FridgeMapper.class);

    FridgeDTO toDTO(Fridge f);

    @Mapping(target = "user", ignore = true)
    Fridge toEntity(FridgeDTO dto);
}
