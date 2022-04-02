package com.lukeware.cotacao.implementacao;

import com.lukeware.cotacao.implementacao.dto.CotacaoDataAccessRequest;

/**
 * @author Diego Morais
 */
public interface ICotacaoDataAccessRegistrador {
  void salvar(CotacaoDataAccessRequest cotacaoDataAccessRequest);
}
