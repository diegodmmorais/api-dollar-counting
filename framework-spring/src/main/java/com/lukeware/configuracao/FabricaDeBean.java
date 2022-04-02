package com.lukeware.configuracao;

import com.lukeware.cotacao.ICotacaoApiAdapter;
import com.lukeware.cotacao.IMigradorCotacaoInteractor;
import com.lukeware.cotacao.IPesquisadorCotacao;
import com.lukeware.cotacao.IPesquisadorCotacaoDataAccess;
import com.lukeware.cotacao.IRegistradorCotacao;
import com.lukeware.cotacao.IRegistradorCotacaoDataAccess;
import com.lukeware.cotacao.api.IPesquisadorCotacaoApi;
import com.lukeware.cotacao.api.implementacao.CotacaoApiAdapterFactory;
import com.lukeware.cotacao.controller.IMigradorCotacaoController;
import com.lukeware.cotacao.controller.IPesquisadorCotacaoController;
import com.lukeware.cotacao.controller.implementacao.migrador.MigradorCotacaoControllerFactory;
import com.lukeware.cotacao.controller.implementacao.pesquisador.PesquisadorCotacaoControllerFactory;
import com.lukeware.cotacao.implementacao.migrador.MigradorCotacaoFactory;
import com.lukeware.cotacao.implementacao.pesquisador.PesquisadorCotacaoFactory;
import com.lukeware.cotacao.implementacao.registrador.RegistradorCotacaoFactory;
import com.lukeware.cotacao.repository.ICotacaoPesquisadorRepository;
import com.lukeware.cotacao.repository.IRegistradorCotacaoRepository;
import com.lukeware.cotacao.repository.implementacao.pesquisador.PesquisadorCotacaoDataAccessFactory;
import com.lukeware.cotacao.repository.implementacao.registrador.RegistradorCotacaoDataAccessFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Diego Morais
 */
@Configuration
final class FabricaDeBean {

  @Autowired
  ICotacaoPesquisadorRepository cotacaoPesquisadorRepository;

  @Autowired
  IRegistradorCotacaoRepository cotacaoRegistradorRepository;

  @Autowired
  IPesquisadorCotacaoApi pesquisadorCotacaoApi;

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

  @Bean
  public IRegistradorCotacaoDataAccess registradorCotacaoDataAccess() {
    return RegistradorCotacaoDataAccessFactory.instance().create(cotacaoRegistradorRepository);
  }

  @Bean
  public ICotacaoApiAdapter cotacaoApiAdapter() {
    return CotacaoApiAdapterFactory.instance().create(pesquisadorCotacaoApi);
  }

  @Bean
  public IRegistradorCotacao registradorCotacao(IRegistradorCotacaoDataAccess registradorCotacaoDataAccess) {
    return RegistradorCotacaoFactory.instace().create(registradorCotacaoDataAccess);
  }

  @Bean
  public IMigradorCotacaoInteractor migradorCotacaoInteractor(IRegistradorCotacaoDataAccess registradorCotacaoDataAccess,
                                                              ICotacaoApiAdapter cotacaoApiAdapter,
                                                              IRegistradorCotacao registradorCotacao,
                                                              IPesquisadorCotacaoDataAccess pesquisadorCotacaoDataAccess) {
    return MigradorCotacaoFactory.instance()
                                 .create(registradorCotacaoDataAccess,
                                         cotacaoApiAdapter,
                                         registradorCotacao,
                                         pesquisadorCotacaoDataAccess);
  }

  @Bean
  public IMigradorCotacaoController migradorCotacaoController(IMigradorCotacaoInteractor migradorCotacaoInteractor) {
    return MigradorCotacaoControllerFactory.instance().create(migradorCotacaoInteractor);
  }

}
