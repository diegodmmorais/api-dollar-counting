package com.lukeware.cotacao.implementacao;

import com.lukeware.cotacao.ICotacaoAdapter;
import com.lukeware.cotacao.ICotacaoDataAccess;
import com.lukeware.cotacao.ICotacaoIteractorCreate;
import com.lukeware.cotacao.ICotacaoMapper;
import com.lukeware.cotacao.ICotacaoResponse;
import com.lukeware.cotacao.dto.CotacaoDataAccessRequest;
import com.lukeware.cotacao.dto.CotacaoRequest;
import com.lukeware.entities.CotacaoBuilder;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Diego Morais
 */
final class CotacaoInteractor implements ICotacaoIteractorCreate {

  private final ICotacaoDataAccess cotacaoDataAccess;
  private final ICotacaoAdapter cotacaoAdapter;

  CotacaoInteractor(ICotacaoDataAccess cotacaoDataAccess, ICotacaoAdapter cotacaoAdapter) {
    this.cotacaoDataAccess = cotacaoDataAccess;
    this.cotacaoAdapter = cotacaoAdapter;
  }

  @Override
  public void criar(final CotacaoRequest cotacaoRequest) {
    this.cotacaoAdapter.buscar(cotacaoRequest).forEach(this::criar);
  }

  private void criar(final ICotacaoResponse cotacaoResponse) {

    var cotacao = CotacaoBuilder.builder()
                                .cotacaoCompra(cotacaoResponse.getCotacaoCompra())
                                .cotacaoVenda(cotacaoResponse.getCotacaoVenda())
                                .dataHoraCotacao(cotacaoResponse.getDataHoraCotacao())
                                .build();

    final CotacaoDataAccessRequest cotacaoDataAccessRequest = CotacaoDataAccessRequest
        .Builder.builder()
                .cotacaoCompra(cotacaoResponse.getCotacaoCompra())
                .cotacaoVenda(cotacaoResponse.getCotacaoVenda())
                .dataHoraCotacao(cotacaoResponse.getDataHoraCotacao())
                .tempoDaRequisicao(LocalDateTime.now())
                .dataCotacao(cotacao.getDataCotacao())
                .build();

    final Optional<ICotacaoMapper> optCotacao = this.cotacaoDataAccess.buscar(cotacaoDataAccessRequest);

    if (!optCotacao.isPresent()) {
      cotacao.validarInformacoes();
      this.cotacaoDataAccess.salvar(cotacaoDataAccessRequest);
    }
  }
}
