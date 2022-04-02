package com.lukeware.cotacao.repository.implementacao.registrador;

import com.lukeware.cotacao.IRegistradorCotacaoDataAccess;
import com.lukeware.cotacao.repository.IRegistradorCotacaoRepository;
import com.lukeware.cotacao.dto.CotacaoDataAccessRequest;

/**
 * @author Diego Morais
 */
final class RegistradorCotacaoDataAccess implements IRegistradorCotacaoDataAccess {

  private final IRegistradorCotacaoRepository cotacaoRegistradorRepository;

  public RegistradorCotacaoDataAccess(IRegistradorCotacaoRepository cotacaoRegistradorRepository) {
    this.cotacaoRegistradorRepository = cotacaoRegistradorRepository;
  }

  @Override
  public void registrar(CotacaoDataAccessRequest cotacaoDataAccessRequest) {
    cotacaoRegistradorRepository.registrar(cotacaoDataAccessRequest);
  }
}
