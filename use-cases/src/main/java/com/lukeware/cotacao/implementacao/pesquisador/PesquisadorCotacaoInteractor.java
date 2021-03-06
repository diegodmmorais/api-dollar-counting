package com.lukeware.cotacao.implementacao.pesquisador;

import com.lukeware.cotacao.IPesquisadorCotacao;
import com.lukeware.cotacao.IPesquisadorCotacaoDataAccess;
import com.lukeware.cotacao.dto.CotacaoDataAccessResponse;
import com.lukeware.cotacao.dto.CotacaoResponse;
import com.lukeware.utils.IValidadorDeData;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Diego morais
 */
final class PesquisadorCotacaoInteractor implements IPesquisadorCotacao {
  private final IPesquisadorCotacaoDataAccess dataAccessPesquisador;
  private final IValidadorDeData validadorDeData;

  public PesquisadorCotacaoInteractor(IPesquisadorCotacaoDataAccess dataAccessPesquisador, IValidadorDeData validadorDeData) {
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
      throw new PesquisadorCotacaoException("Data utilizada está incosistênte", "Data pode está inválida ou a data inicial está maior que a data final");
    }

    if (validadorDeData.quantidadeDiasEntreDatas(dataIncial, dataFinal) > 30) {
      throw new PesquisadorCotacaoException("Data utilizada está incosistênte", "O intervalo entre as datas está maior que 30 dias.");
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
