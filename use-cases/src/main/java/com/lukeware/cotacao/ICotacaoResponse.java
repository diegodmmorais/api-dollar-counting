package com.lukeware.cotacao;

import java.time.LocalDateTime;

/**
 * @author Diego Morais
 */
public interface ICotacaoResponse {
  Double getCotacaoCompra();

  Double getCotacaoVenda();

  LocalDateTime getDataHoraCotacao();
}
