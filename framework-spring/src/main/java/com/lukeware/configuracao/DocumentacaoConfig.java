package com.lukeware.configuracao;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Diego Morais
 */
@Configuration
public class DocumentacaoConfig {
  @Bean
  public GroupedOpenApi grupoCotacao(@Value("${springdoc.version}") String appVersion) {
    return GroupedOpenApi.builder().group("Cotaçao")
                         .addOperationCustomizer((operation, handlerMethod) -> {
                           operation.addSecurityItem(new SecurityRequirement().addList("basicScheme"));
                           return operation;
                         })
                         .addOpenApiCustomiser(openApi -> openApi.info(new Info()
                                                                           .title("Cotação Dolar API")
                                                                           .description("API tem como objetivo fornecer a cotação do dolar atualizado")
                                                                           .version(appVersion)))
                         .packagesToScan("com.lukeware")
                         .build();
  }
}
