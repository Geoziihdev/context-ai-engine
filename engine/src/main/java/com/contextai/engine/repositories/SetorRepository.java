package com.contextai.engine.repositories;

import com.contextai.engine.models.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SetorRepository extends JpaRepository<Setor, Long> {
    Optional<Setor> findFirstByNomeIgnoreCase(String nome);
}