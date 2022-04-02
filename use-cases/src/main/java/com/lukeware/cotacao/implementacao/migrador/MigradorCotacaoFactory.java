package com.lukeware.cotacao.implementacao.migrador;

import com.lukeware.cotacao.IAdapterCotacao;
import com.lukeware.cotacao.IPesquisadorCotacaoDataAccess;
import com.lukeware.cotacao.IRegistradorCotacaoDataAccess;
import com.lukeware.cotacao.IMigradorCotacao;
import com.lukeware.cotacao.IRegistradorCotacao;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class MigradorCotacaoFactory {
  private static MigradorCotacaoFactory migradorCotacaoFactory;

  private MigradorCotacaoFactory() {
    super();
  }

  public static MigradorCotacaoFactory instance() {
    if (Objects.isNull(migradorCotacaoFactory)) {
      synchronized (MigradorCotacaoFactory.class) {
        if (Objects.isNull(migradorCotacaoFactory)) {
          migradorCotacaoFactory = new MigradorCotacaoFactory();
        }
      }
    }
    return migradorCotacaoFactory;
  }

  public IMigradorCotacao create(IRegistradorCotacaoDataAccess cotacaoDataAccess,
                                 IAdapterCotacao cotacaoAdapter,
                                 IRegistradorCotacao cotacaoRegistrador,
                                 IPesquisadorCotacaoDataAccess dataAccessPesquisador) {
    return new MigradorInteractorCotacao(cotacaoDataAccess, cotacaoAdapter, cotacaoRegistrador, dataAccessPesquisador);
  }
}
