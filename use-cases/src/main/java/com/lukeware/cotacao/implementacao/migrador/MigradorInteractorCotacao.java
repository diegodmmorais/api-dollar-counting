package com.lukeware.cotacao.implementacao.migrador;

import com.lukeware.cotacao.IMigradorCotacao;
import com.lukeware.cotacao.dto.CotacaoRequest;
import com.lukeware.cotacao.IAdapterCotacao;
import com.lukeware.cotacao.IRegistradorCotacaoDataAccess;
import com.lukeware.cotacao.IRegistradorCotacao;
import com.lukeware.cotacao.dto.CotacaoResponse;
import com.lukeware.cotacao.IPesquisadorCotacaoDataAccess;

/**
 * @author Diego Morais
 */
final class MigradorInteractorCotacao implements IMigradorCotacao {

  private final IRegistradorCotacaoDataAccess dataAccessRegistrador;
  private final IPesquisadorCotacaoDataAccess dataAccessPesquisador;
  private final IAdapterCotacao cotacaoAdapter;
  private final IRegistradorCotacao cotacaoRegistrador;

  public MigradorInteractorCotacao(IRegistradorCotacaoDataAccess dataAccessRegistrador,
                                   IAdapterCotacao cotacaoAdapter,
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

  private void registrar(CotacaoResponse cotacaoResponse) {
    this.cotacaoRegistrador.registrar(new CotacaoRequest(cotacaoResponse.getCotacaoCompra(),
                                                         cotacaoResponse.getCotacaoVenda(),
                                                         cotacaoResponse.getDataHoraCotacaoString()));
  }
}
