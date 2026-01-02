package com.contextai.engine.controllers;

import com.contextai.engine.models.*;
import com.contextai.engine.services.TriagemService;
import com.contextai.engine.services.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional; 

@RestController
@RequestMapping("/api/ocorrencias")
public class OcorrenciaController {

    @Autowired
    private TriagemService service;

    @Autowired
    private AiService aiService;

    @PostMapping("/tecnica")
    public Ocorrencia criarTecnica(@RequestBody OcorrenciaTecnica ot) {
        vincularSetorIA(ot);
        return service.salvar(ot);
    }

    @PostMapping("/financeira")
    public Ocorrencia criarFinanceira(@RequestBody OcorrenciaFinanceira of) {
        vincularSetorIA(of);
        return service.salvar(of);
    }

    @PostMapping("/rh")
    public Ocorrencia criarRH(@RequestBody OcorrenciaRH orh) {
        vincularSetorIA(orh);
        return service.salvar(orh);
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

    private void vincularSetorIA(Ocorrencia o) {
        String nomeSetorIA = aiService.analisarRelato(o.getRelato());
        Setor setorExistente = service.buscarSetorPorNome(nomeSetorIA);

        if (setorExistente != null) {
            o.setSetor(setorExistente);
        } else {
            Setor setorNovo = new Setor();
            setorNovo.setNome(nomeSetorIA);
            setorNovo.setPesoEstrategico(1); 
            o.setSetor(setorNovo);
        }
    }
}