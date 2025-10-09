package com.graphqljava.tutorial.portal_vagas_graphql.infrastructure.repository.persistance.professor;

import com.graphqljava.tutorial.portal_vagas_graphql.infrastructure.data.model.document.professor.ProfessorDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorMongoRepository extends MongoRepository<ProfessorDocument, String> {

    Optional<ProfessorDocument> findByEmail(String email);
}
