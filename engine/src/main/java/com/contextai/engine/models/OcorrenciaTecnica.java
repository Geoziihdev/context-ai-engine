package com.contextai.engine.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OcorrenciaTecnica extends Ocorrencia { 
    
    private String equipamento;
    private boolean sistemaForaDoAr;

    @Override
    public String calcularNivelUrgencia() {
        if (sistemaForaDoAr) {
            return "URGÊNCIA CRÍTICA: Sistema parado no equipamento " + equipamento;
        }
        return "Análise Técnica agendada para: " + equipamento;
    }
}

