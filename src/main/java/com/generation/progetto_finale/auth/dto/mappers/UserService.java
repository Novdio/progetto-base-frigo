package com.generation.progetto_finale.auth.dto.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.generation.progetto_finale.auth.model.UserEntity;
import com.generation.progetto_finale.auth.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository uRepo;

    @Autowired
    private JavaMailSender mailSender;

    public UserEntity createUser(String username, String password, String confirmationKey, String email) {

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setConfirmationKey(confirmationKey);
        UserEntity savedUser = uRepo.save(user);

        sendConfirmationEmail(savedUser);

        return savedUser;

    }

    private void sendConfirmationEmail(UserEntity user) {

        String confirmationUrl = "http://localhost:8080/auth/confirm?username=" + user.getUsername() + "&key="
                + user.getConfirmationKey();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Conferma la tua registrazione");
        message.setText("Clicca sul link per confermare iil tuo account: " + confirmationUrl);

        mailSender.send(message);
    }
}