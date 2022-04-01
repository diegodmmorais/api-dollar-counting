package com.lukeware.cotacao.implementacao;

import com.lukeware.cotacao.ICotacaoAdapter;
import com.lukeware.cotacao.ICotacaoDataAccess;
import com.lukeware.cotacao.ICotacaoIteractorCreate;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class CotacaoInteractorFactory {

  private static CotacaoInteractorFactory cotacaoInteractorFactory;

  private CotacaoInteractorFactory() {
    super();
  }

  public static CotacaoInteractorFactory instace() {
    if (Objects.isNull(cotacaoInteractorFactory)) {
      synchronized (cotacaoInteractorFactory) {
        if (Objects.isNull(cotacaoInteractorFactory)) {
          cotacaoInteractorFactory = new CotacaoInteractorFactory();
        }
      }
    }
    return cotacaoInteractorFactory;
  }

  public ICotacaoIteractorCreate create(ICotacaoDataAccess cotacaoDataAccess, ICotacaoAdapter cotacaoAdapter) {
    return new CotacaoInteractor(cotacaoDataAccess, cotacaoAdapter);
  }
}
