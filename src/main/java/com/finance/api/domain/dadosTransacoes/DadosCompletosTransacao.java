package com.finance.api.domain.dadosTransacoes;


import com.finance.api.domain.dadosCategoria.TipoCategorias;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosCompletosTransacao(@NotNull Long id, String descricao, BigDecimal valor, LocalDateTime data, Tipo tipo,
                                      TipoCategorias tipoCategoria) {

    public DadosCompletosTransacao(Transacao transacao) {
        this(transacao.getId(),transacao.getDescricao(),transacao.getValor(),transacao.getData(),transacao.getTipo(),transacao.getCategoria().getTipoCategoria());
    }
}
