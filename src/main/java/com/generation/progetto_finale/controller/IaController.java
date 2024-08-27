package com.generation.progetto_finale.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.generation.progetto_finale.auth.dto.IaRequestDTO;

@RestController
public class IaController {

        @PostMapping("/fairicetta")
        public String faiRicetta(@RequestBody IaRequestDTO dto) throws JsonProcessingException {

                System.out.println(dto);
                String msg = "Scrivimi una ricetta con tutti i passaggi avente come ingredienti: " + dto.getQuestion();
                String result = callIa(msg);
                return result;
        }

        @GetMapping("/consigliaricetta")
        public List<String> consigliaRicetta() throws JsonProcessingException {

                List<String> res = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                        String msgRicette = "Consigliami 1 ricetta stravagante";
                        String result = callIa(msgRicette);
                        res.add(result);

                }
                return res;
        }

        public String callIa(String msgContent) throws JsonProcessingException {
                WebClient client = WebClient.create("http://localhost:1234");
                // Costruzione del body della richiesta (simile all'esempio precedente)
                String body = "{\"model\": \"LM Studio Community/Meta-Llama-3-8B-Instruct-GGUF\", \"messages\": [{\"role\": \"user\", \"content\": \""
                                + msgContent + "\"}], \"temperature\": 0.7, \"max_tokens\": -1}"; // modificare
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
