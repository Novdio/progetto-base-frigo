package com.generation.progetto_finale.auth.dto.mappers;

import java.util.ArrayList;
import java.util.List;

import com.generation.progetto_finale.auth.dto.FridgeDTO;
import com.generation.progetto_finale.auth.model.Fridge;

public class FridgeService {

    private FridgeMapper mapper = FridgeMapper.ISTANCE;

    public Fridge toEntity(FridgeDTO dto) {
        return mapper.toEntity(dto);
    }

    public List<Fridge> toEntity(List<FridgeDTO> dtos) {
        List<Fridge> res = new ArrayList<>();
        for (FridgeDTO dto : dtos) {
            res.add(mapper.toEntity(dto));
        }
        return res;
    }

    public FridgeDTO toDTO(Fridge user) {
        return mapper.toDTO(user);
    }

    public List<FridgeDTO> toDTO(List<Fridge> users) {
        List<FridgeDTO> res = new ArrayList<>();
        for (Fridge user : users) {
            res.add(mapper.toDTO(user));
        }
        return res;
    }
}
