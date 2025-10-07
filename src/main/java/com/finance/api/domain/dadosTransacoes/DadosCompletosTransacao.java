package com.finance.api.domain.dadosTransacoes;

import java.time.LocalDateTime;

public record DadosCompletosTransacao(Long id,String descricao, Double valor, LocalDateTime data, Tipo tipo) {

    public DadosCompletosTransacao(Transacao transacao) {
        this(transacao.getId(),transacao.getDescricao(),transacao.getValor(),transacao.getData(),transacao.getTipo());
    }
}
