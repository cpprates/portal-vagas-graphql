package com.graphqljava.tutorial.portal_vagas_graphql.infrastructure.repository.persistance.estudante;

import com.graphqljava.tutorial.portal_vagas_graphql.infrastructure.data.model.document.estudante.EstudanteDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudanteMongoRepository extends MongoRepository<EstudanteDocument, String> {

    Optional<EstudanteDocument> findByEmail(String email);
}
