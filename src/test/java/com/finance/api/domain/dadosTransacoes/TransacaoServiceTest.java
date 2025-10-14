package com.finance.api.domain.dadosTransacoes;

import com.finance.api.domain.dadosCategoria.Categoria;
import com.finance.api.domain.dadosCategoria.CategoriaRepository;
import com.finance.api.domain.dadosCategoria.TipoCategorias;
import com.finance.api.domain.dadosUsuario.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TransacaoServiceTest {

    @InjectMocks
    private TransacaoService service;


    @BeforeEach
    void inicializarTeste(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("deve devolver exception pois não existem transações")
    void gerarSumarioCaso1() {
        List<Transacao> transacoes = new ArrayList<>();

        RuntimeException ex = assertThrows(RuntimeException.class,() -> service.gerarSumario(transacoes));

        assertEquals("Você não possui transações ativas",ex.getMessage());
    }

    @Test
    @DisplayName("deve devolver o dto com o sumário correto")
    void gerarSumarioCaso2(){
        List<Transacao> transacoes = new ArrayList<>();

        Transacao receitaMock = mock(Transacao.class);
        when(receitaMock.getTipo()).thenReturn(Tipo.RECEITA);
        when(receitaMock.getValor()).thenReturn(new BigDecimal("2500.00"));

        Transacao despesaMock = mock(Transacao.class);
        when(despesaMock.getTipo()).thenReturn(Tipo.DESPESA);
        when(despesaMock.getValor()).thenReturn(new BigDecimal("1500.00"));

        Transacao outraDespesaMock = mock(Transacao.class);
        when(outraDespesaMock.getTipo()).thenReturn(Tipo.DESPESA);
        when(outraDespesaMock.getValor()).thenReturn(new BigDecimal("500.00"));

        transacoes.add(receitaMock);
        transacoes.add(despesaMock);
        transacoes.add(outraDespesaMock);

        DadosSumarioTransacoes dtoSumario = service.gerarSumario(transacoes);

        assertNotNull(dtoSumario);

        assertEquals(0,new BigDecimal("2000.00").compareTo(dtoSumario.valorDespesas()));
        assertEquals(0,new BigDecimal("2500.00").compareTo(dtoSumario.valorReceita()));
        assertEquals(0,new BigDecimal("500.00").compareTo(dtoSumario.saldo()));

    }


}