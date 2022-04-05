# Desafio
## Historia
Deverá ser desenvolvida uma API que busca a cotação do dólar comercial de uma determinada data a ser informada na requisição.


Requisitos
1. Buscar a contação do dólar na API externa a seguir: https://dadosabertos.bcb.gov.br/dataset/dolar-americano-usd-todos-os-boletins-diarios  :white_check_mark:
1. Gravar a contação no banco de dados:white_check_mark:
	1. O banco de dados pode ser postgre, mysql, mongo, ficando a escolha:white_check_mark:
	1. Os dados que deveram ser salvos no banco de dados serão:  :white_check_mark:
		1. **id da requisição**
		1. **timestamp da requisição**
		1. **Data da cotação do dolar**
		1. **Cotação de compra**
		1. **Cotação de venda**
		1. **Data e Hora da Cotação**
1. A API deverá estar documentada com Swagger  :white_check_mark:
1. Monitoração com Prometheus e Grafana exibindo métricas da API, do DB e da infra. :white_check_mark:
1. Tracing (Jaeger):white_large_square:
1. Testes automatizados efetivos:white_check_mark:
1. Containerização da aplicação Docker/docker-compose :white_check_mark:


*Obs. não é obrigatório a criação do front, pode ser utilizado o postman ou o swagger para os testes.*

*Obs. Não existe cotação nos finais de semana.*

## Critérios de aceite
### História 1


Eu desejo que seja desenvolvido um conector que tem como responsabilidade buscar o dólar atualizado conforme a data desejada.
Critério de aceite.
 - Deverá passar data inicial e data final. E trazer uma lista de coração.
 - Deverá validar se o intervalo entre a data inicial e final é menor que os últimos 30 dias.
 - Deverá validar se a data inicial é maior que a data final.
 
### História 2
 Eu desejo que seja desenvolvido um "adapter data access" que tem como objetivo salvar, listar as contações.
Critério de aceite.
- Deverá salvar as cotações em um banco de dados NoSql
- Deverá poder filtrar as cotações com base na data inicial e final com o intervalo de 30 dias limites.


### História 3
Eu desejo que seja desenvolvido uma "API Client" que possibilite buscar uma lista de cotação na API https://dadosabertos.bcb.gov.br/dataset/dolar-americano-usd-todos-os-boletins-diarios.
Critério de aceite.
- Deverá buscar cotação por dia conforme a data informada.
- Deverá buscar a contação de forma automatizada, de segunda a sexta as 18h 30m.


### Casos de teste
CT1 - Ao listar as cotações sem passar nenhum data deverá receber uma mensagem informando a obrigatoriedade do campo data. :white_check_mark:

CT2 - Ao listar as cotações e passa a data inicial menor que a datafinal, deverá receber uma mensagem informando data inválida. :white_check_mark:

CT3 - Ao listar as contações passando as datas corretas, deverá retornar uma lista de cotação. :white_check_mark:

CT4 - Validar se o agendamento de atualização de cotações busca a contação do dia sendo de segunda a sexta as 18h 30m através dos logs da aplicação. :white_check_mark:

CT5 - Validar se os dados da API não estão inconsistentes. :white_check_mark:
```json
[
    {
        "cotacaoCompra": 5.0507,
        "cotacaoVenda": 5.0513,
        "dataHoraCotacao": "2022-03-10T19:48:39.068",
        "dataCotacao": "2022-03-10",
        "tempoDaRequisicao": "2022-04-04T02:19:44.037"
    }
]
```

# Implantação
Para executar a aplicação é necessário como pré-requisito ter em usa máquina instalado:
- maven 
- docker
- docker compose
- Java versão 11 ou maior

Obs. Não temos como objetivo encinar a instalação dos itens acima.

## Execute o comando a seguir:
```cmd
mvn clean install && docker-compose up -d
```

# Documentação
Link documentação open api: http://localhost:8080/api/documentacao/swagger

Link swagger: http://localhost:8080/api/documentacao/swagger-ui/index.html

# Acessar banco de dados
Link do banco de dados http://localhost:8081/db/lukeware/

# Detalhes Técnicos
#### Arquitetura utilizada
Clean architecture 
#### Framework
- Spring Boot
- Spring Cloud Starter Feign
- Spring Doc OpenApi
- Lombok
#### Collection Postman
[api-dollar-counting.postman_collection.json](https://github.com/diegodmmorais/api-dollar-counting/blob/main/api-dollar-counting.postman_collection.json)

#### Schedule
A classe responsável por orquestrar o agendamento de atualizar cotação dolar do dia
[AtualizadorDeCotacaoScheduler.java](https://github.com/diegodmmorais/api-dollar-counting/blob/main/framework-spring/src/main/java/com/lukeware/atualizacao/AtualizadorDeCotacaoScheduler.java)
```java
@Scheduled(cron = "${aplicacao.atualizarCotacao.scheduled.cron:0 30 18 ? * MON,TUE,WED,THU,FRI}")
```

#### Prometheus e Grafana
Linck do Prometheus: http://localhost:9090/graph

Linck do Grafana: http://localhost:9091/dashboard

Obs. Não foi criado uma documentação de como configurar o grafana e o prometheus
