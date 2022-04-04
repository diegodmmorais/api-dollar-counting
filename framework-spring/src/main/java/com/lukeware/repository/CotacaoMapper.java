package com.lukeware.repository;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Diego Morais
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("cotacoes")
public class CotacaoMapper {
  @Id
  private String id;
  private Double cotacaoCompra;
  private Double cotacaoVenda;
  private LocalDate dataCotacao;
  @EqualsAndHashCode.Exclude
  private LocalDateTime dataHoraCotacao;
  @EqualsAndHashCode.Exclude
  private LocalDateTime tempoDaRequisicao;
}
