package com.contextai.engine.controllers;

import com.contextai.engine.models.Ocorrencia;
import com.contextai.engine.models.OcorrenciaTecnica;
import com.contextai.engine.models.Setor; 
import com.contextai.engine.services.TriagemService;
import com.contextai.engine.services.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ocorrencias")
public class OcorrenciaController {

    @Autowired
    private TriagemService service;

    @Autowired
    private AiService aiService; 

    @PostMapping("/tecnica")
    public OcorrenciaTecnica criar(@RequestBody OcorrenciaTecnica ot) { 
        
        String nomeSetorIA = aiService.analisarRelato(ot.getRelato());
        
        Setor setorExistente = service.buscarSetorPorNome(nomeSetorIA);

        if (setorExistente != null) {
            ot.setSetor(setorExistente);
        } else {
            Setor setorNovo = new Setor();
            setorNovo.setNome(nomeSetorIA);
            setorNovo.setPesoEstrategico(1); 
            ot.setSetor(setorNovo);
        }

        return (OcorrenciaTecnica) service.salvar(ot);
    }

    @GetMapping
    public List<Ocorrencia> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ocorrencia> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}