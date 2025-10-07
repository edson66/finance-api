package com.finance.api.domain.dadosTransacoes;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastroTransacoes(
        @NotBlank
        String descricao,
        @NotNull
        Double valor,
        @NotNull
        @Future
        LocalDateTime data,
        @NotNull
        Tipo tipo
) {
}
