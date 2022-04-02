package com.lukeware.cotacao.controller.implementacao.migrador;

import com.lukeware.cotacao.ICotacaoMigrador;
import com.lukeware.cotacao.controller.IMigradorCotacaoController;

/**
 * @author Diego Morais
 */
final class MigradorCotacaoController implements IMigradorCotacaoController {

  private final ICotacaoMigrador cotacaoMigrador;

  MigradorCotacaoController(ICotacaoMigrador cotacaoMigrador) {
    this.cotacaoMigrador = cotacaoMigrador;
  }

  @Override
  public void migrar(String dataCotacao) {
    this.cotacaoMigrador.migrar(dataCotacao);
  }
}
