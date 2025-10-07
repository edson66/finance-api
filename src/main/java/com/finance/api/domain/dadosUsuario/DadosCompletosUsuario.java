package com.finance.api.domain.dadosUsuario;

public record DadosCompletosUsuario(Long id,String login,String senha) {
    public DadosCompletosUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin(), usuario.getSenha());
    }
}
