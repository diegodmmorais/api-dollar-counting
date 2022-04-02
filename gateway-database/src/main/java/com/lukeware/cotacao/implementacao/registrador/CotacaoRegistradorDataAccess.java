package com.lukeware.cotacao.implementacao.registrador;

import com.lukeware.cotacao.ICotacaoRegistradorDataAccess;
import com.lukeware.cotacao.ICotacaoRegistradorRepository;
import com.lukeware.cotacao.dto.CotacaoDataAccessRequest;

/**
 * @author Diego Morais
 */
final class CotacaoRegistradorDataAccess implements ICotacaoRegistradorDataAccess {

  private final ICotacaoRegistradorRepository cotacaoRegistradorRepository;

  public CotacaoRegistradorDataAccess(ICotacaoRegistradorRepository cotacaoRegistradorRepository) {
    this.cotacaoRegistradorRepository = cotacaoRegistradorRepository;
  }

  @Override
  public void registrar(CotacaoDataAccessRequest cotacaoDataAccessRequest) {
    cotacaoRegistradorRepository.registrar(cotacaoDataAccessRequest);
  }
}
