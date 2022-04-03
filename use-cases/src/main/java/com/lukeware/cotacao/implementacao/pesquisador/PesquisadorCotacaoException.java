package com.lukeware.cotacao.implementacao.pesquisador;

import com.lukeware.cotacao.implementacao.AbstractException;

/**
 * @author Diego Morais
 */
public final class PesquisadorCotacaoException extends AbstractException {

  public PesquisadorCotacaoException(String mensagem) {
    this(mensagem, null);
  }

  public PesquisadorCotacaoException(String mensagem, String detalhamento) {
    super(mensagem, detalhamento);
  }
}
