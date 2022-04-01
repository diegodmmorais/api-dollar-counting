package com.lukeware.entities;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Diego Morais
 */
final class Cotacao implements ICotacao {
  private double cotacaoCompra;
  private double cotacaoVenda;
  private LocalDateTime dataHoraCotacao;

  Cotacao(double cotacaoCompra, double cotacaoVenda, LocalDateTime dataHoraCotacao) {
    this.cotacaoCompra = cotacaoCompra;
    this.cotacaoVenda = cotacaoVenda;
    this.dataHoraCotacao = dataHoraCotacao;
  }

  @Override
  public double getCotacaoCompra() {
    return cotacaoCompra;
  }

  @Override
  public double getCotacaoVenda() {
    return cotacaoVenda;
  }

  @Override
  public LocalDateTime getDataHoraCotacao() {
    return dataHoraCotacao;
  }

  @Override
  public String toString() {
    return "Cotacao{" +
        "cotacaoCompra=" + cotacaoCompra +
        ", cotacaoVenda=" + cotacaoVenda +
        ", dataHoraCotacao=" + dataHoraCotacao +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cotacao cotacao = (Cotacao) o;
    return Double.compare(cotacao.cotacaoCompra, cotacaoCompra) == 0 && Double.compare(cotacao.cotacaoVenda, cotacaoVenda) == 0 && Objects.equals(dataHoraCotacao, cotacao.dataHoraCotacao);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cotacaoCompra, cotacaoVenda, dataHoraCotacao);
  }
}
