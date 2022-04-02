package com.lukeware.cotacao.implementacao.dto;

/**
 * @author Diego Morais
 */
public final class CotacaoRequest {
  private final Double cotacaoCompra;
  private final Double cotacaoVenda;
  private final String dataHoraCotacao;

  public CotacaoRequest(Double cotacaoCompra, Double cotacaoVenda, String dataHoraCotacao) {
    this.cotacaoCompra = cotacaoCompra;
    this.cotacaoVenda = cotacaoVenda;
    this.dataHoraCotacao = dataHoraCotacao;
  }

  public Double getCotacaoCompra() {
    return this.cotacaoCompra;
  }

  public Double getCotacaoVenda() {
    return this.cotacaoVenda;
  }

  public String getDataHoraCotacao() {
    return this.dataHoraCotacao;
  }
}
