package com.lukeware.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author Diego Morais
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CotacaoClient {
  private double cotacaoCompra;
  private double cotacaoVenda;
  private String dataHoraCotacao;
}
