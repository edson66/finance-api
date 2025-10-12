package com.finance.api.domain.dadosTransacoes;

import java.math.BigDecimal;

public record DadosSumarioTransacoes(BigDecimal valorDespesas,BigDecimal valorReceita,BigDecimal saldo) {
}
