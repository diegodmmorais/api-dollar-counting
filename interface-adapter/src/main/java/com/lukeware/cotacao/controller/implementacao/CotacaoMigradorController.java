package com.lukeware.cotacao.controller.implementacao;

import com.lukeware.cotacao.ICotacaoMigrador;
import com.lukeware.cotacao.controller.ICotacaoMigradorController;

/**
 * @author Diego Morais
 */
final class CotacaoMigradorController implements ICotacaoMigradorController {

  private final ICotacaoMigrador cotacaoMigrador;

  CotacaoMigradorController(ICotacaoMigrador cotacaoMigrador) {
    this.cotacaoMigrador = cotacaoMigrador;
  }

  @Override
  public void migrar(String dataCotacao) {
    this.cotacaoMigrador.migrar(dataCotacao);
  }
}
