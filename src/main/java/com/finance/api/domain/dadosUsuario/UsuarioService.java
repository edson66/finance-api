package com.finance.api.domain.dadosUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public DadosCompletosUsuario cadastrar(DadosCadastroUsuario dados){
        var senhaCodificada = encoder.encode(dados.senha());
        var usuario = new Usuario(dados,senhaCodificada);
        repository.save(usuario);
        var dto = new DadosCompletosUsuario(usuario);
        return dto;
    }
}
