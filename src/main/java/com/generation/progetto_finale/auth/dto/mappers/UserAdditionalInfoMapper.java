package com.generation.progetto_finale.auth.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.generation.progetto_finale.auth.dto.UserAdditionalInfoDTO;
import com.generation.progetto_finale.auth.model.UserAdditionalInfo;


@Mapper
public interface UserAdditionalInfoMapper 
{
    public static final UserAdditionalInfoMapper ISTANCE = Mappers.getMapper(UserAdditionalInfoMapper.class);

    UserAdditionalInfoDTO toDTO(UserAdditionalInfo u);

    @Mapping(target="user",ignore = true)
    UserAdditionalInfo toEntity(UserAdditionalInfoDTO dto);
    
}
