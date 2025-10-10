package com.finance.api.domain.dadosTransacoes;

import com.finance.api.domain.dadosCategoria.TipoCategorias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosCadastroTransacoes(
        @NotBlank
        String descricao,
        @NotNull
        BigDecimal valor,
        @NotNull
        @Past
        LocalDateTime data,
        @NotNull
        Tipo tipo,
        @NotNull
        TipoCategorias tipoCategoria
) {
}
