package com.generation.progetto_finale.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class IaController {

    @GetMapping("/prova")
    public String getProva(@RequestParam String nome) throws JsonProcessingException {
        WebClient client = WebClient.create("http://localhost:1234");
        String msg = "Fammi una ricetta con: ";
        // Costruzione del body della richiesta (simile all'esempio precedente)
        String body = "{\"model\": \"LM Studio Community/Meta-Llama-3-8B-Instruct-GGUF\", \"messages\": [{\"role\": \"user\", \"content\": \""
                + msg + "\"}], \"temperature\": 0.7, \"max_tokens\": 100}"; // modificare
        // messaggio
        // per
        // quello
        // che
        // ci
        // serve

        // Effettuare la richiesta e ottenere la risposta
        String response = client.post()
                .uri("/v1/chat/completions")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Blocco per ottenere la risposta (opzionale)

        // Parsing della risposta JSON
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);

        // Estrazione del content dal message
        String content = jsonNode
                .path("choices")
                .get(0)
                .path("message")
                .path("content")
                .asText();

        // Stampa del risultato
        return content;
    }
}
