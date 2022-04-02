package com.lukeware.cotacao.dto;

import java.time.LocalDateTime;

/**
 * @author Diego Morais
 */
public final class CotacaoRequest {
  private final Double cotacaoCompra;
  private final Double cotacaoVenda;
  private final LocalDateTime dataHoraCotacao;

  public CotacaoRequest(Double cotacaoCompra, Double cotacaoVenda, LocalDateTime dataHoraCotacao) {
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

  public LocalDateTime getDataHoraCotacao() {
    return this.dataHoraCotacao;
  }
}
