package com.generation.progetto_finale.a_i;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public AiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String makeApiRequest(String input) {
        String url = apiUrl + apiKey;
        String jsonString = "{"
    + "'contents': ["
    + "  {"
    + "    'role': 'user',"
    + "    'parts': ["
    + "      {"
    + "        'text': '"+input+"'"
    + "      }"
    + "    ]"
    + "  }"
    + "],"
    + "'generationConfig': {"
    + "  'temperature': 1,"
    + "  'topK': 64,"
    + "  'topP': 0.95,"
    + "  'maxOutputTokens': 8192,"
    + "  'responseMimeType': 'text/plain'"
    + "}"
    + "}";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> req = new HttpEntity<String>(jsonString,headers);

        ResponseEntity<String> resp = restTemplate.postForEntity(url, req, String.class);

        return resp.getBody();
    }
}

