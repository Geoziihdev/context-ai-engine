package com.contextai.engine.controllers;

import com.contextai.engine.models.OcorrenciaTecnica;
import com.contextai.engine.services.TriagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ocorrencias")
public class OcorrenciaController {
    @Autowired
    private TriagemService service;

    @PostMapping("/tecnica")
    public void criar(@RequestBody OcorrenciaTecnica ot) {
        service.salvar(ot);
    }
}