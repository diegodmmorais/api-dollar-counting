package com.lukeware.api;

import com.lukeware.api.dto.CotacaoClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Diego Morais
 */
@FeignClient(name = "${feign.cotacaoProxy.name}", url = "${feign.cotacaoProxy.url}")
public interface IPesquisadorCotacaoProxy {

  @RequestMapping("/CotacaoDolarDia(dataCotacao=@dataCotacao)")
  CotacaoClientResponse cotacaoDolarDia(@RequestParam(value = "@dataCotacao") String dataCotacao, @RequestParam(value = "$format") String format);
}
