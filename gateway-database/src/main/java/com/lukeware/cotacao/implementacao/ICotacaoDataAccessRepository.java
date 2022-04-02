package com.lukeware.cotacao.implementacao;

import com.lukeware.cotacao.implementacao.dto.CotacaoDataAccessResponse;

import java.util.List;
import java.util.Optional;

/**
 * @author Diego Morais
 */
public interface ICotacaoDataAccessRepository {
  Optional<CotacaoDataAccessResponse> pesquisarPorData(String dataDaCotacao);

  List<CotacaoDataAccessResponse> pesquisarPorData(String dataIncial, String dataFinal);
}
