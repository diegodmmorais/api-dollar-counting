package com.lukeware.cotacao;

import com.lukeware.cotacao.dto.CotacaoDataAccessResponse;

import java.util.List;
import java.util.Optional;

/**
 * @author Diego Morais
 */
public interface ICotacaoPesquisadorDataAccess {
  Optional<CotacaoDataAccessResponse> pesquisarPorData(String dataDaCotacao);

  List<CotacaoDataAccessResponse> pesquisarPorData(String dataIncial, String dataFinal);
}
