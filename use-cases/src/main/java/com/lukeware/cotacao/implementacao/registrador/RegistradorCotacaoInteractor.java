package com.lukeware.cotacao.implementacao.registrador;

import com.lukeware.cotacao.ICotacaoRegistradorDataAccess;
import com.lukeware.cotacao.dto.CotacaoDataAccessRequest;
import com.lukeware.cotacao.dto.CotacaoRequest;
import com.lukeware.cotacao.ICotacaoRegistrador;
import com.lukeware.entities.CotacaoBuilder;

import java.time.LocalDateTime;

/**
 * @author Diego Morais
 */
final class RegistradorCotacaoInteractor implements ICotacaoRegistrador {

  private final ICotacaoRegistradorDataAccess cotacaoDataAccess;

  RegistradorCotacaoInteractor(ICotacaoRegistradorDataAccess cotacaoDataAccess) {
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
