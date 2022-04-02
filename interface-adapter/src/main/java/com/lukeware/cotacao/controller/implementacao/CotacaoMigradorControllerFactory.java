package com.lukeware.cotacao.controller.implementacao;

import com.lukeware.cotacao.ICotacaoMigrador;
import com.lukeware.cotacao.controller.ICotacaoMigradorController;

import java.util.Objects;

/**
 * @author Diewgo Morais
 */

public final class CotacaoMigradorControllerFactory {
  private static CotacaoMigradorControllerFactory cotacaoMigradorControllerFactory;

  private CotacaoMigradorControllerFactory() {
    super();
  }

  public static CotacaoMigradorControllerFactory instance() {
    if (Objects.isNull(cotacaoMigradorControllerFactory)) {
      synchronized (CotacaoMigradorControllerFactory.class) {

        if (Objects.isNull(cotacaoMigradorControllerFactory)) {
          cotacaoMigradorControllerFactory = new CotacaoMigradorControllerFactory();
        }
      }
    }
    return cotacaoMigradorControllerFactory;
  }

  public ICotacaoMigradorController create(ICotacaoMigrador cotacaoMigrador) {
    return new CotacaoMigradorController(cotacaoMigrador);
  }
}
