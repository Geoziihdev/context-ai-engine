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
        if (valorEmRisco != null && valorEmRisco > 10000) {
            return "URGÊNCIA ALTA: Risco financeiro elevado de R$ " + valorEmRisco;
        }
        return "Análise Financeira padrão para transação de " + tipoTransacao;
    }

    public String getPrioridadeDefinida() {
        
        int pesoSetor = (getSetor() != null) ? getSetor().getPesoEstrategico() : 1;
        
        int multiplicador = 1;
        
        if (valorEmRisco != null && valorEmRisco > 10000) {
            multiplicador = 2;
        } else if (tipoTransacao != null && (tipoTransacao.equalsIgnoreCase("Folha de Pagamento") || tipoTransacao.equalsIgnoreCase("Impostos"))) {
            multiplicador = 2;
        }

        int score = pesoSetor * multiplicador;

        if (score >= 15) return "CRÍTICA FINANCEIRA (Score " + score + ")";
        if (score >= 10) return "ALTA (Score " + score + ")";
        return "NORMAL (Score " + score + ")";
    }
}