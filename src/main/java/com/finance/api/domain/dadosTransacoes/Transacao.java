package com.finance.api.domain.dadosTransacoes;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Double valor;
    private LocalDateTime data;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    public Transacao(@Valid DadosCadastroTransacoes dados) {
        this.descricao = dados.descricao();
        this.valor = dados.valor();
        this.data = dados.data();
        this.tipo = dados.tipo();
    }
}
