package com.lukeware.utils;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author
 */
public final class ValidadorDeDataFactory {

  private static ValidadorDeDataFactory validadorDeDataFactory;

  private ValidadorDeDataFactory() {
    super();
  }

  public static ValidadorDeDataFactory instance() {
    if (Objects.isNull(validadorDeDataFactory)) {
      synchronized (ValidadorDeDataFactory.class) {
        if (Objects.isNull(validadorDeDataFactory)) {
          validadorDeDataFactory = new ValidadorDeDataFactory();
        }
      }
    }
    return validadorDeDataFactory;
  }

  public IValidadorDeData create(DateTimeFormatter dateFormatter) {
    return new ValidadorDeData(dateFormatter);
  }
}
