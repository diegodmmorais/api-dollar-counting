package com.lukeware.repository;

import com.lukeware.cotacao.repository.IRegistradorCotacaoRepository;
import com.lukeware.cotacao.dto.CotacaoDataAccessRequest;
import org.springframework.stereotype.Component;

/**
 * @author Diego Morais
 */
@Component
public class RegistradorCotacaoRepositorio implements IRegistradorCotacaoRepository {

  private ICotacaoRepository cotacaoRepository;

  public RegistradorCotacaoRepositorio(ICotacaoRepository cotacaoRepository) {
    this.cotacaoRepository = cotacaoRepository;
  }

  @Override
  public void registrar(CotacaoDataAccessRequest request) {
    cotacaoRepository.save(CotacaoMapper.builder()
                                        .cotacaoCompra(request.getCotacaoCompra())
                                        .cotacaoVenda(request.getCotacaoVenda())
                                        .dataCotacao(request.getDataCotacao())
                                        .dataHoraCotacao(request.getDataHoraCotacao())
                                        .tempoDaRequisicao(request.getTempoDaRequisicao())
                                        .build());
  }
}
