package com.lukeware.cotacao.implementacao;

import com.lukeware.cotacao.implementacao.dto.CotacaoResponse;

import java.util.List;

/**
 * @author Diego Morais
 */
public interface IPesquisadorCotacao {
  List<CotacaoResponse> pesquisar(String dataIncial, String dataFinal);
}
