package com.lukeware.api;

import com.lukeware.api.dto.CotacaoClient;
import com.lukeware.cotacao.api.IPesquisadorCotacaoApi;
import com.lukeware.cotacao.dto.CotacaoApiResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * @author Diego Morais
 */
@Component
public class PesquisadorCotacaoApi implements IPesquisadorCotacaoApi {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
  private static final DateTimeFormatter FORMATTER_ORIGINAL = DateTimeFormatter.ofPattern("dd-MM-yyyy");
  private static final DateTimeFormatter FORMATTER_FILTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");

  private final IPesquisadorCotacaoProxy pesquisadorCotacaoProxy;

  public PesquisadorCotacaoApi(IPesquisadorCotacaoProxy pesquisadorCotacaoProxy) {
    this.pesquisadorCotacaoProxy = pesquisadorCotacaoProxy;
  }

  @Override
  public Optional<CotacaoApiResponse> buscar(String dataCotacao) {
    return this.pesquisadorCotacaoProxy.cotacaoDolarDia(dataCotacaoRequest(dataCotacao), "json")
                                       .getCotacoes()
                                       .stream()
                                       .map(this::toResponse)
                                       .findFirst();
  }

  private String dataCotacaoRequest(String dataCotacao) {
    return "%27" + LocalDate.parse(dataCotacao, FORMATTER_ORIGINAL).format(FORMATTER_FILTER) + "%27";
  }

  private CotacaoApiResponse toResponse(CotacaoClient cotacaoClient) {
    return CotacaoApiResponse.Builder.builder().cotacaoCompra(cotacaoClient.getCotacaoCompra())
                                     .cotacaoVenda(cotacaoClient.getCotacaoVenda())
                                     .dataHoraCotacao(LocalDateTime.parse(cotacaoClient.getDataHoraCotacao(), FORMATTER))
                                     .build();
  }
}
