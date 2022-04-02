package com.lukeware.cotacao.controller.implementacao;

import com.lukeware.cotacao.ICotacaoMigrador;
import com.lukeware.cotacao.controller.IMigradorCotacaoController;
import com.lukeware.cotacao.controller.implementacao.migrador.MigradorCotacaoControllerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author Diego Morais
 */
@DisplayName("Testando o controlador de contações")
class MigradorCotacaoControllerTest {

  @Mock
  ICotacaoMigrador cotacaoMigrador;

  private IMigradorCotacaoController cotacaoMigradorController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    cotacaoMigradorController = MigradorCotacaoControllerFactory.instance().create(cotacaoMigrador);
  }

  @Test
  @DisplayName("1 - Testando a migração de contações")
  void testando_a_migração_de_contacoes() {

    Mockito.doNothing().when(cotacaoMigrador).migrar("12-04-2022");

    cotacaoMigradorController.migrar("12-04-2022");

    Mockito.verify(cotacaoMigrador, Mockito.times(1)).migrar("12-04-2022");

  }

}