package com.contextai.engine.services;

import com.contextai.engine.models.Ocorrencia;
import com.contextai.engine.models.Setor;
import com.contextai.engine.repositories.OcorrenciaRepository;
import com.contextai.engine.repositories.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class TriagemService {

    @Autowired
    private OcorrenciaRepository repository;

    @Autowired
    private SetorRepository setorRepository;

    @Transactional
    public Ocorrencia salvar(Ocorrencia o) {
        if (o.getSetor() != null) {
            String nomeSetor = o.getSetor().getNome();
            Setor setor = setorRepository.findFirstByNomeIgnoreCase(nomeSetor)
                    .orElseGet(() -> {
                        Setor novoSetor = o.getSetor();
                        return setorRepository.save(novoSetor);
                    });
            o.setSetor(setor);
        }

        String urgenciaIA = o.calcularNivelUrgencia();
        o.setPrioridadeDefinida(urgenciaIA);

        return repository.saveAndFlush(o);
    }

    public Setor buscarSetorPorNome(String nome) {
        return setorRepository.findFirstByNomeIgnoreCase(nome)
                               .orElse(null);
    }

    public Optional<Ocorrencia> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Ocorrencia> listarTodas() {
        return repository.findAll();
    }
}