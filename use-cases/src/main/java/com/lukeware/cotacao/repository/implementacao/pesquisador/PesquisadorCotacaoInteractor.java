package com.lukeware.cotacao.repository.implementacao.pesquisador;

import com.lukeware.cotacao.ICotacaoPesquisadorDataAccess;
import com.lukeware.cotacao.IPesquisadorCotacao;
import com.lukeware.cotacao.dto.CotacaoDataAccessResponse;
import com.lukeware.cotacao.dto.CotacaoResponse;
import com.lukeware.utils.IValidadorDeData;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Diego morais
 */
final class PesquisadorCotacaoInteractor implements IPesquisadorCotacao {
  private final ICotacaoPesquisadorDataAccess dataAccessPesquisador;
  private final IValidadorDeData validadorDeData;

  public PesquisadorCotacaoInteractor(ICotacaoPesquisadorDataAccess dataAccessPesquisador, IValidadorDeData validadorDeData) {
    this.dataAccessPesquisador = dataAccessPesquisador;
    this.validadorDeData = validadorDeData;
  }

  @Override
  public List<CotacaoResponse> pesquisar(String dataIncial, String dataFinal) {
    validarDatas(dataIncial, dataFinal);
    return this.dataAccessPesquisador.pesquisarPorData(dataIncial, dataFinal)
                                     .stream()
                                     .map(this::coverterParaResponse)
                                     .collect(Collectors.toList());
  }

  private void validarDatas(String dataIncial, String dataFinal) {
    if (!validadorDeData.dataEMenor(dataIncial, dataFinal)) {
      throw new PesquisadorCotacaoException("Data usada para pesquisa está inválida dd-MM-yyyy(12-04-2022). Data inicial deverá ser menor igual a data final.");
    }

    if (validadorDeData.quantidadeDiasEntreDatas(dataIncial, dataFinal) > 30) {
      throw new PesquisadorCotacaoException("O intervalo entre as datas está maior que 30 dias.");
    }
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
