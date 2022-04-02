package com.lukeware.cotacao.repository.implementacao.pesquisador;

import com.lukeware.cotacao.repository.ICotacaoPesquisadorRepository;
import com.lukeware.cotacao.dto.CotacaoDataAccessResponse;
import com.lukeware.cotacao.IPesquisadorCotacaoDataAccess;

import java.util.List;
import java.util.Optional;

/**
 * @author Diego Morais
 */
final class PesquisadorCotacaoDataAccess implements IPesquisadorCotacaoDataAccess {

  private final ICotacaoPesquisadorRepository cotacaoPesquisadorRepository;

  public PesquisadorCotacaoDataAccess(ICotacaoPesquisadorRepository cotacaoPesquisadorRepository) {
    this.cotacaoPesquisadorRepository = cotacaoPesquisadorRepository;
  }

  @Override
  public Optional<CotacaoDataAccessResponse> pesquisarPorData(String dataDaCotacao) {
    return this.cotacaoPesquisadorRepository.pesquisarPorData(dataDaCotacao);
  }

  @Override
  public List<CotacaoDataAccessResponse> pesquisarPorData(String dataIncial, String dataFinal) {
    return this.cotacaoPesquisadorRepository.pesquisarPorData(dataIncial, dataFinal);
  }
}
