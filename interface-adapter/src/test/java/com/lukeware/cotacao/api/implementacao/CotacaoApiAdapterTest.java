package com.lukeware.cotacao.api.implementacao;

import com.lukeware.cotacao.ICotacaoApiAdapter;
import com.lukeware.cotacao.api.IPesquisadorCotacaoApi;
import com.lukeware.cotacao.dto.CotacaoApiResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Diego Morais
 */
@DisplayName("Testando o adaptador responsável por buscar as cotações no banco central")
class CotacaoApiAdapterTest {

  @Mock
  IPesquisadorCotacaoApi pesquisadorCotacaoApi;

  private ICotacaoApiAdapter cotacaoApiAdapter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    this.cotacaoApiAdapter = CotacaoApiAdapterFactory.instance().create(pesquisadorCotacaoApi);
  }

  @Test
  @DisplayName("1 - buscar a contação do dia no banco central")
  void buscar_a_contacao_do_dia_no_banco_central() {

    final var cotacaoApiResponse = new CotacaoApiResponse(4.85,
                                                          4.95,
                                                          LocalDateTime.of(2022, 4, 12, 0, 0, 0));
    Mockito.when(pesquisadorCotacaoApi.buscar("12-04-2022")).thenReturn(Optional.of(cotacaoApiResponse));

    final var otpCotacaoApiResponse = cotacaoApiAdapter.buscar("12-04-2022");

    Assertions.assertThat(otpCotacaoApiResponse).isNotNull().isNotEmpty();
    Assertions.assertThat(otpCotacaoApiResponse.isPresent()).isTrue();
    Assertions.assertThat(otpCotacaoApiResponse.get()).isNotNull();
    Assertions.assertThat(otpCotacaoApiResponse.get().getDataHoraCotacao())
              .isNotNull()
              .isEqualTo(LocalDateTime.of(2022, 4, 12, 0, 0, 0));
    Assertions.assertThat(otpCotacaoApiResponse.get().getCotacaoCompra()).isNotNull().isEqualTo(4.85);
    Assertions.assertThat(otpCotacaoApiResponse.get().getCotacaoVenda()).isNotNull().isEqualTo(4.95);


  }
}