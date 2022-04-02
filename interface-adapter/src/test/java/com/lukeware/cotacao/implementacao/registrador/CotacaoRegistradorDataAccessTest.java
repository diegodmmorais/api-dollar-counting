package com.lukeware.cotacao.implementacao.registrador;

import com.lukeware.cotacao.IRegistradorCotacaoDataAccess;
import com.lukeware.cotacao.repository.IRegistradorCotacaoRepository;
import com.lukeware.cotacao.dto.CotacaoDataAccessRequest;
import com.lukeware.cotacao.repository.implementacao.registrador.RegistradorCotacaoDataAccessFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Diego Morais
 */
@DisplayName("Testando o registro de uma cotação")
class CotacaoRegistradorDataAccessTest {

  @Mock
  IRegistradorCotacaoRepository cotacaoRegistradorRepository;

  IRegistradorCotacaoDataAccess cotacaoRegistradorDataAccess;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    this.cotacaoRegistradorDataAccess = RegistradorCotacaoDataAccessFactory.instance().create(cotacaoRegistradorRepository);
  }

  @Test
  @DisplayName("1 - Registrando um cotação")
  void registrando_um_cotacao() {
    final var accessRequest = CotacaoDataAccessRequest.Builder
        .builder()
        .cotacaoCompra(4.85)
        .cotacaoVenda(4.95)
        .dataCotacao(LocalDate.of(2022, 4, 12))
        .tempoDaRequisicao(LocalDateTime.now())
        .dataHoraCotacao(LocalDateTime.of(2022, 4, 12, 0, 0))
        .build();

    Mockito.doNothing().when(cotacaoRegistradorRepository).registrar(accessRequest);

    cotacaoRegistradorDataAccess.registrar(accessRequest);

    Mockito.verify(cotacaoRegistradorRepository, Mockito.times(1)).registrar(accessRequest);


  }
}