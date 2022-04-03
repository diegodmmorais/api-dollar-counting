package com.lukeware.cotacao.implementacao.migrador;

import com.lukeware.cotacao.implementacao.AbstractException;

/**
 * @author Diego Morais
 */
public final class MigradorCotacaoException extends AbstractException {

  public MigradorCotacaoException(String mensagem) {
    this(mensagem, null);
  }

  public MigradorCotacaoException(String mensagem, String detalhamento) {
    super(mensagem, detalhamento);
  }
}
