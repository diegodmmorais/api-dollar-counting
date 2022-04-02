package com.lukeware.cotacao;

import com.lukeware.cotacao.dto.CotacaoResponse;

import java.util.List;

/**
 * @author Diego Morais
 */
public interface IPesquisadorCotacao {
  List<CotacaoResponse> pesquisar(String dataIncial, String dataFinal);
}
