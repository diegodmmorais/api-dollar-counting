package com.lukeware.entities;

import java.time.LocalDateTime;

/**
 * @author Diego Morais
 */
public interface ICotacao {
  double getCotacaoCompra();

  double getCotacaoVenda();

  LocalDateTime getDataHoraCotacao();
}
