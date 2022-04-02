package com.lukeware.cotacao;

import com.lukeware.cotacao.dto.CotacaoResponse;

/**
 * @author Diego Morais
 */
public interface ICotacaoMigrador {
  void migrar(String dataCotacao);
}
