package com.lukeware.cotacao.implementacao.implementacao;

import com.lukeware.cotacao.implementacao.ICotacaoDataAccessPesquisador;
import com.lukeware.cotacao.implementacao.ICotacaoDataAccessRepository;
import com.lukeware.cotacao.implementacao.dto.CotacaoDataAccessResponse;

import java.util.List;
import java.util.Optional;

/**
 * @author Diego Morais
 */
final class CotacaoPesquisadorDataAccess implements ICotacaoDataAccessPesquisador {

  private final ICotacaoDataAccessRepository cotacaoDataAccessRepository;

  public CotacaoPesquisadorDataAccess(ICotacaoDataAccessRepository cotacaoDataAccessRepository) {
    this.cotacaoDataAccessRepository = cotacaoDataAccessRepository;
  }

  @Override
  public Optional<CotacaoDataAccessResponse> pesquisarPorData(String dataDaCotacao) {
    return this.cotacaoDataAccessRepository.pesquisarPorData(dataDaCotacao);
  }

  @Override
  public List<CotacaoDataAccessResponse> pesquisarPorData(String dataIncial, String dataFinal) {
    return this.cotacaoDataAccessRepository.pesquisarPorData(dataIncial, dataFinal);
  }
}
