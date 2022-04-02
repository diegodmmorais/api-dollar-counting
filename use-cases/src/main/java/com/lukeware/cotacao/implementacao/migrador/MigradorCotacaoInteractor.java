package com.lukeware.cotacao.implementacao.migrador;

import com.lukeware.cotacao.ICotacaoMigrador;
import com.lukeware.cotacao.dto.CotacaoRequest;
import com.lukeware.cotacao.ICotacaoAdapter;
import com.lukeware.cotacao.ICotacaoRegistradorDataAccess;
import com.lukeware.cotacao.ICotacaoRegistrador;
import com.lukeware.cotacao.dto.CotacaoResponse;
import com.lukeware.cotacao.ICotacaoPesquisadorDataAccess;

/**
 * @author Diego Morais
 */
final class MigradorCotacaoInteractor implements ICotacaoMigrador {

  private final ICotacaoRegistradorDataAccess dataAccessRegistrador;
  private final ICotacaoPesquisadorDataAccess dataAccessPesquisador;
  private final ICotacaoAdapter cotacaoAdapter;
  private final ICotacaoRegistrador cotacaoRegistrador;

  public MigradorCotacaoInteractor(ICotacaoRegistradorDataAccess dataAccessRegistrador,
                                   ICotacaoAdapter cotacaoAdapter,
                                   ICotacaoRegistrador cotacaoRegistrador,
                                   ICotacaoPesquisadorDataAccess dataAccessPesquisador) {
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
