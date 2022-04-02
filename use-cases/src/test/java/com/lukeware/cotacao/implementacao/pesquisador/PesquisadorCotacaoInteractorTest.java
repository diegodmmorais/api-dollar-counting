package com.lukeware.cotacao.implementacao.pesquisador;

import com.lukeware.cotacao.ICotacaoDataAccessPesquisador;
import com.lukeware.cotacao.IPesquisadorCotacao;
import com.lukeware.cotacao.dto.CotacaoDataAccessResponse;
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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Diego Morais
 */
@DisplayName("Testando o pesquisador de contacao do dia")
class PesquisadorCotacaoInteractorTest {

  IPesquisadorCotacao pesquisadorCotacao;

  @Mock
  ICotacaoDataAccessPesquisador dataAccessPesquisador;


  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    this.pesquisadorCotacao = PesquisadorCotacaoFactory.instance().create(dataAccessPesquisador);
  }

  @Test
  @DisplayName("1 - Pesquisando a contação do dia")
  void pesquisando_a_contacao_do_dia() {
    final var responses = Stream.of(CotacaoDataAccessResponse.Builder.builder()
                                                                     .cotacaoCompra(4.85)
                                                                     .cotacaoVenda(4.95)
                                                                     .dataCotacao(LocalDate.of(2022, 4, 12))
                                                                     .dataHoraCotacao(LocalDateTime.of(2022, 4, 12, 0, 0))
                                                                     .tempoDaRequisicao(LocalDateTime.now())
                                                                     .build()).collect(Collectors.toList());

    Mockito.when(this.dataAccessPesquisador.pesquisarPorData("01-04-2022", "12-04-2022")).thenReturn(responses);

    List<CotacaoResponse> cotacoes = this.pesquisadorCotacao.pesquisar("01-04-2022", "12-04-2022");

    Assertions.assertThat(cotacoes).isNotNull();
    Assertions.assertThat(cotacoes).isNotEmpty();
    Assertions.assertThat(cotacoes).hasSize(1);
    Assertions.assertThat(cotacoes.get(0)).isNotNull();
    Assertions.assertThat(cotacoes.get(0).getCotacaoCompra()).isNotNull().isEqualTo(4.85);
    Assertions.assertThat(cotacoes.get(0).getCotacaoVenda()).isNotNull().isEqualTo(4.95);
    Assertions.assertThat(cotacoes.get(0).getDataCotacao())
              .isNotNull()
              .isEqualTo(LocalDate.of(2022, 4, 12));
    Assertions.assertThat(cotacoes.get(0).getDataHoraCotacao().toLocalDate())
              .isNotNull()
              .isEqualTo(LocalDate.of(2022, 4, 12));
    Assertions.assertThat(cotacoes.get(0).getTempoDaRequisicao().toLocalDate())
              .isNotNull()
              .isEqualTo(LocalDate.now());
    Mockito.verify(this.dataAccessPesquisador, Mockito.times(1)).pesquisarPorData("01-04-2022", "12-04-2022");
  }
}