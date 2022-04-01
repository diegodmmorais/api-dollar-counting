package com.lukeware.cotacao;

import java.util.Set;

/**
 * @author Diego Morais
 */
public interface ICotacaoAdapter {
  Set<ICotacaoResponse> buscar(CotacaoRequest cotacaoRequest);
}
