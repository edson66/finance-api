package com.finance.api.domain.dadosUsuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;


class UsuarioServiceTest {

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    @BeforeEach
    void inicializar(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("deve retornar true e senha ser validada")
    void validarSenhaCase1() {
        var senha = "testE123!";

        assertDoesNotThrow(() -> service.validarSenha(senha));
    }

    @Test
    @DisplayName("senha não deve ser validada por falta de caracteres")
    void validarSenhaCase2() {
        var senhaErrada = "";

        RuntimeException ex = assertThrows(RuntimeException.class,() -> service.validarSenha(senhaErrada));

        assertEquals("A senha não tem conteúdo",ex.getMessage());
    }

    @Test
    @DisplayName("senha não deve ser validada por falta de caracter maiúsculo")
    void validarSenhaCase3() {
        var senhaErrada = "teste123!";

        RuntimeException ex = assertThrows(RuntimeException.class,() -> service.validarSenha(senhaErrada));

        assertEquals("A senha deve conter pelo menos um caracter Maiúsculo",ex.getMessage());
    }

    @Test
    @DisplayName("senha não deve ser validada por falta de caracter minúsculo")
    void validarSenhaCase4() {
        var senhaErrada = "TESTE123!";

        RuntimeException ex = assertThrows(RuntimeException.class,() -> service.validarSenha(senhaErrada));

        assertEquals("A senha deve conter pelo menos um caracter Minúsculo",ex.getMessage());
    }

    @Test
    @DisplayName("senha não deve ser validada por falta de número")
    void validarSenhaCase5() {
        var senhaErrada = "testE!";

        RuntimeException ex = assertThrows(RuntimeException.class,() -> service.validarSenha(senhaErrada));

        assertEquals("A senha deve conter pelo menos um Número",ex.getMessage());
    }

    @Test
    @DisplayName("senha não deve ser validada por falta de caracter especial")
    void validarSenhaCase6() {
        var senhaErrada = "Teste123";

        RuntimeException ex = assertThrows(RuntimeException.class,() -> service.validarSenha(senhaErrada));

        assertEquals("A senha deve conter pelo menos um caracter especial",ex.getMessage());
    }


}