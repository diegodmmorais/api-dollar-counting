package com.lukeware.cotacao.implementacao.pesquisador;

import com.lukeware.cotacao.ICotacaoDataAccessPesquisador;
import com.lukeware.cotacao.IPesquisadorCotacao;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class PesquisadorCotacaoFactory {

  private static PesquisadorCotacaoFactory pesquisadorCotacaoFactory;

  private PesquisadorCotacaoFactory() {
    super();
  }

  public static PesquisadorCotacaoFactory instance() {
    if (Objects.isNull(pesquisadorCotacaoFactory)) {
      synchronized (PesquisadorCotacaoFactory.class) {
        if (Objects.isNull(pesquisadorCotacaoFactory)) {
          pesquisadorCotacaoFactory = new PesquisadorCotacaoFactory();
        }
      }
    }
    return pesquisadorCotacaoFactory;
  }

  public IPesquisadorCotacao create(final ICotacaoDataAccessPesquisador dataAccessPesquisador) {
    return new PesquisadorCotacaoInteractor(dataAccessPesquisador);
  }
}
