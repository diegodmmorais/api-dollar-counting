package com.lukeware.cotacao.implementacao.registrador;

import com.lukeware.cotacao.ICotacaoDataAccessRegistrador;
import com.lukeware.cotacao.ICotacaoRegistrador;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class RegistradorCotacaoFactory {

  private static RegistradorCotacaoFactory cotacaoInteractorFactory;

  private RegistradorCotacaoFactory() {
    super();
  }

  public static RegistradorCotacaoFactory instace() {
    if (Objects.isNull(cotacaoInteractorFactory)) {
      synchronized (RegistradorCotacaoFactory.class) {
        if (Objects.isNull(cotacaoInteractorFactory)) {
          cotacaoInteractorFactory = new RegistradorCotacaoFactory();
        }
      }
    }
    return cotacaoInteractorFactory;
  }

  public ICotacaoRegistrador create(ICotacaoDataAccessRegistrador cotacaoDataAccess) {
    return new RegistradorCotacaoInteractor(cotacaoDataAccess);
  }
}
