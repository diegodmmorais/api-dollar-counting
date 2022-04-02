package com.lukeware.cotacao.implementacao.implementacao.migrador;

import com.lukeware.cotacao.implementacao.ICotacaoAdapter;
import com.lukeware.cotacao.implementacao.ICotacaoDataAccessPesquisador;
import com.lukeware.cotacao.implementacao.ICotacaoDataAccessRegistrador;
import com.lukeware.cotacao.implementacao.ICotacaoMigrador;
import com.lukeware.cotacao.implementacao.ICotacaoRegistrador;
import com.lukeware.cotacao.implementacao.dto.CotacaoRequest;
import com.lukeware.cotacao.implementacao.dto.CotacaoResponse;

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
