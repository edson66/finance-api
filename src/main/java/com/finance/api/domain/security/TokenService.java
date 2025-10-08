package com.finance.api.domain.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.finance.api.domain.dadosUsuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    @Value("${senha.secreta.token}")
    private String senhaSecreta;

    public String gerarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(senhaSecreta);
            return JWT.create()
                    .withIssuer("financeAPI")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token JWT.",exception);
        }
    }

    public String validarTokenJwt(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(senhaSecreta);
            return JWT.require(algorithm)
                    .withIssuer("financeAPI")
                    .build()
                    .verify(token)
                    .getSubject();


        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token inválido ou expirado");
        }
    }
}
