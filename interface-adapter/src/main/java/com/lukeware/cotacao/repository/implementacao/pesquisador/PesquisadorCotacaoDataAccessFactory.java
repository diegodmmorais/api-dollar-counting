package com.lukeware.cotacao.repository.implementacao.pesquisador;

import com.lukeware.cotacao.IPesquisadorCotacaoDataAccess;
import com.lukeware.cotacao.repository.ICotacaoPesquisadorRepository;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class PesquisadorCotacaoDataAccessFactory {
  private static PesquisadorCotacaoDataAccessFactory cotacaoDataAccessFactory;

  private PesquisadorCotacaoDataAccessFactory() {
    super();
  }


  public static PesquisadorCotacaoDataAccessFactory instance() {
    if (Objects.isNull(cotacaoDataAccessFactory)) {
      synchronized (PesquisadorCotacaoDataAccessFactory.class) {
        if (Objects.isNull(cotacaoDataAccessFactory)) {
          cotacaoDataAccessFactory = new PesquisadorCotacaoDataAccessFactory();
        }
      }
    }
    return cotacaoDataAccessFactory;
  }

  public IPesquisadorCotacaoDataAccess create(ICotacaoPesquisadorRepository cotacaoDataAccessRepository) {
    return new PesquisadorCotacaoDataAccess(cotacaoDataAccessRepository);
  }
}
