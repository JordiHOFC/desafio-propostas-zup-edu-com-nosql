package br.com.zupedu.propostasComNoSql.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

//o handler ta desativando as anotações de validacao
//@RestControllerAdvice
public class HandlerExceptionRest {
  //  @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> argumentMethodNotValidException(MethodArgumentNotValidException e){
        return ResponseEntity.ok(e.getBindingResult().getFieldErrors().stream().map(ErroPersonalizado::new).collect(Collectors.toList()));
    }
}
