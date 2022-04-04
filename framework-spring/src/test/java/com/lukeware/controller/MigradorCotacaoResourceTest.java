package com.lukeware.controller;

import com.lukeware.cotacao.controller.IMigradorCotacaoController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Diego Morais
 */
@DisplayName("Testando serviço api que migra as cotações do dia para banco local")
@ExtendWith(SpringExtension.class)
@WebMvcTest(MigradorCotacaoResource.class)
class MigradorCotacaoResourceTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  IMigradorCotacaoController migradorCotacaoController;


  @Test
  @DisplayName("1 - migrando a cotação de uma data para a banco local")
  void migrar() throws Exception {

    final var mvcResult = mockMvc.perform(get("/cotacoes/migrar").queryParam("dataCotacao", "02-03-2022"))
                                 .andExpect(status().isOk())
                                 .andReturn();

    Mockito.verify(migradorCotacaoController, Mockito.times(1)).migrar("02-03-2022");
  }
}