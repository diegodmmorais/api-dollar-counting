package com.lukeware.cotacao.implementacao.registrador;

import com.lukeware.cotacao.IRegistradorCotacaoDataAccess;
import com.lukeware.cotacao.IRegistradorCotacao;

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

  public IRegistradorCotacao create(IRegistradorCotacaoDataAccess cotacaoDataAccess) {
    return new RegistradorInteractorCotacao(cotacaoDataAccess);
  }
}
