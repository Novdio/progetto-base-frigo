package com.generation.progetto_finale.a_i;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class GeminiController {
    @Autowired
    private AiService aiService;

    @PostMapping("/process")
    public String processInput(@RequestBody String input) {
        
        return aiService.makeApiRequest(input);
    }
    
}
