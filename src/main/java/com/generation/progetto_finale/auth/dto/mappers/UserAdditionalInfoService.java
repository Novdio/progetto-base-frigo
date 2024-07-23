package com.generation.progetto_finale.auth.dto.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.generation.progetto_finale.auth.dto.UserAdditionalInfoDTO;
import com.generation.progetto_finale.auth.model.UserAdditionalInfo;

@Service
public class UserAdditionalInfoService 
{
    private UserAdditionalInfoMapper mapper = UserAdditionalInfoMapper.ISTANCE;

    public UserAdditionalInfo toEntity(UserAdditionalInfoDTO dto) 
    {
        return mapper.toEntity(dto);
    }

    public List<UserAdditionalInfo> toEntity(List<UserAdditionalInfoDTO> dtos)
    {
        List<UserAdditionalInfo> res = new ArrayList<>();
        for (UserAdditionalInfoDTO dto : dtos) 
        {
            res.add(mapper.toEntity(dto));
        }
        return res;
    }
    public UserAdditionalInfoDTO toDTO(UserAdditionalInfo user) 
    {
        return mapper.toDTO(user);
    }

    public List<UserAdditionalInfoDTO> toDTO(List<UserAdditionalInfo> users)
    {
        List<UserAdditionalInfoDTO> res = new ArrayList<>();
        for (UserAdditionalInfo user : users) 
        {
            res.add(mapper.toDTO(user));
        }
        return res;
    }
}
