package com.lukeware.cotacao.implementacao.registrador;

import com.lukeware.cotacao.IRegistradorCotacaoDataAccess;
import com.lukeware.cotacao.dto.CotacaoDataAccessRequest;
import com.lukeware.cotacao.dto.CotacaoRequest;
import com.lukeware.cotacao.IRegistradorCotacao;
import com.lukeware.entities.CotacaoBuilder;

import java.time.LocalDateTime;

/**
 * @author Diego Morais
 */
final class RegistradorInteractorCotacao implements IRegistradorCotacao {

  private final IRegistradorCotacaoDataAccess cotacaoDataAccess;

  RegistradorInteractorCotacao(IRegistradorCotacaoDataAccess cotacaoDataAccess) {
    this.cotacaoDataAccess = cotacaoDataAccess;
  }

  @Override
  public void registrar(final CotacaoRequest cotacaoRequest) {
    var cotacao = CotacaoBuilder.builder()
                                .cotacaoCompra(cotacaoRequest.getCotacaoCompra())
                                .cotacaoVenda(cotacaoRequest.getCotacaoVenda())
                                .dataHoraCotacao(cotacaoRequest.getDataHoraCotacao())
                                .build();

    cotacao.validarInformacoes();

    final CotacaoDataAccessRequest cotacaoDataAccess = CotacaoDataAccessRequest
        .Builder.builder()
                .cotacaoCompra(cotacao.getCotacaoCompra())
                .cotacaoVenda(cotacao.getCotacaoVenda())
                .dataHoraCotacao(cotacao.getDataHoraCotacao())
                .tempoDaRequisicao(LocalDateTime.now())
                .dataCotacao(cotacao.getDataCotacao())
                .build();

    this.cotacaoDataAccess.registrar(cotacaoDataAccess);
  }
}
