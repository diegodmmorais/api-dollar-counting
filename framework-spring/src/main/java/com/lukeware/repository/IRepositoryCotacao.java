package com.lukeware.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Diego Morais
 */
public interface IRepositoryCotacao extends MongoRepository<CotacaoMapper, String> {
  Optional<CotacaoMapper> findByDataCotacao(LocalDate dataCotacao);

  @Query("{'dataCotacao' : { $gte: ?0, $lte: ?1 }}")
  List<CotacaoMapper> findByDataCotacao(LocalDate dataInicial, LocalDate dataFinal);
}
