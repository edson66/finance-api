package com.finance.api.domain.dadosUsuario;

import com.finance.api.domain.security.FiltroValidacaoToken;
import com.finance.api.domain.security.TokenService;
import jakarta.validation.constraints.NotBlank;
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
        validarSenha(dados.senha());

        var senhaCodificada = encoder.encode(dados.senha());
        var usuario = new Usuario(dados,senhaCodificada);
        repository.save(usuario);

        return new DadosCompletosUsuario(usuario);
    }

    public void validarSenha(@NotBlank String senha) {
        boolean temMaiuscula = false;
        boolean temMinuscula = false;
        boolean temNumero = false;
        boolean temCaracterEspecial = false;


        if (senha.isEmpty()){
            throw new RuntimeException("A senha não tem conteúdo");
        }

        for (char c: senha.toCharArray()){
            if (Character.isUpperCase(c)){
                temMaiuscula = true;
            } else if (Character.isLowerCase(c)) {
                temMinuscula = true;
            } else if (Character.isDigit(c)) {
                temNumero = true;
            } else if ("!@#$%^&*()_+-={}[]|:;\"'<>,.?/".contains(String.valueOf(c))) {
                temCaracterEspecial = true;
            }
        }

        if (!temMaiuscula){
            throw new RuntimeException("A senha deve conter pelo menos um caracter Maiúsculo");
        }
        if (!temMinuscula){
            throw new RuntimeException("A senha deve conter pelo menos um caracter Minúsculo");
        }
        if (!temNumero){
            throw new RuntimeException("A senha deve conter pelo menos um Número");
        }
        if (!temCaracterEspecial){
            throw new RuntimeException("A senha deve conter pelo menos um caracter especial");
        }
    }

}
