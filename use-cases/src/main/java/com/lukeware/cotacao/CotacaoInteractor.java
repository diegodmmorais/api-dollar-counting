package com.lukeware.cotacao;

import com.lukeware.entities.CotacaoBuilder;

import java.util.Optional;

/**
 * @author Diego Morais
 */
class CotacaoInteractor implements ICotacaoIteractorCreate {

  private final ICotacaoDataAccess cotacaoDataAccess;
  private final ICotacaoAdapter cotacaoAdapter;

  CotacaoInteractor(ICotacaoDataAccess cotacaoDataAccess, ICotacaoAdapter cotacaoAdapter) {
    this.cotacaoDataAccess = cotacaoDataAccess;
    this.cotacaoAdapter = cotacaoAdapter;
  }

  @Override
  public void criar(CotacaoRequest cotacaoRequest) {
    this.cotacaoAdapter.buscar(cotacaoRequest).forEach(this::criar);
  }

  private void criar(ICotacaoResponse cotacaoResponse) {
    CotacaoDataAccessRequest cotacaoDataAccessRequest = null;

    Optional<ICotacaoMapper> optCotacao = this.cotacaoDataAccess.buscar(cotacaoDataAccessRequest);

    if (!optCotacao.isPresent()) {
      var cotacao = CotacaoBuilder.builder()
                                  .cotacaoCompra(cotacaoResponse.getCotacaoCompra())
                                  .cotacaoVenda(cotacaoResponse.getCotacaoVenda())
                                  .dataHoraCotacao(cotacaoResponse.getDataHoraCotacao())
                                  .build();

      cotacao.validarInformacoes();
      this.cotacaoDataAccess.salvar(cotacaoDataAccessRequest);
    }
  }
}
