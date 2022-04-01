package com.lukeware.entities;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

/**
 * @author Diego Morais
 */
@DisplayName("Testando a contrução de cotacao")
class CotacaoBuilderTest {

  @Test
  @DisplayName("Construindo um objeto de cotação")
  void construindo_um_objeto_de_contacao() {
    final var cotacao = CotacaoBuilder.builder()
                                      .cotacaoCompra(4.73720)
                                      .cotacaoVenda(4.73780)
                                      .dataHoraCotacao(LocalDateTime.of(2022, 03, 31, 14, 36, 26))
                                      .build();

    Assertions.assertThat(cotacao)
              .isNotNull();

    Assertions.assertThat(cotacao.getCotacaoCompra())
              .isNotNull()
              .isEqualTo(4.73720);

    Assertions.assertThat(cotacao.getCotacaoVenda())
              .isNotNull().isEqualTo(4.73780);

    Assertions.assertThat(cotacao.getDataHoraCotacao())
              .isNotNull()
              .isEqualTo(LocalDateTime.of(2022, 03, 31, 14, 36, 26));

    Assertions.assertThat(cotacao.toString())
              .isEqualTo("Cotacao{cotacaoCompra=4.7372, cotacaoVenda=4.7378, dataHoraCotacao=2022-03-31T14:36:26}");
  }

  @Test
  @DisplayName("Verifiando se um cotação e igual a outra cotação")
  void verifiando_se_um_cotacao_e_igual_a_outra_cotacao() {
    final var cotacao1 = CotacaoBuilder.builder()
                                       .cotacaoCompra(4.73720)
                                       .cotacaoVenda(4.73780)
                                       .dataHoraCotacao(LocalDateTime.of(2022, 03, 31, 14, 36, 26))
                                       .build();

    final var cotacao2 = CotacaoBuilder.builder()
                                       .cotacaoCompra(4.73720)
                                       .cotacaoVenda(4.73780)
                                       .dataHoraCotacao(LocalDateTime.of(2022, 03, 31, 14, 36, 26))
                                       .build();

    Assertions.assertThat(cotacao1.equals(cotacao2)).isTrue();
    Assertions.assertThat(cotacao1.hashCode() == cotacao2.hashCode()).isTrue();
  }

  @Test
  @DisplayName("Verifiando se um cotação e diferente a outra cotação")
  void verifiando_se_um_cotacao_e_diferente_a_outra_cotacao() {
    final var cotacao1 = CotacaoBuilder.builder()
                                       .cotacaoCompra(4.73720)
                                       .cotacaoVenda(4.73780)
                                       .dataHoraCotacao(LocalDateTime.of(2022, 03, 31, 14, 36, 26))
                                       .build();

    final var cotacao2 = CotacaoBuilder.builder()
                                       .cotacaoCompra(5.73720)
                                       .cotacaoVenda(6.73780)
                                       .dataHoraCotacao(LocalDateTime.of(2019, 03, 30, 14, 36, 26))
                                       .build();

    Assertions.assertThat(cotacao1.equals(cotacao2)).isFalse();
    Assertions.assertThat(cotacao1.hashCode() == cotacao2.hashCode()).isFalse();
  }
}