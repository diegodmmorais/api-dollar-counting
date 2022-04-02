package com.lukeware.cotacao.implementacao.pesquisador;

import com.lukeware.cotacao.ICotacaoPesquisadorDataAccess;
import com.lukeware.cotacao.ICotacaoPesquisadorRepository;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class CotacaoPesquisadorDataAccessFactory {
  private static CotacaoPesquisadorDataAccessFactory cotacaoDataAccessFactory;

  private CotacaoPesquisadorDataAccessFactory() {
    super();
  }


  public static CotacaoPesquisadorDataAccessFactory instance() {
    if (Objects.isNull(cotacaoDataAccessFactory)) {
      synchronized (CotacaoPesquisadorDataAccessFactory.class) {
        if (Objects.isNull(cotacaoDataAccessFactory)) {
          cotacaoDataAccessFactory = new CotacaoPesquisadorDataAccessFactory();
        }
      }
    }
    return cotacaoDataAccessFactory;
  }

  public ICotacaoPesquisadorDataAccess create(ICotacaoPesquisadorRepository cotacaoDataAccessRepository) {
    return new CotacaoPesquisadorDataAccess(cotacaoDataAccessRepository);
  }
}
