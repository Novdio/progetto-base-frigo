package com.generation.progetto_finale.notification;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.generation.progetto_finale.auth.model.Alarm;
import com.generation.progetto_finale.auth.repository.AlarmRepository;

@Component
public class AlarmSender {
    @Autowired
    AlarmRepository aRepo;
    @Autowired
    WebSocketService wServ;

    @Scheduled(cron = "0 */5 * * * * ")
    public void sendAlarm() {
        LocalDateTime now = LocalDateTime.now();

        // Crea un formatter per il giorno della settimana in italiano
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.ITALIAN);

        // Crea un formatter per l'ora in formato 24 ore
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // Formatta il giorno della settimana
        String dayOfWeek = now.format(dayFormatter);
        dayOfWeek = dayOfWeek.substring(0, 1).toUpperCase() + dayOfWeek.substring(1).toLowerCase();

        // Formatta l'ora
        String time = now.format(timeFormatter);

        List<Alarm> toSend = aRepo.findByTimeAndDaysContains(LocalTime.parse(time), dayOfWeek);

        for (Alarm alarm : toSend) 
        {
            wServ.sendAlarm("Sveglia delle "+time, alarm.getUser().getUser().getId());    
            System.out.println("Sveglia delle "+time+" "+ alarm.getUser().getUser().getId());
        }
    }
}
