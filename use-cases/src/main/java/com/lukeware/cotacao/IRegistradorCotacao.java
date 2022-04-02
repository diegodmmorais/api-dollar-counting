package com.lukeware.cotacao;

import com.lukeware.cotacao.dto.CotacaoRequest;

/**
 * @author Diego Morais
 */
public interface IRegistradorCotacao {
  void registrar(final CotacaoRequest cotacaoRequest);
}
