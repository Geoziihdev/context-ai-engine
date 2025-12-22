package com.contextai.engine.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter
public abstract class Ocorrencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String relato;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "setor_id")
    private Setor setor;

    public abstract String calcularNivelUrgencia();
}