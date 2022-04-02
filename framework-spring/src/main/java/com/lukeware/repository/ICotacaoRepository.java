package com.lukeware.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Diego Morais
 */
public interface ICotacaoRepository extends MongoRepository<CotacaoMapper, String> {
}
