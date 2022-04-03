package com.lukeware.entities;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Diego Morais
 */
@DisplayName("Testando a contrução de cotacao")
class CotacaoBuilderTest {

  @Test
  @DisplayName("1 - construindo um objeto de cotação")
  void construindo_um_objeto_de_cotacao() {
    final var cotacao = CotacaoBuilder.builder()
                                      .cotacaoCompra(4.73720)
                                      .cotacaoVenda(4.73780)
                                      .dataHoraCotacao(LocalDateTime.of(2022, 03, 31, 14, 36, 26))
                                      .build();

    System.out.println(cotacao);

    Assertions.assertThat(cotacao)
              .isNotNull();

    Assertions.assertThat(cotacao.getCotacaoCompra())
              .isNotNull()
              .isEqualTo(4.73720);

    Assertions.assertThat(cotacao.getCotacaoVenda())
              .isNotNull()
              .isEqualTo(4.73780);

    Assertions.assertThat(cotacao.getDataHoraCotacao())
              .isNotNull()
              .isEqualTo(LocalDateTime.of(2022, 03, 31, 14, 36, 26));

    Assertions.assertThat(cotacao.getDataCotacao())
              .isNotNull()
              .isEqualTo(LocalDate.of(2022, 03, 31));

    Assertions.assertThat(cotacao.toString())
              .isEqualTo("Cotacao{cotacaoCompra=4.7372, cotacaoVenda=4.7378, dataHoraCotacao=2022-03-31T14:36:26}");
  }

  @Test
  @DisplayName("2 - verificando se um cotação e igual a outra cotação")
  void verificando_se_um_cotacao_e_igual_a_outra_cotacao() {
    final var cotacao1 = CotacaoBuilder.builder().cotacaoCompra(4.73720).cotacaoVenda(4.73780)
                                       .dataHoraCotacao("2022-03-31 14:36:26.861").build();

    final var cotacao2 = CotacaoBuilder.builder().cotacaoCompra(4.73720).cotacaoVenda(4.73780)
                                       .dataHoraCotacao("2022-03-31 14:36:26.861").build();

    Assertions.assertThat(cotacao1.equals(cotacao2)).isTrue();
    Assertions.assertThat(cotacao1.hashCode() == cotacao2.hashCode()).isTrue();
  }

  @Test
  @DisplayName("3 - verificando se um cotação e diferente a outra cotação")
  void verificando_se_um_cotacao_e_diferente_a_outra_cotacao() {
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

  @Test
  @DisplayName("4 - validando os campos obrigatórios válidos")
  void Validar_os_campos_obrigatorios_validos() {
    final var cotacao1 = CotacaoBuilder.builder()
                                       .cotacaoVenda(4.73780)
                                       .dataHoraCotacao(LocalDateTime.of(2022, 03, 31, 14, 36, 26))
                                       .build();

    final var cotacao2 = CotacaoBuilder.builder()
                                       .cotacaoCompra(5.73720)
                                       .dataHoraCotacao(LocalDateTime.of(2019, 03, 30, 14, 36, 26))
                                       .build();

    final var cotacao3 = CotacaoBuilder.builder()
                                       .cotacaoCompra(5.73720)
                                       .cotacaoVenda(4.73780)
                                       .build();

    final var cotacao4 = CotacaoBuilder.builder()
                                       .cotacaoCompra(4.73720)
                                       .cotacaoVenda(4.73780)
                                       .dataHoraCotacao(LocalDateTime.of(2022, 03, 31, 14, 36, 26))
                                       .build();

    Assertions.assertThatThrownBy(() -> cotacao1.validarInformacoes())
              .isInstanceOf(NullPointerException.class)
              .hasMessageContaining("Cotação de compra é obrigatório");

    Assertions.assertThatThrownBy(() -> cotacao2.validarInformacoes())
              .isInstanceOf(NullPointerException.class)
              .hasMessageContaining("Cotação de venda é obrigatório");

    Assertions.assertThatThrownBy(() -> cotacao3.validarInformacoes())
              .isInstanceOf(NullPointerException.class)
              .hasMessageContaining("Data da cotação é obrigatório");

    Assertions.assertThat(cotacao4)
              .isNotNull();

    Assertions.assertThat(cotacao4.getCotacaoCompra())
              .isNotNull()
              .isEqualTo(4.73720);

    Assertions.assertThat(cotacao4.getCotacaoVenda())
              .isNotNull()
              .isEqualTo(4.73780);

    Assertions.assertThat(cotacao4.getDataHoraCotacao())
              .isNotNull()
              .isEqualTo(LocalDateTime.of(2022, 03, 31, 14, 36, 26));

    Assertions.assertThat(cotacao4.toString())
              .isEqualTo("Cotacao{cotacaoCompra=4.7372, cotacaoVenda=4.7378, dataHoraCotacao=2022-03-31T14:36:26}");
  }

  @Test
  @DisplayName("5 - validar a consistência dos dados")
  void validar_a_consistencia_dos_dados() {
    final var cotacao = CotacaoBuilder.builder()
                                      .cotacaoCompra(4.73720)
                                      .cotacaoVenda(4.73780)
                                      .build();


    Assertions.assertThat(cotacao)
              .isNotNull();

    Assertions.assertThat(cotacao.getCotacaoCompra())
              .isNotNull()
              .isEqualTo(4.73720);

    Assertions.assertThat(cotacao.getCotacaoVenda())
              .isNotNull()
              .isEqualTo(4.73780);

    Assertions.assertThatThrownBy(() -> cotacao.validarInformacoes())
              .isInstanceOf(NullPointerException.class)
              .hasMessageContaining("Data da cotação é obrigatório");
  }

  @Test
  @DisplayName("6 - validando se duas contações são iguais")
  void validando_se_duas_contacoes_sao_iguais() {
    final var cotacao = CotacaoBuilder.builder()
                                      .cotacaoCompra(4.73720)
                                      .cotacaoVenda(4.73780)
                                      .dataHoraCotacao(LocalDateTime.of(2019, 03, 30, 14, 36, 26))
                                      .build();

    final var cotacao2 = CotacaoBuilder.builder()
                                       .cotacaoCompra(4.73720)
                                       .cotacaoVenda(4.73780)
                                       .dataHoraCotacao(LocalDateTime.of(2019, 03, 30, 14, 36, 26))
                                       .build();

    Assertions.assertThat(cotacao.equals(cotacao2)).isTrue();
    Assertions.assertThat(cotacao.validarInformacoes()).isTrue();
    Assertions.assertThat(cotacao2.validarInformacoes()).isTrue();
  }
}