package com.lukeware.atualizacao;

import com.lukeware.cotacao.controller.IMigradorCotacaoController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Diego Morais
 */
@Slf4j
@Component
@EnableAsync
@EnableScheduling
public class AtualizadorDeCotacaoScheduler {

  private final IMigradorCotacaoController migradorCotacaoController;

  public AtualizadorDeCotacaoScheduler(IMigradorCotacaoController migradorCotacaoController) {
    this.migradorCotacaoController = migradorCotacaoController;
  }

  /**
   * Cofiguração padrão para atualizar a cotação diariamente, está, atualizar de segunda a sexta as 18:30
   *
   * @throws Exception
   */
  @Async
  @Scheduled(cron = "${aplicacao.atualizarCotacao.scheduled.cron:0 30 18 ? * MON,TUE,WED,THU,FRI}")
  public void atualizarCotacao() {
    try {
      migradorCotacaoController.migrar(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    } catch (Exception exception) {
      log.error("erro ocorrido {}", exception.getMessage(), exception);
    }
  }
}
