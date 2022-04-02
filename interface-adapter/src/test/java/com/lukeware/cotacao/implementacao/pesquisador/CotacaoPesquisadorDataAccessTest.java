package com.lukeware.cotacao.implementacao.pesquisador;

import com.lukeware.cotacao.dto.CotacaoDataAccessResponse;
import com.lukeware.cotacao.ICotacaoPesquisadorDataAccess;
import com.lukeware.cotacao.ICotacaoPesquisadorRepository;
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
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Diego Morais
 */
@DisplayName("Testando a pesquisa das cotações registradas no banco")
class CotacaoPesquisadorDataAccessTest {

  @Mock
  ICotacaoPesquisadorRepository cotacaoDataAccessRepository;

  ICotacaoPesquisadorDataAccess cotacaoDataAccessPesquisador;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    cotacaoDataAccessPesquisador = CotacaoPesquisadorDataAccessFactory.instance().create(cotacaoDataAccessRepository);
  }

  @Test
  @DisplayName("1 - Buscando cotação do dia")
  void buscando_cotacao_do_dia() {

    final var dataAccessResponse = CotacaoDataAccessResponse.Builder.builder().cotacaoCompra(4.85).cotacaoVenda(4.95)
                                                                    .dataCotacao(LocalDate.of(2022, 4, 12))
                                                                    .dataHoraCotacao(LocalDateTime.of(2022, 4, 12, 0, 0))
                                                                    .tempoDaRequisicao(LocalDateTime.now()).build();

    Mockito.when(cotacaoDataAccessRepository.pesquisarPorData("12-04-2022"))
           .thenReturn(Optional.of(dataAccessResponse));

    Optional<CotacaoDataAccessResponse> responseOptional = cotacaoDataAccessPesquisador.pesquisarPorData("12-04-2022");

    Assertions.assertThat(responseOptional).isNotNull();
    Assertions.assertThat(responseOptional).isNotEmpty();
    Assertions.assertThat(responseOptional.isPresent()).isTrue();
    Assertions.assertThat(responseOptional.get().getCotacaoCompra()).isEqualTo(4.85);
    Assertions.assertThat(responseOptional.get().getCotacaoVenda()).isEqualTo(4.95);
    Assertions.assertThat(responseOptional.get().getDataHoraCotacao().toLocalDate())
              .isEqualTo(LocalDate.of(2022, 4, 12));
    Assertions.assertThat(responseOptional.get().getDataCotacao()).isEqualTo(LocalDate.of(2022, 4, 12));
    Assertions.assertThat(responseOptional.get().getTempoDaRequisicao().toLocalDate()).isEqualTo(LocalDate.now());

    Mockito.verify(cotacaoDataAccessRepository, Mockito.times(1)).pesquisarPorData("12-04-2022");
  }

  @Test
  @DisplayName("1 - Buscando cotação no intervalo do duas datas")
  void buscando_cotacao_no_intervalo_do_duas_datas() {
    final var dataAccessResponse = CotacaoDataAccessResponse.Builder.builder().cotacaoCompra(4.85).cotacaoVenda(4.95)
                                                                    .dataCotacao(LocalDate.of(2022, 4, 12))
                                                                    .dataHoraCotacao(LocalDateTime.of(2022, 4, 12, 0, 0))
                                                                    .tempoDaRequisicao(LocalDateTime.now()).build();

    Mockito.when(cotacaoDataAccessRepository.pesquisarPorData("01-04-2022", "12-04-2022"))
           .thenReturn(Stream.of(dataAccessResponse).collect(Collectors.toList()));

    List<CotacaoDataAccessResponse> responses = cotacaoDataAccessPesquisador.pesquisarPorData("01-04-2022", "12-04-2022");

    Assertions.assertThat(responses).isNotNull();
    Assertions.assertThat(responses).isNotEmpty();
    Assertions.assertThat(responses.get(0).getCotacaoCompra()).isEqualTo(4.85);
    Assertions.assertThat(responses.get(0).getCotacaoVenda()).isEqualTo(4.95);
    Assertions.assertThat(responses.get(0).getDataHoraCotacao().toLocalDate())
              .isEqualTo(LocalDate.of(2022, 4, 12));
    Assertions.assertThat(responses.get(0).getDataCotacao()).isEqualTo(LocalDate.of(2022, 4, 12));
    Assertions.assertThat(responses.get(0).getTempoDaRequisicao().toLocalDate()).isEqualTo(LocalDate.now());

    Mockito.verify(cotacaoDataAccessRepository, Mockito.times(1)).pesquisarPorData("01-04-2022", "12-04-2022");
  }
}