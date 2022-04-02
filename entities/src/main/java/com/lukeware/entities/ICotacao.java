package com.lukeware.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Diego Morais
 */
public interface ICotacao {
  Double getCotacaoCompra();

  Double getCotacaoVenda();

  LocalDateTime getDataHoraCotacao();

  LocalDate getDataCotacao();

  void validarInformacoes();
}
