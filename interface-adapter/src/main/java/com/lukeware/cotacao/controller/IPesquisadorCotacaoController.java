package com.lukeware.cotacao.controller;

import com.lukeware.cotacao.dto.CotacaoResponse;

import java.util.List;

/**
 * @author Diego Morais
 */
public interface IPesquisadorCotacaoController {
  List<CotacaoResponse> pesquisar(String dataIncial, String dataFinal);
}
