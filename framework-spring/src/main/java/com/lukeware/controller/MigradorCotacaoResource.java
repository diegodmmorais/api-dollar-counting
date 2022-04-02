package com.lukeware.controller;

import com.lukeware.cotacao.controller.IMigradorCotacaoController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Diego Morais
 */
@RestController
@RequestMapping("/cotacoes")
public class MigradorCotacaoResource {

  private final IMigradorCotacaoController migradorCotacaoController;

  public MigradorCotacaoResource(IMigradorCotacaoController migradorCotacaoController) {
    this.migradorCotacaoController = migradorCotacaoController;
  }

  @GetMapping("/migrar")
  public void migrar(@RequestParam String dataCotacao) {
    migradorCotacaoController.migrar(dataCotacao);
  }
}
