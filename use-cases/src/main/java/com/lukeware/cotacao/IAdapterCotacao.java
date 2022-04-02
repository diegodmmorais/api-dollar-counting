package com.lukeware.cotacao;

import com.lukeware.cotacao.dto.CotacaoResponse;

import java.util.Optional;

/**
 * @author Diego Morais
 */
public interface IAdapterCotacao {
  Optional<CotacaoResponse> buscar(String dataCotacao);
}
