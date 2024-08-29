package com.generation.progetto_finale.controller;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.progetto_finale.auth.dto.MealEntityDTO;
import com.generation.progetto_finale.auth.dto.mappers.MealService;
import com.generation.progetto_finale.auth.model.CalendarEvent;
import com.generation.progetto_finale.auth.model.DayEntity;
import com.generation.progetto_finale.auth.model.MealEntity;
import com.generation.progetto_finale.auth.repository.CalendarEventRepository;
import com.generation.progetto_finale.auth.repository.DayRepository;
import com.generation.progetto_finale.auth.repository.MealRepository;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/meal")
public class MealController {

    @Autowired
    MealRepository mRepo;
    @Autowired
    MealService mServ;
    @Autowired
    CalendarEventRepository cRepo;
    @Autowired
    DayRepository dRepo;

    @GetMapping("{id}")
    public MealEntityDTO getMealEntityInfo(@PathVariable Integer id) {

        return mServ.toDTO(mRepo.findById(id).get());
    }

    @PostMapping("{id}")
    public MealEntityDTO formMeal(@RequestBody MealEntityDTO DTO, @PathVariable int id) {

        MealEntity mealDaCreare = mServ.toEntity(DTO);
        Optional<DayEntity> c = dRepo.findById(id);
        if (c.isEmpty())
            throw new RuntimeErrorException(new Error("shit"));

        mealDaCreare.setDay(c.get());;
        mealDaCreare = mRepo.save(mealDaCreare);

        return mServ.toDTO(mealDaCreare);
    }

    @PutMapping("{id}")
    public MealEntityDTO modifyMeal(@PathVariable int id, @RequestBody MealEntityDTO dto) {
        System.out.println(dto);
        MealEntity mealDaModificare = mServ.toEntity(dto);
        Optional<MealEntity> c = mRepo.findById(id);
        if (c.isEmpty())
            throw new RuntimeErrorException(new Error("Non posso modificare meal"));
        mealDaModificare.setDay(c.get().getDay());
        mealDaModificare.setId(id);
        mealDaModificare = mRepo.save(mealDaModificare);
        return mServ.toDTO(mealDaModificare);
    }

    @DeleteMapping("{id}")
    public void deleteMeal(@PathVariable int id) {

        Optional<MealEntity> mealDaEliminare = mRepo.findById(id);
        if (mealDaEliminare.isEmpty())
            throw new RuntimeErrorException(new Error("Non si può cancellare, non esiste"));
        mRepo.delete(mealDaEliminare.get());
    }

}
