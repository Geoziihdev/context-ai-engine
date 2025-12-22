package com.contextai.engine.services;

import com.contextai.engine.models.Ocorrencia;
import com.contextai.engine.repositories.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TriagemService {
    @Autowired
    private OcorrenciaRepository repository;

    public Ocorrencia salvar(Ocorrencia o) {
        System.out.println(o.calcularNivelUrgencia()); 
        return repository.save(o);
    }
}