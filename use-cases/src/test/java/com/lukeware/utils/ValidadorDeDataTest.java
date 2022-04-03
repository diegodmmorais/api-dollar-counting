package com.lukeware.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

/**
 * @author Diego Morais
 */
@DisplayName("Testando o validador de datas")
class ValidadorDeDataTest {

  @Test
  @DisplayName("1 - validando a data quando chega nulo")
  void validando_a_data_quando_chega_nulo() {
    final var validadorDeData = ValidadorDeDataFactory.instance().create(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    Assertions.assertThat(validadorDeData.validar(null)).isFalse();
  }

  @Test
  @DisplayName("2 - validando a data quando chega vazio")
  void validando_a_data_quando_chega_vazio() {
    final var validadorDeData = ValidadorDeDataFactory.instance().create(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    Assertions.assertThat(validadorDeData.validar("")).isFalse();
  }

  @Test
  @DisplayName("3 - validando a data quando chega espaço")
  void validando_a_data_quando_chega_espaco() {
    final var validadorDeData = ValidadorDeDataFactory.instance().create(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    Assertions.assertThat(validadorDeData.validar("      ")).isFalse();
  }

  @Test
  @DisplayName("4 - validando a data quando chega com outra formatação")
  void validando_a_data_quando_chega_com_outra_formatacao() {
    final var validadorDeData = ValidadorDeDataFactory.instance().create(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    Assertions.assertThat(validadorDeData.validar("2022-04-12 01:00")).isFalse();
  }

  @Test
  @DisplayName("5 - validando a data")
  void validando_a_data() {
    final var validadorDeData = ValidadorDeDataFactory.instance().create(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    Assertions.assertThat(validadorDeData.validar("12-04-2022")).isTrue();
  }

  @Test
  @DisplayName("6 - validando se uma data é menor que a outra")
  void validando_se_uma_data_e_menor_que_a_outra() {
    final var validadorDeData = ValidadorDeDataFactory.instance().create(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    Assertions.assertThat(validadorDeData.dataEMenor("01-04-2022", "12-04-2022")).isTrue();
  }

  @Test
  @DisplayName("7 - validando quando uma data é igual a outra")
  void validando_quando_uma_data_e_igual_a_outra() {
    final var validadorDeData = ValidadorDeDataFactory.instance().create(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    Assertions.assertThat(validadorDeData.dataEMenor("12-04-2022", "01-04-2022")).isFalse();
  }

  @Test
  @DisplayName("8 - validando a quantidade de dias entre duas datas")
  void validando_a_quantidade_de_dias_entre_duas_datas() {
    final var validadorDeData = ValidadorDeDataFactory.instance().create(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    Assertions.assertThat(validadorDeData.quantidadeDiasEntreDatas("01-04-2022", "12-04-2022")).isEqualTo(11);
  }

  @Test
  @DisplayName("9 - validando a quantidade de dias entre duas datas com uma data inválida")
  void validando_a_quantidade_de_dias_entre_duas_datas_com_uma_data_inicial_invalida() {
    final var validadorDeData = ValidadorDeDataFactory.instance().create(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    Assertions.assertThatThrownBy(() -> validadorDeData.quantidadeDiasEntreDatas("32-04-2022", "12-04-2022"))
              .isInstanceOf(IllegalArgumentException.class)
              .hasMessageContaining("Data inválida: 32-04-2022, 12-04-2022");
  }

  @Test
  @DisplayName("9 - validando a quantidade de dias entre duas datas com uma data inválida")
  void validando_a_quantidade_de_dias_entre_duas_datas_com_uma_data_final_invalida() {
    final var validadorDeData = ValidadorDeDataFactory.instance().create(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    Assertions.assertThatThrownBy(() -> validadorDeData.quantidadeDiasEntreDatas("01-04-2022", "34-04-2022"))
              .isInstanceOf(IllegalArgumentException.class)
              .hasMessageContaining("Data inválida: 01-04-2022, 34-04-2022");
  }
}