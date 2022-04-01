package com.lukeware.cotacao;

/**
 * @author Diego Morais
 */
public interface ICotacaoResponse {
  Double getCotacaoCompra();

  Double getCotacaoVenda();

  String getDataHoraCotacao();
}
