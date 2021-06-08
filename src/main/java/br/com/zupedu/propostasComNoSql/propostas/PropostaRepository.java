package br.com.zupedu.propostasComNoSql.propostas;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PropostaRepository extends MongoRepository<Proposta, String> {


    Boolean existsByDocumento(String documento);
}
