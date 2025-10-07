package com.finance.api.domain.dadosUsuario;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
        @NotBlank
        String login,
        @NotBlank
        String senha
) {
}
