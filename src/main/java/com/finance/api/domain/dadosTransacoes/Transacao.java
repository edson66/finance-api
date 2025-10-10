package com.finance.api.domain.dadosTransacoes;

import com.finance.api.domain.dadosCategoria.Categoria;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    private BigDecimal valor;
    private LocalDateTime data;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Transacao(@Valid DadosCadastroTransacoes dados, Categoria categoria) {
        this.descricao = dados.descricao();
        this.valor = dados.valor();
        this.data = dados.data();
        this.tipo = dados.tipo();
        this.categoria = categoria;
    }

    public void atualizarInformacoes(@Valid DadosCompletosTransacao dados,Categoria categoria) {
        if (dados.descricao() != null){
            this.descricao = dados.descricao();
        }
        if (dados.valor() != null){
            this.valor = dados.valor();
        }
        if (dados.data() != null){
            this.data = dados.data();
        }
        if (dados.tipo() != null){
            this.tipo = dados.tipo();
        }
        if (categoria != null){
            this.categoria = categoria;
        }
    }
}
