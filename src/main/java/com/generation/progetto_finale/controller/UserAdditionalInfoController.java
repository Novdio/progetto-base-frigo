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



@RestController
@RequestMapping("/info")
public class UserAdditionalInfoController 
{
    @Autowired
    UserAdditionalInfoRepository infoRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserAdditionalInfoService infoService;


    @GetMapping("{id}")
    public UserAdditionalInfoDTO getUserInfo(@PathVariable Integer id) 
    {
        return infoService.toDTO(infoRepo.findById(id).get());
    }
    

    /**
     * da chiedere a Stefano il rapporto con user e userid.
     * 
     */
    @PostMapping("{id}")
    public UserAdditionalInfoDTO formUserInfo(@RequestBody UserAdditionalInfoDTO DTO,@PathVariable int id) 
    {
        UserAdditionalInfo infoDaCreare = infoService.toEntity(DTO);
        Optional<UserEntity> u = userRepository.findById(id);
        if (u.isEmpty()) 
            throw new UserNotFoundException("User non trovato");
        //set
        infoDaCreare.setUser(u.get());
        //save
        infoDaCreare=infoRepo.save(infoDaCreare);
        
        return infoService.toDTO(infoDaCreare);
    }

    @DeleteMapping("{id}")
    public void deleteUserAdditionalInfo(@PathVariable int id)
    {
        Optional<UserAdditionalInfo> infoDaEliminare = infoRepo.findById(id);
        if (infoDaEliminare.isEmpty()) 
            throw new UserNotFoundException("Informazione User non trovate");
        infoRepo.delete(infoDaEliminare.get());
    }

    @PutMapping("{id}")
    public UserAdditionalInfoDTO putMethodName(@PathVariable int id, @RequestBody UserAdditionalInfoDTO dto) 
    {
        UserAdditionalInfo infoDaModificare = infoService.toEntity(dto);
        Optional<UserEntity> u = userRepository.findById(id);
        if (u.isEmpty()) 
            throw new UserNotFoundException("User non trovato");
        //set
        infoDaModificare.setUser(u.get());
        //save
        infoDaModificare=infoRepo.save(infoDaModificare);
        return infoService.toDTO(infoDaModificare);
    }
    


    // @Autowired
    // private UserAdditionalInfoService userAdditionalInfoService;

    // @GetMapping("/{id}")
    // public ResponseEntity<UserAdditionalInfoDTO> getUserAdditionalInfoById(@PathVariable int id) {
    //     UserAdditionalInfoDTO userAdditionalInfoDTO = userAdditionalInfoService.getUserAdditionalInfoById(id);
    //     return new ResponseEntity<>(userAdditionalInfoDTO, HttpStatus.OK);
    // }

    // @GetMapping
    // public ResponseEntity<List<UserAdditionalInfoDTO>> getAllUserAdditionalInfos() {
    //     List<UserAdditionalInfoDTO> userAdditionalInfos = userAdditionalInfoService.getAllUserAdditionalInfos();
    //     return new ResponseEntity<>(userAdditionalInfos, HttpStatus.OK);
    // }

    // @PostMapping
    // public ResponseEntity<UserAdditionalInfoDTO> createUserAdditionalInfo(@RequestBody UserAdditionalInfoDTO userAdditionalInfoDTO) {
    //     UserAdditionalInfoDTO createdUserAdditionalInfo = userAdditionalInfoService.createUserAdditionalInfo(userAdditionalInfoDTO);
    //     return new ResponseEntity<>(createdUserAdditionalInfo, HttpStatus.CREATED);
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<UserAdditionalInfoDTO> updateUserAdditionalInfo(@PathVariable int id, @RequestBody UserAdditionalInfoDTO userAdditionalInfoDTO) {
    //     UserAdditionalInfoDTO updatedUserAdditionalInfo = userAdditionalInfoService.updateUserAdditionalInfo(id, userAdditionalInfoDTO);
    //     return new ResponseEntity<>(updatedUserAdditionalInfo, HttpStatus.OK);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteUserAdditionalInfo(@PathVariable int id) {
    //     userAdditionalInfoService.deleteUserAdditionalInfo(id);
    //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }
}
