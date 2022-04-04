package com.lukeware.controller;

import com.lukeware.cotacao.controller.IPesquisadorCotacaoController;
import com.lukeware.cotacao.dto.CotacaoResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Diego Morais
 */
@DisplayName("Testando serviço api que pesquisa as cotações do dia")
@ExtendWith(SpringExtension.class)
@WebMvcTest(PesquisadorCotacaoResource.class)
class PesquisadorCotacaoResourceTest {

  @MockBean
  IPesquisadorCotacaoController pesquisadorCotacaoController;

  @Autowired
  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
  }

  @Test
  @DisplayName("1 - buscar a cotação dolar do dia")
  void buscar_a_cotacao_dolar_do_dia() throws Exception {

    final var cotacaoResponse = CotacaoResponse.Builder.builder()
                                                       .cotacaoCompra(4.7372)
                                                       .cotacaoVenda(4.7378)
                                                       .dataCotacao(LocalDate.of(2022, 3, 31))
                                                       .dataHoraCotacao(LocalDateTime.of(2022, 3, 31, 14, 36, 26, 861))
                                                       .tempoDaRequisicao(LocalDateTime.of(2022, 4, 2, 21, 50, 41, 761))
                                                       .build();

    final var cotacaoResponses = Stream.of(cotacaoResponse).collect(Collectors.toList());

    Mockito.when(pesquisadorCotacaoController.pesquisar("02-03-2022", "01-04-2022")).thenReturn(cotacaoResponses);

    mockMvc.perform(get("/cotacoes/pesquisar")
                        .queryParam("dataInicial", "02-03-2022")
                        .queryParam("dataFinal", "01-04-2022"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$", Matchers.hasSize(1)))
           .andExpect(jsonPath("$[0].cotacaoCompra", Matchers.is(4.7372)))
           .andExpect(jsonPath("$[0].cotacaoVenda", Matchers.is(4.7378)))
           .andExpect(jsonPath("$[0].dataHoraCotacao", Matchers.is("2022-03-31T14:36:26.000000861")))
           .andExpect(jsonPath("$[0].dataCotacao", Matchers.is("2022-03-31")))
           .andExpect(jsonPath("$[0].tempoDaRequisicao", Matchers.is("2022-04-02T21:50:41.000000761")));

    Mockito.verify(pesquisadorCotacaoController, Mockito.times(1)).pesquisar("02-03-2022", "01-04-2022");
  }
}