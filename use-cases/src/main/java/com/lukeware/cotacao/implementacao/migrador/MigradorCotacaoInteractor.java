package com.lukeware.cotacao.implementacao.migrador;

import com.lukeware.cotacao.ICotacaoApiAdapter;
import com.lukeware.cotacao.IMigradorCotacaoInteractor;
import com.lukeware.cotacao.IPesquisadorCotacaoDataAccess;
import com.lukeware.cotacao.IRegistradorCotacao;
import com.lukeware.cotacao.IRegistradorCotacaoDataAccess;
import com.lukeware.cotacao.dto.CotacaoApiResponse;
import com.lukeware.cotacao.dto.CotacaoRequest;

/**
 * @author Diego Morais
 */
final class MigradorCotacaoInteractor implements IMigradorCotacaoInteractor {

  private final IRegistradorCotacaoDataAccess dataAccessRegistrador;
  private final IPesquisadorCotacaoDataAccess dataAccessPesquisador;
  private final ICotacaoApiAdapter cotacaoAdapter;
  private final IRegistradorCotacao cotacaoRegistrador;

  public MigradorCotacaoInteractor(IRegistradorCotacaoDataAccess dataAccessRegistrador,
                                   ICotacaoApiAdapter cotacaoAdapter,
                                   IRegistradorCotacao cotacaoRegistrador,
                                   IPesquisadorCotacaoDataAccess dataAccessPesquisador) {
    this.dataAccessRegistrador = dataAccessRegistrador;
    this.cotacaoAdapter = cotacaoAdapter;
    this.cotacaoRegistrador = cotacaoRegistrador;
    this.dataAccessPesquisador = dataAccessPesquisador;
  }

  @Override
  public void migrar(String dataCotacao) {
    if (!this.dataAccessPesquisador.pesquisarPorData(dataCotacao).isPresent()) {
      registrar(dataCotacao);
    }
  }

  private void registrar(String dataCotacao) {
    this.cotacaoAdapter.buscar(dataCotacao).ifPresent(this::registrar);
  }

  private void registrar(CotacaoApiResponse cotacaoResponse) {
    this.cotacaoRegistrador.registrar(new CotacaoRequest(cotacaoResponse.getCotacaoCompra(),
                                                         cotacaoResponse.getCotacaoVenda(),
                                                         cotacaoResponse.getDataHoraCotacao()));
  }
}
