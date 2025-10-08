package com.finance.api.controllers;


import com.finance.api.domain.dadosUsuario.DadosCadastroUsuario;
import com.finance.api.domain.dadosUsuario.Usuario;
import com.finance.api.domain.security.TokenDto;
import com.finance.api.domain.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios/login")
public class UsuarioController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity fazerLogin(@RequestBody @Valid DadosCadastroUsuario dados){
        var token = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());
        var autenticacao = manager.authenticate(token);
        var usuario = new Usuario(dados.login());
        var tokenJWT = tokenService.gerarToken(usuario);

        return ResponseEntity.ok(new TokenDto(tokenJWT));
    }

}


