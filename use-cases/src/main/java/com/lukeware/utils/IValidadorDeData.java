package com.lukeware.utils;

/**
 * @author Diego Morais
 */
public interface IValidadorDeData {
  boolean validar(String data);

  boolean dataEMenor(String dataIncial, String dataFinal);

  long quantidadeDiasEntreDatas(String dataIncial, String dataFinal);
}
