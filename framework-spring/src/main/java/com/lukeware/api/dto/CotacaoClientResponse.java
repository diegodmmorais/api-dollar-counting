package com.lukeware.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Diego Morais
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CotacaoClientResponse {
  @JsonProperty("value")
  List<CotacaoClient> cotacoes;
}
