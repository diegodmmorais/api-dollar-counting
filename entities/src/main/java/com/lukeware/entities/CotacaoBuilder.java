package com.lukeware.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Diego Morais
 */
public final class CotacaoBuilder {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

  private Double cotacaoCompra;
  private Double cotacaoVenda;
  private LocalDateTime dataHoraCotacao;

  public static CotacaoBuilder builder() {
    return new CotacaoBuilder();
  }

  public CotacaoBuilder cotacaoCompra(Double cotacaoCompra) {
    this.cotacaoCompra = cotacaoCompra;
    return this;
  }

  public CotacaoBuilder cotacaoVenda(Double cotacaoVenda) {
    this.cotacaoVenda = cotacaoVenda;
    return this;
  }

  public CotacaoBuilder dataHoraCotacao(LocalDateTime dataHoraCotacao) {
    this.dataHoraCotacao = dataHoraCotacao;
    return this;
  }

  public CotacaoBuilder dataHoraCotacao(String dataHoraCotacao) {
    this.dataHoraCotacao = LocalDateTime.parse(dataHoraCotacao, FORMATTER);
    return this;
  }

  public ICotacao build() {
    return new Cotacao(this.cotacaoCompra, this.cotacaoVenda, this.dataHoraCotacao);
  }

}
