package com.lukeware.repository;

import com.lukeware.cotacao.dto.CotacaoDataAccessRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Diego Morais
 */
@DisplayName("Testando a pesistência da cotação")
class CotacaoRegistradorRepositoryTest {

  @Mock
  ICotacaoRepository cotacaoRepository;

  @InjectMocks
  RegistradorCotacaoRepositorio cotacaoRegistradorRepository;

  @Captor
  ArgumentCaptor<CotacaoMapper> cotacaoMapper;


  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("1 - registrar cotação no banco de dados")
  void registrar() {

    final var accessRequest = CotacaoDataAccessRequest.Builder
        .builder()
        .cotacaoCompra(4.85)
        .cotacaoVenda(4.95)
        .dataCotacao(LocalDate.of(2022, 4, 12))
        .dataHoraCotacao(LocalDateTime.of(2022, 4, 12, 0, 0))
        .tempoDaRequisicao(LocalDateTime.now())
        .build();

    cotacaoRegistradorRepository.registrar(accessRequest);

    Mockito.verify(cotacaoRepository).save(cotacaoMapper.capture());

    final var mapperValue = cotacaoMapper.getValue();
    Assertions.assertThat(mapperValue.getCotacaoCompra()).isEqualTo(4.85);
    Assertions.assertThat(mapperValue.getCotacaoVenda()).isEqualTo(4.95);
    Assertions.assertThat(mapperValue.getDataCotacao()).isEqualTo(LocalDate.of(2022, 4, 12));
    Assertions.assertThat(mapperValue.getDataHoraCotacao()).isEqualTo(LocalDateTime.of(2022, 4, 12, 0, 0));
    Assertions.assertThat(mapperValue.getTempoDaRequisicao().toLocalDate()).isEqualTo(LocalDate.now());
  }
}