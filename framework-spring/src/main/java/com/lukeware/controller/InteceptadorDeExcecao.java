package com.lukeware.controller;

import com.lukeware.cotacao.implementacao.migrador.MigradorCotacaoException;
import com.lukeware.cotacao.implementacao.pesquisador.PesquisadorCotacaoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Diego Morais
 */
@ControllerAdvice
public class InteceptadorDeExcecao extends ResponseEntityExceptionHandler {

  @ExceptionHandler({PesquisadorCotacaoException.class})
  public ResponseEntity<Map<String, Object>> excecoesNaPesquisa(PesquisadorCotacaoException ex, WebRequest request) {
    return new ResponseEntity(toMap(ex.getError(), (ServletWebRequest) request), new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({MigradorCotacaoException.class})
  public ResponseEntity<Map<String, Object>> excecoesNaMigracao(MigradorCotacaoException ex, WebRequest request) {
    return new ResponseEntity(toMap(ex.getError(), (ServletWebRequest) request), new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  private Map<String, Object> toMap(Map<String, String> ex, ServletWebRequest request) {
    Map<String, Object> excecao = new LinkedHashMap<>();
    excecao.put("timestamp", LocalDateTime.now());
    excecao.put("status", HttpStatus.BAD_REQUEST.value());
    excecao.put("error", ex);
    excecao.put("path", request.getRequest().getRequestURI());
    return excecao;
  }


}
