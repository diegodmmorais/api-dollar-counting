package com.lukeware.cotacao.repository.implementacao.registrador;

import com.lukeware.cotacao.ICotacaoRegistradorDataAccess;
import com.lukeware.cotacao.repository.ICotacaoRegistradorRepository;

import java.util.Objects;

public final class CotacaoRegistradorFactory {
  private static CotacaoRegistradorFactory cotacaoRegistradorFactory;

  private CotacaoRegistradorFactory() {
    super();
  }

  public static CotacaoRegistradorFactory instance() {
    if (Objects.isNull(cotacaoRegistradorFactory)) {
      synchronized (CotacaoRegistradorFactory.class) {
        if (Objects.isNull(cotacaoRegistradorFactory)) {
          cotacaoRegistradorFactory = new CotacaoRegistradorFactory();
        }
      }
    }
    return cotacaoRegistradorFactory;
  }

  public ICotacaoRegistradorDataAccess create(ICotacaoRegistradorRepository cotacaoRegistradorRepository) {
    return new CotacaoRegistradorDataAccess(cotacaoRegistradorRepository);
  }

}
