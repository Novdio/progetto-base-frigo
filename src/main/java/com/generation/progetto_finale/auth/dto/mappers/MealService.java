package com.generation.progetto_finale.auth.dto.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.generation.progetto_finale.auth.dto.MealEntityDTO;
import com.generation.progetto_finale.auth.dto.UserAdditionalInfoDTO;
import com.generation.progetto_finale.auth.model.MealEntity;
import com.generation.progetto_finale.auth.model.UserAdditionalInfo;

@Service
public class MealService {

    private MealMapper mapper = MealMapper.ISTANCE;

    public MealEntity toEntity(MealEntityDTO dto) {

        return mapper.toEntity(dto);
    }

    public List<MealEntity> toEntity(List<MealEntityDTO> dtos) {

        List<MealEntity> res = new ArrayList<>();
        for (MealEntityDTO dto : dtos) {

            res.add(mapper.toEntity(dto));
        }

        return res;
    }

    public MealEntityDTO toDTO(MealEntity meal) {
        return mapper.toDTO((meal));
    }

    public List<MealEntityDTO> toDTO(List<MealEntity> meals) {

        List<MealEntityDTO> res = new ArrayList<>();
        for (MealEntity meal : meals) {
            res.add(mapper.toDTO(meal));
        }
        return res;
    }
}
