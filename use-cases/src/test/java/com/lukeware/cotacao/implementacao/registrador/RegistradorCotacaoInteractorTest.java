package com.lukeware.cotacao.implementacao.registrador;

import com.lukeware.cotacao.IRegistradorCotacao;
import com.lukeware.cotacao.IRegistradorCotacaoDataAccess;
import com.lukeware.cotacao.dto.CotacaoDataAccessRequest;
import com.lukeware.cotacao.dto.CotacaoRequest;
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

/**
 * @author Diego Morais
 */
@DisplayName("Testando as interações com a cotação")
class RegistradorCotacaoInteractorTest {

  @Mock
  IRegistradorCotacaoDataAccess cotacaoDataAccess;

  @Captor
  ArgumentCaptor<CotacaoDataAccessRequest> dataAccessCaptor;

  IRegistradorCotacao cotacaoRegistrador;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    this.cotacaoRegistrador = RegistradorCotacaoFactory.instace().create(cotacaoDataAccess);
  }

  @Test
  @DisplayName("1 - registrando uma cotação encontrando que não está registrada no banco")
  void registrar() {
    final var cotacaoRequest = new CotacaoRequest(4.85, 4.95, LocalDateTime.of(2022, 4, 12, 0, 0));

    this.cotacaoRegistrador.registrar(cotacaoRequest);

    Mockito.verify(this.cotacaoDataAccess).registrar(this.dataAccessCaptor.capture());

    final var dataAccessRequest = this.dataAccessCaptor.getValue();
    Assertions.assertThat(dataAccessRequest).isNotNull();
    Assertions.assertThat(dataAccessRequest.getCotacaoCompra()).isNotNull().isEqualTo(4.85);
    Assertions.assertThat(dataAccessRequest.getCotacaoVenda()).isNotNull().isEqualTo(4.95);
    Assertions.assertThat(dataAccessRequest.getDataCotacao())
              .isNotNull()
              .isEqualTo(LocalDate.of(2022, 4, 12));
    Assertions.assertThat(dataAccessRequest.getDataHoraCotacao().toLocalDate())
              .isNotNull()
              .isEqualTo(LocalDate.of(2022, 4, 12));
    Assertions.assertThat(dataAccessRequest.getTempoDaRequisicao().toLocalDate())
              .isNotNull()
              .isEqualTo(LocalDate.now());
  }
}