package com.lukeware.cotacao.implementacao;

import com.lukeware.cotacao.implementacao.dto.CotacaoResponse;

import java.util.Optional;

/**
 * @author Diego Morais
 */
public interface ICotacaoAdapter {
  Optional<CotacaoResponse> buscar(String dataCotacao);
}
