package com.lukeware.cotacao.controller.implementacao.pesquisador;

import com.lukeware.cotacao.IPesquisadorCotacao;
import com.lukeware.cotacao.controller.IPesquisadorCotacaoController;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class PesquisadorCotacaoControllerFactory {

  private static PesquisadorCotacaoControllerFactory pesquisadorCotacaoControllerFactory;

  private PesquisadorCotacaoControllerFactory() {
    super();
  }

  public static PesquisadorCotacaoControllerFactory instance() {
    if (Objects.isNull(pesquisadorCotacaoControllerFactory)) {
      synchronized (PesquisadorCotacaoControllerFactory.class) {
        if (Objects.isNull(pesquisadorCotacaoControllerFactory)) {
          pesquisadorCotacaoControllerFactory = new PesquisadorCotacaoControllerFactory();
        }
      }
    }
    return pesquisadorCotacaoControllerFactory;
  }

  public IPesquisadorCotacaoController create(IPesquisadorCotacao pesquisadorCotacao) {
    return new PesquisadorCotacaoController(pesquisadorCotacao);
  }
}
