package com.generation.progetto_finale;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.generation.progetto_finale.auth.model.CalendarEvent;
import com.generation.progetto_finale.auth.model.DayEntity;
import com.generation.progetto_finale.auth.model.Meal;
import com.generation.progetto_finale.auth.model.MealEntity;
import com.generation.progetto_finale.auth.model.UserAdditionalInfo;
import com.generation.progetto_finale.auth.model.UserEntity;
import com.generation.progetto_finale.auth.model.WeekDay;
import com.generation.progetto_finale.auth.repository.CalendarEventRepository;
import com.generation.progetto_finale.auth.repository.UserAdditionalInfoRepository;
import com.generation.progetto_finale.auth.repository.UserRepository;

@Component
public class ScheduledCalendar {

    @Autowired
    UserAdditionalInfoRepository uARepo;

    @Autowired
    CalendarEventRepository calendarEventRepository;;

    //obiettivo di questo metodo è creare per ogni utente un calendar event con i 7 giorni della settimana, ognuno di essi con tutti i meal unchecked
        //1. scorrere tutti gli utenti
        //2. creare calendario nuovo
        //3. creare lista di 7 day
        //4. scorrere la lista di day
        //5. per ognuno creare 5 pasti
        //6. salvare tutto
    @Scheduled(cron = "0 1 0 * * MON")
    // @Scheduled(fixedRate = 30000)
    public void createCalendarForTheWeek() {
        // Recupera tutti gli utenti
        List<UserAdditionalInfo> allUsers = uARepo.findAll();

        // Per ogni utente, crea un evento di calendario per la settimana
        for (UserAdditionalInfo user : allUsers) {
            // Crea un nuovo evento di calendario
            CalendarEvent calendarEvent = new CalendarEvent();
            calendarEvent.setUser(user);
            calendarEvent.setDate(LocalDate.now()); // Imposta la data al lunedÃ¬ corrente

            // Crea i 7 giorni della settimana
            List<DayEntity> days = new ArrayList<>();
            for (WeekDay weekDay : WeekDay.values()) {
                DayEntity dayEntity = new DayEntity();
                dayEntity.setWeekDay(weekDay);
                dayEntity.setCalendar(calendarEvent);

                // Crea i pasti per il giorno corrente
                List<MealEntity> meals = new ArrayList<>();
                for (Meal mealType : Meal.values()) {
                    MealEntity mealEntity = new MealEntity();
                    mealEntity.setMeal(mealType);
                    mealEntity.setChecked(false); // Imposta il pasto come non selezionato
                    mealEntity.setDay(dayEntity);

                    // Aggiungi la lista dei pasti (se necessario)
                    // mealEntity.setPasti(...);

                    meals.add(mealEntity);
                }

                // Associa i pasti al giorno
                dayEntity.setMeals(meals);

                // Aggiungi il giorno alla lista dei giorni
                days.add(dayEntity);
            }

            // Associa i giorni all'evento di calendario
            calendarEvent.setDays(days);

            // Salva l'evento di calendario nel repository
            calendarEventRepository.save(calendarEvent);
            System.out.println("fatto!!");
        }
    }
}