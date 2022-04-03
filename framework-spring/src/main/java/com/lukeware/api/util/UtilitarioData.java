package com.lukeware.api.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Diego Morais
 */
public final class UtilitarioData {

  private UtilitarioData() {
    super();
  }

  public static LocalDateTime paraDataEHora(String data, String formatacao) {
    return LocalDateTime.parse(data, DateTimeFormatter.ofPattern(formatacao));
  }

  public static LocalDateTime paraDataEHora(String data) {
    if (data.length() == 22) {
      return paraDataEHora(data, TipoFormatacao.DATA_HORA_MINUTO_SEGUNDO_MILE_2.toString());
    }else if (data.length() == 21) {
      return paraDataEHora(data, TipoFormatacao.DATA_HORA_MINUTO_SEGUNDO_MILE_3.toString());
    }
    return paraDataEHora(data, TipoFormatacao.DATA_HORA_MINUTO_SEGUNDO_MILE_1.toString());
  }

  public enum TipoFormatacao {

    DATA_HORA_MINUTO_SEGUNDO_MILE_1("yyyy-MM-dd HH:mm:ss.SSS"),
    DATA_HORA_MINUTO_SEGUNDO_MILE_2("yyyy-MM-dd HH:mm:ss.SS"),
    DATA_HORA_MINUTO_SEGUNDO_MILE_3("yyyy-MM-dd HH:mm:ss.S");

    private final String formatacao;

    TipoFormatacao(String formatacao) {
      this.formatacao = formatacao;
    }

    @Override
    public String toString() {
      return formatacao;
    }
  }

}
