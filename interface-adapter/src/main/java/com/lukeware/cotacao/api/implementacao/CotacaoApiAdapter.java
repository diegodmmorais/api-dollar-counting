package com.lukeware.cotacao.api.implementacao;

import com.lukeware.cotacao.ICotacaoApiAdapter;
import com.lukeware.cotacao.api.IPesquisadorCotacaoApi;
import com.lukeware.cotacao.dto.CotacaoApiResponse;

import java.util.Optional;

/**
 * @author Diego
 */
final class CotacaoApiAdapter implements ICotacaoApiAdapter {

  private final IPesquisadorCotacaoApi pesquisadorCotacaoApi;

  public CotacaoApiAdapter(IPesquisadorCotacaoApi pesquisadorCotacaoApi) {
    this.pesquisadorCotacaoApi = pesquisadorCotacaoApi;
  }

  @Override
  public Optional<CotacaoApiResponse> buscar(String dataCotacao) {
    return pesquisadorCotacaoApi.buscar(dataCotacao);
  }
}
