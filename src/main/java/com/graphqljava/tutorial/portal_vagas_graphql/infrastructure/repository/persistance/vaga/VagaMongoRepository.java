package com.graphqljava.tutorial.portal_vagas_graphql.infrastructure.repository.persistance.vaga;

import com.graphqljava.tutorial.portal_vagas_graphql.infrastructure.data.model.document.vaga.VagaDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaMongoRepository extends MongoRepository<VagaDocument, String> {

    VagaDocument findByIdAndIdProfessor(String id, String idProfessor);
}
