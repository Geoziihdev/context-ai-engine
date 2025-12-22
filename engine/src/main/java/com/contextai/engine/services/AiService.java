package com.contextai.engine.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class AiService {

    @Value("${groq.api.key}")
    private String apiKey;

    private final String URL = "https://api.groq.com/openai/v1/chat/completions";

    public String analisarRelato(String relato) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "llama-3.3-70b-versatile"); 
        
        String prompt = "Classifique o seguinte relato como 'TECNICO' ou 'FINANCEIRO' " +
                        "e responda apenas a palavra: " + relato;

        body.put("messages", new Object[]{
            Map.of("role", "user", "content", prompt)
        });

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(URL, entity, Map.class);
            return response.getBody().toString(); 
        } catch (Exception e) {
            return "Erro ao processar IA: " + e.getMessage();
        }
    }
}