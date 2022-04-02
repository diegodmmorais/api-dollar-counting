package com.lukeware.cotacao;

import com.lukeware.cotacao.dto.CotacaoApiResponse;

import java.util.Optional;

/**
 * @author Diego Morais
 */
public interface ICotacaoApiAdapter {
  Optional<CotacaoApiResponse> buscar(String dataCotacao);
}
