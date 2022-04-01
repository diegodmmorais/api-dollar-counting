package com.lukeware.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Diego Morais
 */
final class Cotacao implements ICotacao {
  private Double cotacaoCompra;
  private Double cotacaoVenda;
  private LocalDateTime dataHoraCotacao;

  Cotacao(Double cotacaoCompra, Double cotacaoVenda, LocalDateTime dataHoraCotacao) {
    this.cotacaoCompra = cotacaoCompra;
    this.cotacaoVenda = cotacaoVenda;
    this.dataHoraCotacao = dataHoraCotacao;
  }

  @Override
  public Double getCotacaoCompra() {
    return cotacaoCompra;
  }

  @Override
  public Double getCotacaoVenda() {
    return cotacaoVenda;
  }

  @Override
  public LocalDateTime getDataHoraCotacao() {
    return dataHoraCotacao;
  }

  @Override
  public void validarInformacoes() {
    Objects.requireNonNull(this.cotacaoCompra, "Cotação de compra é obrigatório");
    Objects.requireNonNull(this.cotacaoVenda, "Cotação de venda é obrigatório");
    Objects.requireNonNull(this.dataHoraCotacao, "Data da cotação é obrigatório");
  }

  @Override
  public LocalDate getDataCotacao() {
    return this.dataHoraCotacao.toLocalDate();
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
