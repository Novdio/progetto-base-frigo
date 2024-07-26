package com.generation.progetto_finale.auth.dto.mappers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.generation.progetto_finale.auth.model.Role;
import com.generation.progetto_finale.auth.model.UserEntity;
import com.generation.progetto_finale.auth.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserService {

    @Autowired
    private UserRepository uRepo;

    @Autowired
    private JavaMailSender mailSender;

    public UserEntity createUser(String username, String password, String email, List<Role> roles) {

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRoles(roles);
        user.setConfirmationKey(UserEntity.generateConfirmationKey());
        UserEntity savedUser = uRepo.save(user);

        try {
            sendConfirmationEmail(savedUser);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return savedUser;

    }

    public void sendConfirmationEmail(UserEntity user) throws MessagingException {

        String confirmationUrl = "http://localhost:8080/api/auth/confirmUser?username=" + user.getUsername() + "&key="
                + user.getConfirmationKey();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(user.getEmail());
        helper.setSubject("Conferma la tua registrazione");
        helper.setText( "<button"+" href="+confirmationUrl+">Confirm email</button>",
                        true);

        
        
        mailSender.send(message);
    }

    public boolean verifyConfirmationEmail(String username, String key) {

        Optional<UserEntity> user = uRepo.findByUsernameAndConfirmationKey(username, key);
        if (user.isPresent()) {

            UserEntity u = user.get();
            u.setConfirmed(true);
            u.setConfirmationKey(null);
            uRepo.save(u);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(u.getEmail());
            message.setSubject("Conferma la tua registrazione");
            message.setText("YIPPI");

            mailSender.send(message);

            return true;
        }

        return false;
    }


}