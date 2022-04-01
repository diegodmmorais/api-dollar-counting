package com.lukeware.entities;

import java.time.LocalDateTime;

/**
 * @author Diego Morais
 */
public final class CotacaoBuilder {

  private double cotacaoCompra;
  private double cotacaoVenda;
  private LocalDateTime dataHoraCotacao;

  public static CotacaoBuilder builder() {
    return new CotacaoBuilder();
  }

  public CotacaoBuilder cotacaoCompra(double cotacaoCompra) {
    this.cotacaoCompra = cotacaoCompra;
    return this;
  }

  public CotacaoBuilder cotacaoVenda(double cotacaoVenda) {
    this.cotacaoVenda = cotacaoVenda;
    return this;
  }

  public CotacaoBuilder dataHoraCotacao(LocalDateTime dataHoraCotacao) {
    this.dataHoraCotacao = dataHoraCotacao;
    return this;
  }

  public ICotacao build() {
    return new Cotacao(this.cotacaoCompra, this.cotacaoVenda, this.dataHoraCotacao);
  }

}
