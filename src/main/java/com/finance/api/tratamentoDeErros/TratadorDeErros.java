package com.finance.api.tratamentoDeErros;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.naming.AuthenticationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity erro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity loginDuplicado(){
        return ResponseEntity.badRequest().body("Usuário já existente");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity erroDeValidacao(MethodArgumentNotValidException exception){
        var erro = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(erro.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity errosRunTime(RuntimeException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity erroDeAutenticacao(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário inexistente ou senha inválida!");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity cadastroDeUsuarioJaExistenteOuInvalido(){
        var body  = Map.of("erro","Usuário já existente");
        return ResponseEntity.badRequest().body(body);
    }

    private record DadosErroValidacao(String campo,String Mensagem){

        public DadosErroValidacao(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
