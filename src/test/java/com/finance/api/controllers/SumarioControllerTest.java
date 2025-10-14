package com.finance.api.controllers;

import com.finance.api.domain.dadosTransacoes.Tipo;
import com.finance.api.domain.dadosTransacoes.Transacao;
import com.finance.api.domain.dadosTransacoes.TransacaoRepository;
import com.finance.api.domain.dadosUsuario.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class SumarioControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private TransacaoRepository repository;


    @Test
    @DisplayName("deve retornar exceção pois a primeira data precisa ser posterior à segunda")
    void gerarSumarioPorDataCaso1() throws Exception {
        mvc.perform(get("/sumario/por-data?dataInicio=2025-10-10&dataFinal=2025-10-05"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("A data de início não pode ser posterior à data final."));


    }

    @Test
    @DisplayName("deve retornar o sumario ,requisição correta")
    void gerarSumarioPorDataCaso2() throws Exception {

        Transacao transacaoMock = mock(Transacao.class);
        when(transacaoMock.getTipo()).thenReturn(Tipo.DESPESA);
        when(transacaoMock.getValor()).thenReturn(new BigDecimal("800.00"));

        List<Transacao> transacoes = List.of(transacaoMock);

        when(repository.findByUsuarioAndDataBetween(any(Usuario.class),any(LocalDateTime.class),any(LocalDateTime.class))).thenReturn(transacoes);


        mvc.perform(get("/sumario/por-data?dataInicio=2025-10-05&dataFinal=2025-10-10"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "valorDespesas":800.00,
                        "valorReceita":0.00,
                        "saldo":-800.00
                        }
                        """ ));


    }
}