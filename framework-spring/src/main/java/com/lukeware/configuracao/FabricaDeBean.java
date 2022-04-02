package com.lukeware.configuracao;

import com.lukeware.cotacao.IPesquisadorCotacao;
import com.lukeware.cotacao.IPesquisadorCotacaoDataAccess;
import com.lukeware.cotacao.controller.IPesquisadorCotacaoController;
import com.lukeware.cotacao.controller.implementacao.pesquisador.PesquisadorCotacaoControllerFactory;
import com.lukeware.cotacao.implementacao.pesquisador.PesquisadorCotacaoFactory;
import com.lukeware.cotacao.repository.ICotacaoPesquisadorRepository;
import com.lukeware.cotacao.repository.implementacao.pesquisador.PesquisadorCotacaoDataAccessFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Diego Morais
 */
@Configuration
class FabricaDeBean {

  @Autowired
  ICotacaoPesquisadorRepository cotacaoPesquisadorRepository;

  @Bean
  IPesquisadorCotacaoDataAccess dataAccessPesquisador() {
    return PesquisadorCotacaoDataAccessFactory.instance().create(cotacaoPesquisadorRepository);
  }

  @Bean
  IPesquisadorCotacao pesquisadorCotacao(IPesquisadorCotacaoDataAccess dataAccessPesquisador) {
    return PesquisadorCotacaoFactory.instance().create(dataAccessPesquisador);
  }

  @Bean
  IPesquisadorCotacaoController pesquisadorCotacaoController(IPesquisadorCotacao pesquisadorCotacao) {
    return PesquisadorCotacaoControllerFactory.instance().create(pesquisadorCotacao);
  }
}
