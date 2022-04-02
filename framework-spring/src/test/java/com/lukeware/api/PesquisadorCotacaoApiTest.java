package com.lukeware.api;

import com.lukeware.api.dto.CotacaoClient;
import com.lukeware.api.dto.CotacaoClientResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Diego Morais
 */
@DisplayName("Testando a buscar de contações no serviço do banco central")
class PesquisadorCotacaoApiTest {

  @Mock
  IPesquisadorCotacaoProxy pesquisadorCotacaoProxy;

  @InjectMocks
  PesquisadorCotacaoApi pesquisadorCotacaoApi;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("1 - Buscando contação dolar do dia no banco central")
  void buscando_contacao_dolar_do_dia_no_banco_central() {

    final var cotacaoClient = new CotacaoClient(4.73720, 4.73780, "2022-04-12 14:36:26.861");
    final var clientResponse = new CotacaoClientResponse();
    clientResponse.setCotacoes(Stream.of(cotacaoClient).collect(Collectors.toList()));

    Mockito.when(this.pesquisadorCotacaoProxy.cotacaoDolarDia("%2712-04-2022%27", "json")).thenReturn(clientResponse);

    final var cotacaoApiResponse = this.pesquisadorCotacaoApi.buscar("12-04-2022");

    Assertions.assertThat(cotacaoApiResponse).isNotNull().isNotEmpty();
    Assertions.assertThat(cotacaoApiResponse.isPresent()).isTrue();
    Assertions.assertThat(cotacaoApiResponse.get()).isNotNull();
    Assertions.assertThat(cotacaoApiResponse.get().getCotacaoCompra()).isNotNull().isEqualTo(4.73720);
    Assertions.assertThat(cotacaoApiResponse.get().getCotacaoVenda()).isNotNull().isEqualTo(4.73780);
    Assertions.assertThat(cotacaoApiResponse.get().getDataHoraCotacao().toLocalDate())
              .isNotNull()
              .isEqualTo(LocalDate.of(2022, 4, 12));
  }
}