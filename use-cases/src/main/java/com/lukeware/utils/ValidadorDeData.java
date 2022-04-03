package com.lukeware.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * @author
 */
final class ValidadorDeData implements IValidadorDeData {

  private DateTimeFormatter dateFormatter;

  public ValidadorDeData(DateTimeFormatter dateFormatter) {
    this.dateFormatter = dateFormatter;
  }

  @Override
  public boolean validar(String data) {
    try {
      if (Objects.isNull(data)) {
        return false;
      }
      LocalDate.parse(data, this.dateFormatter);
    } catch (DateTimeParseException e) {
      return false;
    }
    return true;
  }

  @Override
  public boolean dataEMenor(String dataIncial, String dataFinal) {
    final var dataIncialValidada = this.validar(dataIncial);
    final var dataFinalValidada = this.validar(dataFinal);
    if (dataIncialValidada && dataFinalValidada) {
      final var novaDataIncial = LocalDate.parse(dataIncial, this.dateFormatter);
      final var novaDataFinal = LocalDate.parse(dataFinal, this.dateFormatter);
      return novaDataIncial.compareTo(novaDataFinal) <= 0;
    }
    return false;
  }

  @Override
  public long quantidadeDiasEntreDatas(String dataIncial, String dataFinal) {
    final var dataIncialValidada = this.validar(dataIncial);
    final var dataFinalValidada = this.validar(dataFinal);
    if (dataIncialValidada && dataFinalValidada) {
      final var novaDataIncial = LocalDate.parse(dataIncial, this.dateFormatter);
      final var novaDataFinal = LocalDate.parse(dataFinal, this.dateFormatter);
      return ChronoUnit.DAYS.between(novaDataIncial, novaDataFinal);
    }
    throw new IllegalArgumentException("Data inválida: " + dataIncial + ", " + dataFinal);
  }
}
