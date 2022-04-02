package com.lukeware.cotacao.implementacao;

import com.lukeware.cotacao.implementacao.dto.CotacaoRequest;

/**
 * @author Diego Morais
 */
public interface ICotacaoRegistrador {
  void registrar(final CotacaoRequest cotacaoRequest);
}
