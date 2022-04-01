package com.lukeware.cotacao;

import java.util.Optional;

/**
 * @author Diego Morais
 */
public interface ICotacaoDataAccess {
  Optional<ICotacaoMapper> buscar(CotacaoDataAccessRequest cotacaoDataAccessRequest);

  void salvar(CotacaoDataAccessRequest cotacaoDataAccessRequest);
}
