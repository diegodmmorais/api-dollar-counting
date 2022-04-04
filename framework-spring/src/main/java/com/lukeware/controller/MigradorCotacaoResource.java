package com.lukeware.controller;

import com.lukeware.cotacao.controller.IMigradorCotacaoController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Diego Morais
 */
@RestController
@RequestMapping("/cotacoes")
@Tag(name = "Migrador de Cotações", description = "API migradora de cotações dolar por dia")
class MigradorCotacaoResource {

  private final IMigradorCotacaoController migradorCotacaoController;

  MigradorCotacaoResource(IMigradorCotacaoController migradorCotacaoController) {
    this.migradorCotacaoController = migradorCotacaoController;
  }

  @GetMapping("/migrar")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "400", description = "Data inválida")
  })
  @Operation(summary = "Migra uma cotação do dolar conforme a data informada", description = "API responsável por migrar cotações do banco central para o banco de dados local, Válida data inválidas", tags = {"Migrador de Cotações"})
  void migrar(
      @Parameter(description = "Data da cotação dolar desejada", required = true, example = "12-03-2022")
      @RequestParam String dataCotacao) {
    this.migradorCotacaoController.migrar(dataCotacao);
  }
}
