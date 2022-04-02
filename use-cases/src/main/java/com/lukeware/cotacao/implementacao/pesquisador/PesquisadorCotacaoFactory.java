package com.lukeware.cotacao.implementacao.pesquisador;

import com.lukeware.cotacao.ICotacaoPesquisadorDataAccess;
import com.lukeware.cotacao.IPesquisadorCotacao;
import com.lukeware.utils.ValidadorDeDataFactory;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class PesquisadorCotacaoFactory {

  private static PesquisadorCotacaoFactory pesquisadorCotacaoFactory;

  private PesquisadorCotacaoFactory() {
    super();
  }

  public static PesquisadorCotacaoFactory instance() {
    if (Objects.isNull(pesquisadorCotacaoFactory)) {
      synchronized (PesquisadorCotacaoFactory.class) {
        if (Objects.isNull(pesquisadorCotacaoFactory)) {
          pesquisadorCotacaoFactory = new PesquisadorCotacaoFactory();
        }
      }
    }
    return pesquisadorCotacaoFactory;
  }

  public IPesquisadorCotacao create(final ICotacaoPesquisadorDataAccess dataAccessPesquisador) {
    return new PesquisadorCotacaoInteractor(dataAccessPesquisador, ValidadorDeDataFactory.instance()
                                                                                         .create(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
  }
}
