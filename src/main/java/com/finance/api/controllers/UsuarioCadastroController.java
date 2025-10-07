package com.finance.api.controllers;


import com.finance.api.domain.dadosUsuario.DadosCadastroUsuario;
import com.finance.api.domain.dadosUsuario.DadosCompletosUsuario;
import com.finance.api.domain.dadosUsuario.Usuario;
import com.finance.api.domain.dadosUsuario.UsuarioRepository;
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
@RequestMapping("/usuarios/cadastro")
public class UsuarioCadastroController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder){
        var usuario = new Usuario(dados);
        repository.save(usuario);

        var dto = new DadosCompletosUsuario(usuario);

        var uri = uriBuilder.path("/usuarios/cadastro/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }
}
