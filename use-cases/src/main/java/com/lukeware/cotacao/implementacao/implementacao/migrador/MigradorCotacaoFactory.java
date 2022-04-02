package com.lukeware.cotacao.implementacao.implementacao.migrador;

import com.lukeware.cotacao.implementacao.ICotacaoAdapter;
import com.lukeware.cotacao.implementacao.ICotacaoDataAccessPesquisador;
import com.lukeware.cotacao.implementacao.ICotacaoDataAccessRegistrador;
import com.lukeware.cotacao.implementacao.ICotacaoMigrador;
import com.lukeware.cotacao.implementacao.ICotacaoRegistrador;

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

  public ICotacaoMigrador create(ICotacaoDataAccessRegistrador cotacaoDataAccess,
                                 ICotacaoAdapter cotacaoAdapter,
                                 ICotacaoRegistrador cotacaoRegistrador,
                                 ICotacaoDataAccessPesquisador dataAccessPesquisador) {
    return new MigradorCotacaoInteractor(cotacaoDataAccess, cotacaoAdapter, cotacaoRegistrador, dataAccessPesquisador);
  }
}
