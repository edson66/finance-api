package com.finance.api.controllers;


import com.finance.api.domain.dadosTransacoes.DadosCadastroTransacoes;
import com.finance.api.domain.dadosTransacoes.DadosCompletosTransacao;
import com.finance.api.domain.dadosTransacoes.Transacao;
import com.finance.api.domain.dadosTransacoes.TransacaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity postarTransacao(@RequestBody @Valid DadosCadastroTransacoes dados, UriComponentsBuilder uriBulder){
        var transacao = new Transacao(dados);
        repository.save(transacao);

        var uri = uriBulder.path("/transacoes/{id}").buildAndExpand(transacao.getId()).toUri();
        var dto = new DadosCompletosTransacao(transacao);

        return ResponseEntity.created(uri).body(dto);
    }
}
