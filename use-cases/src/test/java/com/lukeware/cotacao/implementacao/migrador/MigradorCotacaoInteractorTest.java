package com.lukeware.cotacao.implementacao.migrador;

import com.lukeware.cotacao.ICotacaoAdapter;
import com.lukeware.cotacao.ICotacaoDataAccessPesquisador;
import com.lukeware.cotacao.ICotacaoDataAccessRegistrador;
import com.lukeware.cotacao.ICotacaoMigrador;
import com.lukeware.cotacao.ICotacaoRegistrador;
import com.lukeware.cotacao.dto.CotacaoRequest;
import com.lukeware.cotacao.dto.CotacaoResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Diego Morais
 */
@DisplayName("Testando a migração da cotação dolar do dia")
class MigradorCotacaoInteractorTest {

  @Mock
  ICotacaoAdapter cotacaoAdapter;

  @Mock
  ICotacaoDataAccessRegistrador cotacaoDataAccess;

  @Mock
  ICotacaoRegistrador cotacaoRegistrador;

  @Mock
  ICotacaoDataAccessPesquisador dataAccessPesquisador;

  @Captor
  ArgumentCaptor<CotacaoRequest> cotacaoRequest;

  ICotacaoMigrador cotacaoMigrador;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    this.cotacaoMigrador = MigradorCotacaoFactory.instance()
                                                 .create(cotacaoDataAccess, cotacaoAdapter, cotacaoRegistrador, dataAccessPesquisador);
  }

  @Test
  @DisplayName("1 - migrando a contação dolar do dia")
  void migrando_a_contacao_dolar_do_dia() {

    final var cotacaoResponse = CotacaoResponse.Builder.builder()
                                                       .cotacaoCompra(4.85)
                                                       .cotacaoVenda(4.95)
                                                       .dataHoraCotacao(LocalDateTime.of(2022, 4, 12, 0, 0, 0))
                                                       .dataCotacao(LocalDate.of(2022, 4, 12))
                                                       .tempoDaRequisicao(LocalDateTime.now())
                                                       .build();


    Mockito.when(this.dataAccessPesquisador.pesquisarPorData("12-04-2022")).thenReturn(Optional.empty());
    Mockito.when(this.cotacaoAdapter.buscar("12-04-2022")).thenReturn(Optional.of(cotacaoResponse));

    this.cotacaoMigrador.migrar("12-04-2022");

    Mockito.verify(cotacaoRegistrador).registrar(cotacaoRequest.capture());
    Mockito.verify(dataAccessPesquisador, Mockito.times(1)).pesquisarPorData("12-04-2022");
    Mockito.verify(cotacaoAdapter, Mockito.times(1)).buscar("12-04-2022");

    final var cotacaoRequest = this.cotacaoRequest.getValue();
    Assertions.assertThat(cotacaoRequest).isNotNull();
    Assertions.assertThat(cotacaoRequest.getCotacaoCompra()).isNotNull().isEqualTo(4.85);
    Assertions.assertThat(cotacaoRequest.getCotacaoVenda()).isNotNull().isEqualTo(4.95);
    Assertions.assertThat(cotacaoRequest.getDataHoraCotacao()).isNotNull().isEqualTo("2022-04-12 00:00:00.000");


  }

}