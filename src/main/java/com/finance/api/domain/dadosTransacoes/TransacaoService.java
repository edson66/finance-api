package com.finance.api.domain.dadosTransacoes;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransacaoService {

    public DadosSumarioTransacoes gerarSumario(List<Transacao> transacoes){
        BigDecimal valorReceita = BigDecimal.ZERO;
        BigDecimal valorDespesas = BigDecimal.ZERO;

        if (transacoes.isEmpty()){
            throw new RuntimeException("Você não possui transações ativas");
        }

        for(Transacao transacao : transacoes){
            if (transacao.getTipo() == Tipo.RECEITA){
                valorReceita = valorReceita.add(transacao.getValor());
            }else {
                valorDespesas = valorDespesas.add(transacao.getValor());
            }
        }

        BigDecimal saldo = valorReceita.subtract(valorDespesas);

        return new DadosSumarioTransacoes(valorDespesas,valorReceita,saldo);
    }
}
