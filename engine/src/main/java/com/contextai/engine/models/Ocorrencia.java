package com.contextai.engine.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ocorrencia")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) 
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@Getter @Setter
public abstract class Ocorrencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "relato_usuario")
    private String relato;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "setor_id")
    private Setor setor;

    @Column(name = "prioridade_definida")
    private String prioridadeDefinida;

    public abstract String calcularNivelUrgencia();
}