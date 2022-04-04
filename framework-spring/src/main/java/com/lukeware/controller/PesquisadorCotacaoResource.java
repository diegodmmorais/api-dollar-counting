package com.lukeware.controller;

import com.lukeware.cotacao.controller.IPesquisadorCotacaoController;
import com.lukeware.cotacao.dto.CotacaoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Diego Morais
 */
@RestController
@RequestMapping("/cotacoes")
@Tag(name = "Pesquisador de Cotações", description = "API pesquisadora de cotações dolar")
class PesquisadorCotacaoResource {

  private final IPesquisadorCotacaoController pesquisadorCotacaoController;

  PesquisadorCotacaoResource(IPesquisadorCotacaoController pesquisadorCotacaoController) {
    this.pesquisadorCotacaoController = pesquisadorCotacaoController;
  }

  @GetMapping("/pesquisar")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "400", description = "Data pode está inválida"),
      @ApiResponse(responseCode = "400", description = "Data pode está inválida ou a data inicial está maior que a data final"),
      @ApiResponse(responseCode = "400", description = "O intervalo entre as datas está maior que 30 dias.")
  })
  @Operation(summary = "Pesquisar cotação do dolar conforme a data inicial e data final informada", description = "API responsável por listar as cotações no intervalo de 30 dias", tags = {"Pesquisador de Cotações"})
  List<CotacaoResponse> pesquisar(
      @Parameter(description = "Data inicial da cotação dolar desejada", required = true, example = "02-03-2022")
      @RequestParam String dataInicial,
      @Parameter(description = "Data final da cotação dolar desejada", required = true, example = "01-04-2022")
      @RequestParam String dataFinal) {
    return pesquisadorCotacaoController.pesquisar(dataInicial, dataFinal);
  }
}
