package com.lukeware.cotacao.dto;

/**
 * @author Diego Morais
 */
public class CotacaoRequest {
  String dataInicial;
  String dataFinal;

  public CotacaoRequest(String dataInicial, String dataFinal) {
    this.dataFinal = dataFinal;
    this.dataInicial = dataInicial;
  }

  public String getDataFinal() {
    return dataFinal;
  }

  public String getDataInicial() {
    return dataInicial;
  }
}
