package com.lukeware.repository;

import com.lukeware.cotacao.dto.CotacaoDataAccessResponse;
import com.lukeware.cotacao.repository.ICotacaoPesquisadorRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Diego Morais
 */
@Component
public class PesquisadorCotacaoRepositorio implements ICotacaoPesquisadorRepository {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
  private ICotacaoRepository cotacaoRepository;

  public PesquisadorCotacaoRepositorio(ICotacaoRepository cotacaoRepository) {
    this.cotacaoRepository = cotacaoRepository;
  }

  @Override
  public Optional<CotacaoDataAccessResponse> pesquisarPorData(String dataDaCotacao) {
    return this.cotacaoRepository.findByDataCotacao(LocalDate.parse(dataDaCotacao, FORMATTER)).map(this::toResponse);
  }

  @Override
  public List<CotacaoDataAccessResponse> pesquisarPorData(String dataIncial, String dataFinal) {
    final var dataInicialCotacao = LocalDate.parse(dataIncial, FORMATTER);
    final var dataFinalCotacao = LocalDate.parse(dataFinal, FORMATTER);
    return this.cotacaoRepository.findByDataCotacao(dataInicialCotacao, dataFinalCotacao, Sort.by(Sort.Order.desc("dataCotacao")))
                                 .stream()
                                 .map(this::toResponse)
                                 .collect(Collectors.toList());
  }

  private CotacaoDataAccessResponse toResponse(CotacaoMapper cotacaoMapper) {
    return CotacaoDataAccessResponse.Builder
        .builder()
        .cotacaoCompra(cotacaoMapper.getCotacaoCompra())
        .cotacaoVenda(cotacaoMapper.getCotacaoVenda())
        .dataHoraCotacao(cotacaoMapper.getDataHoraCotacao())
        .dataCotacao(cotacaoMapper.getDataCotacao())
        .tempoDaRequisicao(cotacaoMapper.getTempoDaRequisicao())
        .build();
  }

}
