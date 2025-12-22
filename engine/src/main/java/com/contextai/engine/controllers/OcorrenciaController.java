package com.contextai.engine.controllers;

import com.contextai.engine.models.OcorrenciaTecnica;
import com.contextai.engine.models.Setor; 
import com.contextai.engine.services.TriagemService;
import com.contextai.engine.services.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ocorrencias")
public class OcorrenciaController {

    @Autowired
    private TriagemService service;

    @Autowired
    private AiService aiService; 

    @PostMapping("/tecnica")
    public OcorrenciaTecnica criar(@RequestBody OcorrenciaTecnica ot) { 
        String classificacao = aiService.analisarRelato(ot.getRelato());

        Setor setorObjeto = new Setor();
        setorObjeto.setNome(classificacao); 
        setorObjeto.setPesoEstrategico(1);

        ot.setSetor(setorObjeto);
        return (OcorrenciaTecnica) service.salvar(ot);
    }
}