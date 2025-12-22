package com.contextai.engine.controllers;

import com.contextai.engine.services.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    @Autowired
    private AiService aiService;

    @GetMapping("/analisar")
    public String testarIA(@RequestParam String relato) {
        return aiService.analisarRelato(relato);
    }
}