package com.lukeware.cotacao.repository;

import com.lukeware.cotacao.dto.CotacaoDataAccessRequest;

/**
 * @author Diego Morais
 */
public interface IRegistradorCotacaoRepository {
  void registrar(CotacaoDataAccessRequest cotacaoDataAccessRequest);
}
