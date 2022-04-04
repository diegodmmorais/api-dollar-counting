package com.lukeware.controller;

import com.lukeware.cotacao.controller.IMigradorCotacaoController;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Diego Morais
 */
@RestController
@RequestMapping("/cotacoes")
class MigradorCotacaoResource {

  private final IMigradorCotacaoController migradorCotacaoController;

  MigradorCotacaoResource(IMigradorCotacaoController migradorCotacaoController) {
    this.migradorCotacaoController = migradorCotacaoController;
  }

  @Async
  @GetMapping("/migrar")
  void migrar(@RequestParam String dataCotacao) {
    this.migradorCotacaoController.migrar(dataCotacao);
  }
}
