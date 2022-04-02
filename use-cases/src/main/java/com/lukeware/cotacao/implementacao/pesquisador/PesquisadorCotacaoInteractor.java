package com.lukeware.cotacao.implementacao.pesquisador;

import com.lukeware.cotacao.ICotacaoDataAccessPesquisador;
import com.lukeware.cotacao.IPesquisadorCotacao;
import com.lukeware.cotacao.dto.CotacaoDataAccessResponse;
import com.lukeware.cotacao.dto.CotacaoResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Diego morais
 */
final class PesquisadorCotacaoInteractor implements IPesquisadorCotacao {
  private final ICotacaoDataAccessPesquisador dataAccessPesquisador;

  public PesquisadorCotacaoInteractor(ICotacaoDataAccessPesquisador dataAccessPesquisador) {
    this.dataAccessPesquisador = dataAccessPesquisador;
  }

  @Override
  public List<CotacaoResponse> pesquisar(String dataIncial, String dataFinal) {
    return this.dataAccessPesquisador.pesquisarPorData(dataIncial, dataFinal)
                                     .stream()
                                     .map(this::coverterParaResponse)
                                     .collect(Collectors.toList());
  }

  private CotacaoResponse coverterParaResponse(CotacaoDataAccessResponse dataAccessResponse) {
    return CotacaoResponse.Builder.builder()
                                  .cotacaoCompra(dataAccessResponse.getCotacaoCompra())
                                  .cotacaoVenda(dataAccessResponse.getCotacaoVenda())
                                  .dataCotacao(dataAccessResponse.getDataCotacao())
                                  .dataHoraCotacao(dataAccessResponse.getDataHoraCotacao())
                                  .tempoDaRequisicao(dataAccessResponse.getTempoDaRequisicao())
                                  .build();
  }
}
