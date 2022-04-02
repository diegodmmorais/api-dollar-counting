package com.lukeware.cotacao.implementacao.implementacao;

import com.lukeware.cotacao.implementacao.ICotacaoDataAccessPesquisador;
import com.lukeware.cotacao.implementacao.ICotacaoDataAccessRepository;

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

  public ICotacaoDataAccessPesquisador create(ICotacaoDataAccessRepository cotacaoDataAccessRepository) {
    return new CotacaoPesquisadorDataAccess(cotacaoDataAccessRepository);
  }
}
