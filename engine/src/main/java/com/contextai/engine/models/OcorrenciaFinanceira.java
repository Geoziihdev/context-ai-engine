package com.contextai.engine.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OcorrenciaFinanceira extends Ocorrencia { 
    
    private Double valorEmRisco;
    private String tipoTransacao; 

    @Override
    public String calcularNivelUrgencia() {
        if (valorEmRisco > 10000) {
            return "URGÊNCIA ALTA: Risco financeiro elevado de R$ " + valorEmRisco;
        }
        return "Análise Financeira padrão para transação de " + tipoTransacao;
    }
}