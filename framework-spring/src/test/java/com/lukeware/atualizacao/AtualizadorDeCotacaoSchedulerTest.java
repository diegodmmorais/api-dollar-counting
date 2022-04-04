package com.lukeware.atualizacao;

import com.lukeware.cotacao.controller.IMigradorCotacaoController;
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
import java.time.format.DateTimeFormatter;

/**
 * @author Diego Morais
 */
@DisplayName("Testando atualização agendada das cotações dolar")
class AtualizadorDeCotacaoSchedulerTest {

  @Mock
  IMigradorCotacaoController migradorCotacaoController;

  @InjectMocks
  AtualizadorDeCotacaoScheduler atualizadorDeCotacaoScheduler;

  @Captor
  ArgumentCaptor<String> dataCotacaoCaptor;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("1 - chamar o método de configurado para buscar a contação do dia")
  void chamar_o_metodo_de_configurado_para_buscar_a_cotação_do_dia() {
    final var dataFormatada = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    atualizadorDeCotacaoScheduler.atualizarCotacao();

    Mockito.verify(this.migradorCotacaoController).migrar(this.dataCotacaoCaptor.capture());

    final var dataCotacao = this.dataCotacaoCaptor.getValue();
    Assertions.assertThat(dataCotacao)
              .isNotNull()
              .isEqualTo(dataFormatada);
  }

  @Test
  @DisplayName("2 - chamar o método de configurado para buscar a contação do dia em caso de erro")
  void chamar_o_metodo_de_configurado_para_buscar_a_cotação_do_dia_em_caso_de_erro() {
    final var dataFormatada = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    Mockito.doThrow(new RuntimeException("Erro inesperado")).when(this.migradorCotacaoController).migrar(dataFormatada);

    atualizadorDeCotacaoScheduler.atualizarCotacao();

  }
}