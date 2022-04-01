package com.lukeware.cotacao;

import com.lukeware.cotacao.dto.CotacaoRequest;

import java.util.Set;

/**
 * @author Diego Morais
 */
public interface ICotacaoAdapter {
  Set<ICotacaoResponse> buscar(CotacaoRequest cotacaoRequest);
}
