package com.lukeware.cotacao;

import com.lukeware.cotacao.dto.CotacaoDataAccessRequest;

/**
 * @author Diego Morais
 */
public interface IRegistradorCotacaoDataAccess {
  void registrar(CotacaoDataAccessRequest cotacaoDataAccessRequest);
}
