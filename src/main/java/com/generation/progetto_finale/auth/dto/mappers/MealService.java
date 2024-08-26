package com.generation.progetto_finale.auth.dto.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.generation.progetto_finale.auth.dto.MealEntityDTO;
import com.generation.progetto_finale.auth.model.MealEntity;

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

    public MealEntityDTO toDTO(MealEntity user) {
        return mapper.toDTO((user));
    }

    public List<MealEntityDTO> toDTO(List<MealEntity> users) {

        List<MealEntityDTO> res = new ArrayList<>();
        for (MealEntity user : users) {
            res.add(mapper.toDTO(user));
        }
        return res;
    }
}
