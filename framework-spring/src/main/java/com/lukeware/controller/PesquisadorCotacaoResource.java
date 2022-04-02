package com.lukeware.controller;

import com.lukeware.cotacao.controller.IPesquisadorCotacaoController;
import com.lukeware.cotacao.dto.CotacaoResponse;
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
public class PesquisadorCotacaoResource {

  private final IPesquisadorCotacaoController pesquisadorCotacaoController;

  public PesquisadorCotacaoResource(IPesquisadorCotacaoController pesquisadorCotacaoController) {
    this.pesquisadorCotacaoController = pesquisadorCotacaoController;
  }

  @GetMapping("/pesquisar")
  public List<CotacaoResponse> pesquisar(@RequestParam String dataInicial, @RequestParam String dataFinal) {
    return pesquisadorCotacaoController.pesquisar(dataInicial, dataFinal);
  }
}
