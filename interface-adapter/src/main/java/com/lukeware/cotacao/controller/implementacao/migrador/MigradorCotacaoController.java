package com.lukeware.cotacao.controller.implementacao.migrador;

import com.lukeware.cotacao.IMigradorCotacao;
import com.lukeware.cotacao.controller.IMigradorCotacaoController;

/**
 * @author Diego Morais
 */
final class MigradorCotacaoController implements IMigradorCotacaoController {

  private final IMigradorCotacao cotacaoMigrador;

  MigradorCotacaoController(IMigradorCotacao cotacaoMigrador) {
    this.cotacaoMigrador = cotacaoMigrador;
  }

  @Override
  public void migrar(String dataCotacao) {
    this.cotacaoMigrador.migrar(dataCotacao);
  }
}
