package com.lukeware.api;

import com.lukeware.api.dto.CotacaoClient;
import com.lukeware.cotacao.api.IPesquisadorCotacaoApi;
import com.lukeware.cotacao.dto.CotacaoApiResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * @author Diego Morais
 */
@Component
public class PesquisadorCotacaoApi implements IPesquisadorCotacaoApi {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

  private final IPesquisadorCotacaoProxy pesquisadorCotacaoProxy;

  public PesquisadorCotacaoApi(IPesquisadorCotacaoProxy pesquisadorCotacaoProxy) {
    this.pesquisadorCotacaoProxy = pesquisadorCotacaoProxy;
  }

  /**
   * %2703-31-2022%27
   *
   * @param dataCotacao
   * @return
   */
  @Override
  public Optional<CotacaoApiResponse> buscar(String dataCotacao) {
    final var clientResponse = this.pesquisadorCotacaoProxy.cotacaoDolarDia("%27" + dataCotacao + "%27", "json");
    return clientResponse.getCotacoes().stream().map(this::toResponse).findFirst();
  }

  private CotacaoApiResponse toResponse(CotacaoClient cotacaoClient) {
    return CotacaoApiResponse.Builder.builder().cotacaoCompra(cotacaoClient.getCotacaoCompra())
                                     .cotacaoVenda(cotacaoClient.getCotacaoVenda())
                                     .dataHoraCotacao(LocalDateTime.parse(cotacaoClient.getDataHoraCotacao(), FORMATTER))
                                     .build();
  }
}
