package com.lukeware.cotacao.controller.implementacao.pesquisador;

import com.lukeware.cotacao.IPesquisadorCotacao;
import com.lukeware.cotacao.controller.IPesquisadorCotacaoController;
import com.lukeware.cotacao.dto.CotacaoResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Diego Morais
 */
@DisplayName("Testando a pesquisa de contações pelo controller")
class PesquisadorCotacaoControllerTest {

  @Mock
  IPesquisadorCotacao pesquisadorCotacao;

  IPesquisadorCotacaoController pesquisadorCotacaoController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    this.pesquisadorCotacaoController = PesquisadorCotacaoControllerFactory.instance().create(pesquisadorCotacao);
  }

  @Test
  @DisplayName("1 - Pesquisando cotação entre datas")
  void pesquisando_cotacao_entre_datas() {
    final var cotacaoResponse = CotacaoResponse.Builder
        .builder()
        .cotacaoCompra(4.85)
        .cotacaoVenda(4.95)
        .dataCotacao(LocalDate.of(2022, 4, 12))
        .dataHoraCotacao(LocalDateTime.of(2022, 4, 12, 0, 0))
        .tempoDaRequisicao(LocalDateTime.now())
        .build();
    final var cotacaoResponses = Stream.of(cotacaoResponse).collect(Collectors.toList());

    Mockito.when(pesquisadorCotacao.pesquisar("01-04-2022", "12-04-2022")).thenReturn(cotacaoResponses);

    final var responses = pesquisadorCotacaoController.pesquisar("01-04-2022", "12-04-2022");

    Assertions.assertThat(responses).isNotNull().isNotEmpty().hasSize(1);
    Assertions.assertThat(responses.get(0)).isNotNull();
    Assertions.assertThat(responses.get(0).getCotacaoCompra()).isNotNull().isEqualTo(4.85);
    Assertions.assertThat(responses.get(0).getCotacaoVenda()).isNotNull().isEqualTo(4.95);
    Assertions.assertThat(responses.get(0).getDataCotacao())
              .isNotNull()
              .isEqualTo(LocalDate.of(2022, 4, 12));
    Assertions.assertThat(responses.get(0).getDataHoraCotacao().toLocalDate())
              .isNotNull()
              .isEqualTo(LocalDate.of(2022, 4, 12));
    Assertions.assertThat(responses.get(0).getTempoDaRequisicao().toLocalDate())
              .isNotNull()
              .isEqualTo(LocalDate.now());


  }
}