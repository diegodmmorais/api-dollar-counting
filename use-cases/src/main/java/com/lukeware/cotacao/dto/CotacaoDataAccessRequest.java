package com.lukeware.cotacao.dto;

import com.lukeware.entities.CotacaoBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class CotacaoDataAccessRequest {

  private Double cotacaoCompra;
  private Double cotacaoVenda;
  private LocalDateTime dataHoraCotacao;
  private LocalDate dataCotacao;
  private LocalDateTime tempoDaRequisicao;

  public CotacaoDataAccessRequest(
      Double cotacaoCompra,
      Double cotacaoVenda,
      LocalDateTime dataHoraCotacao,
      LocalDate dataCotacao,
      LocalDateTime tempoDaRequisicao
  ) {
    this.cotacaoCompra = cotacaoCompra;
    this.cotacaoVenda = cotacaoVenda;
    this.dataHoraCotacao = dataHoraCotacao;
    this.dataCotacao = dataCotacao;
    this.tempoDaRequisicao = tempoDaRequisicao;
  }

  public Double getCotacaoCompra() {
    return cotacaoCompra;
  }

  public Double getCotacaoVenda() {
    return cotacaoVenda;
  }

  public LocalDateTime getDataHoraCotacao() {
    return dataHoraCotacao;
  }

  public LocalDate getDataCotacao() {
    return dataCotacao;
  }

  public LocalDateTime getTempoDaRequisicao() {
    return tempoDaRequisicao;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CotacaoDataAccessRequest that = (CotacaoDataAccessRequest) o;
    return Objects.equals(cotacaoCompra, that.cotacaoCompra) && Objects.equals(cotacaoVenda, that.cotacaoVenda) && Objects.equals(dataCotacao, that.dataCotacao);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cotacaoCompra, cotacaoVenda, dataCotacao);
  }

  @Override
  public String toString() {
    return "CotacaoDataAccessRequest{" +
        "cotacaoCompra=" + cotacaoCompra +
        ", cotacaoVenda=" + cotacaoVenda +
        ", dataHoraCotacao=" + dataHoraCotacao +
        '}';
  }

  public static final class Builder {

    private Double cotacaoCompra;
    private Double cotacaoVenda;
    private LocalDateTime dataHoraCotacao;
    private LocalDate dataCotacao;
    private LocalDateTime tempoDaRequisicao;

    private Builder() {
      super();
    }

    public static Builder builder() {
      return new Builder();
    }

    public Builder cotacaoCompra(Double cotacaoCompra) {
      this.cotacaoCompra = cotacaoCompra;
      return this;
    }

    public Builder cotacaoVenda(Double cotacaoVenda) {
      this.cotacaoVenda = cotacaoVenda;
      return this;
    }

    public Builder dataHoraCotacao(LocalDateTime dataHoraCotacao) {
      this.dataHoraCotacao = dataHoraCotacao;
      return this;
    }

    public Builder dataCotacao(LocalDate dataCotacao) {
      this.dataCotacao = dataCotacao;
      return this;
    }

    public Builder tempoDaRequisicao(LocalDateTime tempoDaRequisicao) {
      this.tempoDaRequisicao = tempoDaRequisicao;
      return this;
    }

    public CotacaoDataAccessRequest build() {
      return new CotacaoDataAccessRequest(this.cotacaoCompra, this.cotacaoVenda, this.dataHoraCotacao, this.dataCotacao, this.tempoDaRequisicao);
    }

  }
}
