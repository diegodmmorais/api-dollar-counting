package com.lukeware.cotacao.implementacao.migrador;

import com.lukeware.cotacao.ICotacaoApiAdapter;
import com.lukeware.cotacao.IMigradorCotacaoInteractor;
import com.lukeware.cotacao.IPesquisadorCotacaoDataAccess;
import com.lukeware.cotacao.IRegistradorCotacao;
import com.lukeware.cotacao.IRegistradorCotacaoDataAccess;
import com.lukeware.utils.ValidadorDeDataFactory;

import java.time.format.DateTimeFormatter;
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

  public IMigradorCotacaoInteractor create(IRegistradorCotacaoDataAccess cotacaoDataAccess,
                                           ICotacaoApiAdapter cotacaoAdapter,
                                           IRegistradorCotacao cotacaoRegistrador,
                                           IPesquisadorCotacaoDataAccess dataAccessPesquisador) {
    return new MigradorCotacaoInteractor(cotacaoDataAccess, cotacaoAdapter, cotacaoRegistrador, dataAccessPesquisador, ValidadorDeDataFactory.instance()
                                                                                                                                             .create(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
  }
}
