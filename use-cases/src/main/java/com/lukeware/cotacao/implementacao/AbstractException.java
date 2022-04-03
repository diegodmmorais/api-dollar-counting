package com.lukeware.cotacao.implementacao;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Diego Morais
 */
public abstract class AbstractException extends RuntimeException {
  private final Map<String, String> error;

  public AbstractException(String mensagem, String detalhamento) {
    super(mensagem);
    this.error = new LinkedHashMap<>();
    this.error.put("mensagem", mensagem);
    this.error.put("detalhamento", detalhamento);
  }

  public Map<String, String> getError() {
    return error;
  }
}
