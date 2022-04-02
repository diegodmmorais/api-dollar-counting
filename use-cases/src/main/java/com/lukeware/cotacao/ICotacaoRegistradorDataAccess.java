package com.lukeware.cotacao;

import com.lukeware.cotacao.dto.CotacaoDataAccessRequest;

/**
 * @author Diego Morais
 */
public interface ICotacaoRegistradorDataAccess {
  void registrar(CotacaoDataAccessRequest cotacaoDataAccessRequest);
}
