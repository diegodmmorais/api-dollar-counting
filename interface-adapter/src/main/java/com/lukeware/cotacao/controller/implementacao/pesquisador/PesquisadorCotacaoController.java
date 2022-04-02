package com.lukeware.cotacao.controller.implementacao.pesquisador;

import com.lukeware.cotacao.IPesquisadorCotacao;
import com.lukeware.cotacao.controller.IPesquisadorCotacaoController;
import com.lukeware.cotacao.dto.CotacaoResponse;

import java.util.List;

/**
 * @author Diego Morais
 */
final class PesquisadorCotacaoController implements IPesquisadorCotacaoController {

  private final IPesquisadorCotacao pesquisadorCotacao;

  public PesquisadorCotacaoController(IPesquisadorCotacao pesquisadorCotacao) {
    this.pesquisadorCotacao = pesquisadorCotacao;
  }

  @Override
  public List<CotacaoResponse> pesquisar(String dataIncial, String dataFinal) {
    return this.pesquisadorCotacao.pesquisar(dataIncial, dataFinal);
  }
}
