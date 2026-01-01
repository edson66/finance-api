package com.finance.api.domain.security;

import com.finance.api.domain.dadosUsuario.Usuario;
import com.finance.api.domain.dadosUsuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroValidacaoToken extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        if (path.equals("/usuarios/login") || path.equals("/usuarios/cadastro")) {
            filterChain.doFilter(request, response);
            return;
        }

        var tokenJWT = pegarToken(request);

        if (tokenJWT != null) {
            var login = tokenService.validarTokenJwt(tokenJWT);
            var usuario = repository.findByLogin(login);

            var authorization = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authorization);

        }

        filterChain.doFilter(request,response);
    }

    private String pegarToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization");

        if (token != null){
            return token.replace("Bearer ","");
        }
        return null;
    }

}
