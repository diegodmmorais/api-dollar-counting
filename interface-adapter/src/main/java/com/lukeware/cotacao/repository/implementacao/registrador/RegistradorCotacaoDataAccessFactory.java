package com.lukeware.cotacao.repository.implementacao.registrador;

import com.lukeware.cotacao.IRegistradorCotacaoDataAccess;
import com.lukeware.cotacao.repository.IRegistradorCotacaoRepository;

import java.util.Objects;

public final class RegistradorCotacaoDataAccessFactory {
  private static RegistradorCotacaoDataAccessFactory registradorCotacaoDataAccessFactory;

  private RegistradorCotacaoDataAccessFactory() {
    super();
  }

  public static RegistradorCotacaoDataAccessFactory instance() {
    if (Objects.isNull(registradorCotacaoDataAccessFactory)) {
      synchronized (RegistradorCotacaoDataAccessFactory.class) {
        if (Objects.isNull(registradorCotacaoDataAccessFactory)) {
          registradorCotacaoDataAccessFactory = new RegistradorCotacaoDataAccessFactory();
        }
      }
    }
    return registradorCotacaoDataAccessFactory;
  }

  public IRegistradorCotacaoDataAccess create(IRegistradorCotacaoRepository cotacaoRegistradorRepository) {
    return new RegistradorCotacaoDataAccess(cotacaoRegistradorRepository);
  }

}
