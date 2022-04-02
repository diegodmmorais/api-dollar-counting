package com.lukeware.cotacao.controller.implementacao.migrador;

import com.lukeware.cotacao.IMigradorCotacaoInteractor;
import com.lukeware.cotacao.controller.IMigradorCotacaoController;

/**
 * @author Diego Morais
 */
final class MigradorCotacaoController implements IMigradorCotacaoController {

  private final IMigradorCotacaoInteractor cotacaoMigrador;

  MigradorCotacaoController(IMigradorCotacaoInteractor cotacaoMigrador) {
    this.cotacaoMigrador = cotacaoMigrador;
  }

  @Override
  public void migrar(String dataCotacao) {
    this.cotacaoMigrador.migrar(dataCotacao);
  }
}
