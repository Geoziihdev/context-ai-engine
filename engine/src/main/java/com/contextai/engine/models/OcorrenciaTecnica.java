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

    public String getPrioridadeDefinida() {
        int peso = (getSetor() != null) ? getSetor().getPesoEstrategico() : 1;
        
        int score = sistemaForaDoAr ? (peso * 2) : peso;

        if (score >= 15) return "CRÍTICA (Score " + score + ")"; 
        if (score >= 10) return "ALTA (Score " + score + ")";    
        if (score >= 5)  return "MÉDIA (Score " + score + ")";   
        return "BAIXA (Score " + score + ")";
    }
}