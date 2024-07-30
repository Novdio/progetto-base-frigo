package com.generation.progetto_finale.controller;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generation.progetto_finale.auth.dto.AuthResponseDto;
import com.generation.progetto_finale.auth.dto.CredentialsDto;
import com.generation.progetto_finale.auth.dto.mappers.UserService;
import com.generation.progetto_finale.auth.model.Role;
import com.generation.progetto_finale.auth.model.UserEntity;
import com.generation.progetto_finale.auth.repository.RoleRepository;
import com.generation.progetto_finale.auth.repository.UserRepository;
import com.generation.progetto_finale.auth.security.JWTGenerator;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTGenerator jwtGenerator;
    @Autowired
    private UserService uService;

    @PostMapping("login")
    public AuthResponseDto login(@RequestBody CredentialsDto loginDto) {

        Authentication user = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(user);

        String token = jwtGenerator.generateToken(user);

        return new AuthResponseDto(token);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody CredentialsDto registerDto) {

        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return new ResponseEntity<>("Email is taken!", HttpStatus.BAD_REQUEST);
        }
        Role roles = roleRepository.findByName("USER").get();

        UserEntity user = uService.createUser(
                registerDto.getUsername(),
                passwordEncoder.encode((registerDto.getPassword())),
                registerDto.getEmail(),
                Collections.singletonList(roles));

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    @GetMapping("/confirmUser")
    public void confirmUser(@RequestParam("username") String username,
            @RequestParam("key") String confirmationKey, HttpServletResponse resp) throws IOException {

        String token = jwtGenerator.generateFirstToken(username);
        System.out.println(token);
        if (uService.verifyConfirmationEmail(username, confirmationKey))
            resp.sendRedirect("http://localhost:4200/confirmation/" + token);

    }

}
