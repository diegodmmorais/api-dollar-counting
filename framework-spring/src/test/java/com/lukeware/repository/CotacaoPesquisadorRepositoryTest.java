package com.lukeware.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Diego Morais
 */
@DisplayName("Testando a pesquisa das cotações no banco de dados")
class CotacaoPesquisadorRepositoryTest {

  @Mock
  ICotacaoRepository cotacaoRepository;

  @InjectMocks
  PesquisadorCotacaoRepositorio cotacaoPesquisadorRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("1 - Buscando a cotação do dia no banco")
  void buscando_a_cotacao_do_dia_no_banco() {

    final var cotacaoMapper = CotacaoMapper.builder()
                                           .cotacaoCompra(4.85)
                                           .cotacaoVenda(4.95)
                                           .dataHoraCotacao(LocalDateTime.of(2022, 4, 12, 0, 0))
                                           .dataCotacao(LocalDate.of(2022, 4, 12))
                                           .tempoDaRequisicao(LocalDateTime.now())
                                           .build();

    Mockito.when(cotacaoRepository.findByDataCotacao(Mockito.any())).thenReturn(Optional.of(cotacaoMapper));

    final var responseOptional = cotacaoPesquisadorRepository.pesquisarPorData("12-04-2022");

    Assertions.assertThat(responseOptional).isNotNull();
    Assertions.assertThat(responseOptional).isNotEmpty();
    Assertions.assertThat(responseOptional.isPresent()).isTrue();
    Assertions.assertThat(responseOptional.get()).isNotNull();
    Assertions.assertThat(responseOptional.get().getCotacaoCompra()).isNotNull().isEqualTo(4.85);
    Assertions.assertThat(responseOptional.get().getCotacaoVenda()).isNotNull().isEqualTo(4.95);
    Assertions.assertThat(responseOptional.get().getDataCotacao())
              .isNotNull()
              .isEqualTo(LocalDate.of(2022, 4, 12));
    Assertions.assertThat(responseOptional.get().getDataHoraCotacao().toLocalDate())
              .isNotNull()
              .isEqualTo(LocalDate.of(2022, 4, 12));
    Assertions.assertThat(responseOptional.get().getTempoDaRequisicao().toLocalDate())
              .isNotNull()
              .isEqualTo(LocalDate.now());
  }

  @Test
  @DisplayName("2 - Buscando a cotação entre duas datas")
  void buscando_a_cotacao_entre_duas_datas() {

    final var cotacaoMapper = CotacaoMapper.builder()
                                           .cotacaoCompra(4.85)
                                           .cotacaoVenda(4.95)
                                           .dataHoraCotacao(LocalDateTime.of(2022, 4, 12, 0, 0))
                                           .dataCotacao(LocalDate.of(2022, 4, 12))
                                           .tempoDaRequisicao(LocalDateTime.now())
                                           .build();
    final var cotacaoMappers = Stream.of(cotacaoMapper).collect(Collectors.toList());

    Mockito.when(cotacaoRepository.findByDataCotacao(Mockito.any(),
                                                     Mockito.any(),
                                                     Mockito.any())).thenReturn(cotacaoMappers);

    final var responseOptional = cotacaoPesquisadorRepository.pesquisarPorData("01-04-2022", "12-04-2022");

    Assertions.assertThat(responseOptional).isNotNull();
    Assertions.assertThat(responseOptional).isNotEmpty();
    Assertions.assertThat(responseOptional.get(0)).isNotNull();
    Assertions.assertThat(responseOptional.get(0).getCotacaoCompra()).isNotNull().isEqualTo(4.85);
    Assertions.assertThat(responseOptional.get(0).getCotacaoVenda()).isNotNull().isEqualTo(4.95);
    Assertions.assertThat(responseOptional.get(0).getDataCotacao())
              .isNotNull()
              .isEqualTo(LocalDate.of(2022, 4, 12));
    Assertions.assertThat(responseOptional.get(0).getDataHoraCotacao().toLocalDate())
              .isNotNull()
              .isEqualTo(LocalDate.of(2022, 4, 12));
    Assertions.assertThat(responseOptional.get(0).getTempoDaRequisicao().toLocalDate())
              .isNotNull()
              .isEqualTo(LocalDate.now());
  }
}