package com.finance.api.controllers;


import com.finance.api.domain.dadosTransacoes.TransacaoRepository;
import com.finance.api.domain.dadosTransacoes.TransacaoService;
import com.finance.api.domain.dadosUsuario.Usuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@RequestMapping("/sumario")
@SecurityRequirement(name = "bearer-key")
public class SumarioController {

    @Autowired
    private TransacaoRepository repository;

    @Autowired
    private TransacaoService service;

    @GetMapping
    public ResponseEntity gerarSumario(@AuthenticationPrincipal Usuario usuario){
        var transacoes = repository.findByUsuario(usuario);

        var sumario = service.gerarSumario(transacoes);

        return ResponseEntity.ok().body(sumario);
    }

    @GetMapping("por-data")
    public ResponseEntity gerarSumarioPorData(@AuthenticationPrincipal Usuario usuario,
                                              @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
                                              @RequestParam("dataFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal){

        if (dataInicio.isAfter(dataFinal)) {
            return ResponseEntity.badRequest().body("A data de início não pode ser posterior à data final.");
        }

        var dataInicioHorario = dataInicio.atStartOfDay();
        var dataFinalHorario = dataFinal.atTime(LocalTime.MAX);

        var transacoes = repository.findByUsuarioAndDataBetween(usuario, dataInicioHorario, dataFinalHorario);
        var sumario = service.gerarSumario(transacoes);

        return ResponseEntity.ok(sumario);
    }
}
