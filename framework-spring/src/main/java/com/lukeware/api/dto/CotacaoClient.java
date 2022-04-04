package com.lukeware.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * @author Diego Morais
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CotacaoClient {
  private double cotacaoCompra;
  private double cotacaoVenda;
  private String dataHoraCotacao;
}
