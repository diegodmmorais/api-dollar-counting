package com.lukeware.cotacao.api.implementacao;

import com.lukeware.cotacao.ICotacaoApiAdapter;
import com.lukeware.cotacao.api.IPesquisadorCotacaoApi;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class CotacaoApiAdapterFactory {

  private static CotacaoApiAdapterFactory cotacaoApiAdapterFactory;

  private CotacaoApiAdapterFactory() {
    super();
  }

  public static CotacaoApiAdapterFactory instance() {
    if (Objects.isNull(cotacaoApiAdapterFactory)) {
      synchronized (CotacaoApiAdapterFactory.class) {
        if (Objects.isNull(cotacaoApiAdapterFactory)) {
          cotacaoApiAdapterFactory = new CotacaoApiAdapterFactory();
        }
      }
    }
    return cotacaoApiAdapterFactory;
  }

  public ICotacaoApiAdapter create(IPesquisadorCotacaoApi pesquisadorCotacaoApi) {
    return new CotacaoApiAdapter(pesquisadorCotacaoApi);
  }

}
