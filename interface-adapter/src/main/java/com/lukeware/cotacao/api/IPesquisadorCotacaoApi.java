package com.lukeware.cotacao.api;

import com.lukeware.cotacao.dto.CotacaoApiResponse;

import java.util.Optional;

/**
 * @author Diego Morais
 */
public interface IPesquisadorCotacaoApi {
  Optional<CotacaoApiResponse> buscar(String dataCotacao);
}
