package com.contextai.engine.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OcorrenciaRH extends Ocorrencia { 
    
    private String cpfColaborador;
    private String categoriaSolicitacao; 

    @Override
    public String calcularNivelUrgencia() {
        if (categoriaSolicitacao != null && (categoriaSolicitacao.equalsIgnoreCase("Demissão") || categoriaSolicitacao.equalsIgnoreCase("Folha de Pagamento"))) {
            return "URGÊNCIA MÁXIMA: Impacto direto no contrato de trabalho do CPF: " + cpfColaborador;
        }
        return "Solicitação de RH recebida para o colaborador: " + cpfColaborador;
    }

    public String getPrioridadeDefinida() {
        int pesoSetor = (getSetor() != null) ? getSetor().getPesoEstrategico() : 1;
        
        int multiplicador = 1;
        
        if (categoriaSolicitacao != null) {
            if (categoriaSolicitacao.equalsIgnoreCase("Demissão") || 
                categoriaSolicitacao.equalsIgnoreCase("Folha de Pagamento")) {
                multiplicador = 2;
            }
        }

        int score = pesoSetor * multiplicador;

        if (score >= 15) return "CRÍTICA RH (Score " + score + ")";
        if (score >= 10) return "ALTA (Score " + score + ")";
        return "NORMAL (Score " + score + ")";
    }
}