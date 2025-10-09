package com.finance.api.controllers;


import com.finance.api.domain.dadosTransacoes.DadosCadastroTransacoes;
import com.finance.api.domain.dadosTransacoes.DadosCompletosTransacao;
import com.finance.api.domain.dadosTransacoes.Transacao;
import com.finance.api.domain.dadosTransacoes.TransacaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public ResponseEntity<Page<DadosCompletosTransacao>> read(@PageableDefault(sort = {"id"},size = 20) Pageable pageable){
        var transacoes = repository.findAll(pageable);
        var page = transacoes.map(transacao -> new DadosCompletosTransacao(transacao));

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity readPorId(@PathVariable Long id){
        var transacao = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosCompletosTransacao(transacao));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarTransacao(@RequestBody @Valid DadosCompletosTransacao dados){
        var transacao = repository.findById(dados.id()).get();
        transacao.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosCompletosTransacao(transacao));
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity deletarTransacao(@PathVariable Long id){
        var transacao = repository.getReferenceById(id);
        repository.delete(transacao);
        return ResponseEntity.noContent().build();
    }

}
