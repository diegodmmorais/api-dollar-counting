package com.lukeware.cotacao.implementacao.migrador;

import com.lukeware.cotacao.ICotacaoAdapter;
import com.lukeware.cotacao.ICotacaoDataAccessPesquisador;
import com.lukeware.cotacao.ICotacaoDataAccessRegistrador;
import com.lukeware.cotacao.ICotacaoMigrador;
import com.lukeware.cotacao.ICotacaoRegistrador;
import com.lukeware.cotacao.dto.CotacaoRequest;
import com.lukeware.cotacao.dto.CotacaoResponse;

/**
 * @author Diego Morais
 */
final class MigradorCotacaoInteractor implements ICotacaoMigrador {

  private final ICotacaoDataAccessRegistrador dataAccessRegistrador;
  private final ICotacaoDataAccessPesquisador dataAccessPesquisador;
  private final ICotacaoAdapter cotacaoAdapter;
  private final ICotacaoRegistrador cotacaoRegistrador;

  public MigradorCotacaoInteractor(ICotacaoDataAccessRegistrador dataAccessRegistrador,
                                   ICotacaoAdapter cotacaoAdapter,
                                   ICotacaoRegistrador cotacaoRegistrador,
                                   ICotacaoDataAccessPesquisador dataAccessPesquisador) {
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
