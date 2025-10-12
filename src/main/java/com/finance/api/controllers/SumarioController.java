package com.finance.api.controllers;


import com.finance.api.domain.dadosTransacoes.TransacaoRepository;
import com.finance.api.domain.dadosTransacoes.TransacaoService;
import com.finance.api.domain.dadosUsuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sumario")
public class SumarioController {

    @Autowired
    private TransacaoRepository repository;

    @Autowired
    private TransacaoService service;

    @GetMapping
    public ResponseEntity gerarSumario(@AuthenticationPrincipal Usuario usuario){
        var transacoes = repository.findByUsuario(usuario);

        var sumario = service.gerarSumario(transacoes);

        return ResponseEntity.ok().build();
    }
}
