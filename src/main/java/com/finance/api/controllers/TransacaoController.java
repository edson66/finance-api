package com.finance.api.controllers;


import com.finance.api.domain.dadosCategoria.CategoriaService;
import com.finance.api.domain.dadosTransacoes.*;
import com.finance.api.domain.dadosUsuario.Usuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/transacoes")
@SecurityRequirement(name = "bearer-key")
public class TransacaoController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private TransacaoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity postarTransacao(@RequestBody @Valid DadosCadastroTransacoes dados, UriComponentsBuilder uriBulder, @AuthenticationPrincipal Usuario usuario){
        var categoria = categoriaService.buscarCategoria(dados.tipoCategoria());
        var transacao = new Transacao(dados,categoria,usuario);
        repository.save(transacao);

        var uri = uriBulder.path("/transacoes/{id}").buildAndExpand(transacao.getId()).toUri();
        var dto = new DadosAtualizacaoTransacao(transacao);

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosCompletosTransacao>> read(@PageableDefault(sort = {"id"},size = 20) Pageable pageable
    ,@AuthenticationPrincipal Usuario usuario){
        var transacoes = repository.findByUsuario(usuario, pageable);
        var page = transacoes.map(transacao -> new DadosCompletosTransacao(transacao));

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity readPorId(@PathVariable Long id,@AuthenticationPrincipal Usuario usuario){
        var transacao = repository.getReferenceById(id);

        if (!transacao.getUsuario().getId().equals(usuario.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(new DadosCompletosTransacao(transacao));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarTransacao(@RequestBody @Valid DadosAtualizacaoTransacao dados,@AuthenticationPrincipal Usuario usuario){
        var categoria = categoriaService.buscarCategoria(dados.tipoCategoria());
        var transacao = repository.findById(dados.id()).get();

        if (!transacao.getUsuario().getId().equals(usuario.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        transacao.atualizarInformacoes(dados,categoria);


        return ResponseEntity.ok(new DadosAtualizacaoTransacao(transacao));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarTransacao(@PathVariable Long id){
        var transacao = repository.getReferenceById(id);
        repository.delete(transacao);
        return ResponseEntity.noContent().build();
    }

}
