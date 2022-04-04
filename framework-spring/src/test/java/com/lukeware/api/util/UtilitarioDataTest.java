package com.lukeware.api.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * @author Diego Morais
 */
@DisplayName("Testando utilitário de coversor de datas")
class UtilitarioDataTest {

  @Test
  @DisplayName("1 - converter para data e hora")
  void converter_para_data_e_hora() {
    final var dataEHora = UtilitarioData.paraDataEHora("2022-03-03 13:04:52.092");
    final var dataEHora2 = UtilitarioData.paraDataEHora("2022-03-03 13:04:52.92");
    final var dataEHora3 = UtilitarioData.paraDataEHora("2022-03-03 13:04:52.9");

    Assertions.assertThat(dataEHora.toLocalDate()).isEqualTo(LocalDate.of(2022, 3, 3));
    Assertions.assertThat(dataEHora2.toLocalDate()).isEqualTo(LocalDate.of(2022, 3, 3));
    Assertions.assertThat(dataEHora3.toLocalDate()).isEqualTo(LocalDate.of(2022, 3, 3));
  }


}