package com.lukeware.cotacao.controller.implementacao.migrador;

import com.lukeware.cotacao.ICotacaoMigrador;
import com.lukeware.cotacao.controller.IMigradorCotacaoController;

import java.util.Objects;

/**
 * @author Diewgo Morais
 */

public final class MigradorCotacaoControllerFactory {
  private static MigradorCotacaoControllerFactory migradorCotacaoControllerFactory;

  private MigradorCotacaoControllerFactory() {
    super();
  }

  public static MigradorCotacaoControllerFactory instance() {
    if (Objects.isNull(migradorCotacaoControllerFactory)) {
      synchronized (MigradorCotacaoControllerFactory.class) {

        if (Objects.isNull(migradorCotacaoControllerFactory)) {
          migradorCotacaoControllerFactory = new MigradorCotacaoControllerFactory();
        }
      }
    }
    return migradorCotacaoControllerFactory;
  }

  public IMigradorCotacaoController create(ICotacaoMigrador cotacaoMigrador) {
    return new MigradorCotacaoController(cotacaoMigrador);
  }
}
