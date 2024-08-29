package com.generation.progetto_finale.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.progetto_finale.auth.dto.UserAdditionalInfoDTO;
import com.generation.progetto_finale.auth.dto.mappers.UserAdditionalInfoService;
import com.generation.progetto_finale.auth.model.UserAdditionalInfo;
import com.generation.progetto_finale.auth.model.UserEntity;
import com.generation.progetto_finale.auth.repository.UserAdditionalInfoRepository;
import com.generation.progetto_finale.auth.repository.UserRepository;
import com.generation.progetto_finale.controller.exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/info")
public class UserAdditionalInfoController {
    @Autowired
    UserAdditionalInfoRepository infoRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserAdditionalInfoService infoService;

    @GetMapping("{id}")
    public UserAdditionalInfoDTO getUserInfo(@PathVariable Integer id) {

        return infoService.toDTO(infoRepo.findById(id).get());
    }

    @PostMapping("{id}")
    public UserAdditionalInfoDTO formUserInfo(@RequestBody UserAdditionalInfoDTO DTO, @PathVariable int id) {

        UserAdditionalInfo infoDaCreare = infoService.toEntity(DTO);
        Optional<UserEntity> u = userRepository.findById(id);
        if (u.isEmpty())
            throw new UserNotFoundException("User non trovato");
        // set
        infoDaCreare.setUser(u.get());
        // save
        infoDaCreare = infoRepo.save(infoDaCreare);

        return infoService.toDTO(infoDaCreare);
    }

    @DeleteMapping("{id}")
    public void deleteUserAdditionalInfo(@PathVariable int id) {

        Optional<UserAdditionalInfo> infoDaEliminare = infoRepo.findById(id);
        if (infoDaEliminare.isEmpty())
            throw new UserNotFoundException("Informazione User non trovate");
        infoRepo.delete(infoDaEliminare.get());
    }

    @PutMapping("{id}")
    public UserAdditionalInfoDTO modifyUserAdditionalInfo(@PathVariable int id,
            @RequestBody UserAdditionalInfoDTO dto) {

        Optional<UserAdditionalInfo> oldInfo = infoRepo.findById(id);
        UserAdditionalInfo infoDaModificare = infoService.toEntity(dto);
        if (oldInfo.isEmpty())
            throw new UserNotFoundException("Informazione User non trovate");
        infoDaModificare.setId(oldInfo.get().getId());
        infoDaModificare.setUser(oldInfo.get().getUser());

        infoDaModificare = infoRepo.save(infoDaModificare);
        return infoService.toDTO(infoDaModificare);
    }
    

}
