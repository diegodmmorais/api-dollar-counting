package com.lukeware.cotacao.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Diego Morais
 */
public class CotacaoApiResponse {
  private Double cotacaoCompra;
  private Double cotacaoVenda;
  private LocalDateTime dataHoraCotacao;

  public CotacaoApiResponse(Double cotacaoCompra, Double cotacaoVenda, LocalDateTime dataHoraCotacao) {
    this.cotacaoCompra = cotacaoCompra;
    this.cotacaoVenda = cotacaoVenda;
    this.dataHoraCotacao = dataHoraCotacao;
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

  public static class Builder {
    private Double cotacaoCompra;
    private Double cotacaoVenda;
    private LocalDateTime dataHoraCotacao;

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

    public CotacaoApiResponse build() {
      return new CotacaoApiResponse(this.cotacaoCompra, this.cotacaoVenda, this.dataHoraCotacao);
    }

  }

}
