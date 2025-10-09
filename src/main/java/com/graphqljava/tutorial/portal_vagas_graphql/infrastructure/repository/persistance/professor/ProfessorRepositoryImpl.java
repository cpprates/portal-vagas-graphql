package com.graphqljava.tutorial.portal_vagas_graphql.infrastructure.repository.persistance.professor;

import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.professor.Professor;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.professor.ProfessorFilter;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.repositories.professor.ProfessorRepository;
import com.graphqljava.tutorial.portal_vagas_graphql.infrastructure.data.mapper.professor.ProfessorDocumentMapper;
import com.graphqljava.tutorial.portal_vagas_graphql.infrastructure.data.model.document.professor.ProfessorDocument;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProfessorRepositoryImpl implements ProfessorRepository {

    private ProfessorMongoRepository professorMongoRepository;
    private ProfessorDocumentMapper professorDocumentMapper;
    private MongoTemplate mongoTemplate;

    public ProfessorRepositoryImpl(
            ProfessorMongoRepository professorMongoRepository,
            ProfessorDocumentMapper professorDocumentMapper,
            MongoTemplate mongoTemplate) {
        this.professorMongoRepository = professorMongoRepository;
        this.professorDocumentMapper = professorDocumentMapper;
        this.mongoTemplate = mongoTemplate;

    }

    @Override
    public Professor criarPerfil(Professor professor) {
        ProfessorDocument professorDocument = professorDocumentMapper.convertToProfessorDocument(professor);
        ProfessorDocument professorCriado = professorMongoRepository.save(professorDocument);
        return professorDocumentMapper.convertToProfessor(professorCriado);
    }

    @Override
    public List<Professor> listarProfessorPorFiltro(ProfessorFilter professorFilter) {
        Query query = new Query();

        if (professorFilter.getNome() != null && !professorFilter.getNome().isEmpty()) {
            query.addCriteria(Criteria.where("nome").regex(professorFilter.getNome(), "i"));
        }
        if (professorFilter.getEmail() != null && !professorFilter.getEmail().isEmpty()) {
            query.addCriteria(Criteria.where("email").regex(professorFilter.getEmail(), "i"));
        }
        if (professorFilter.getFormacao() != null && !professorFilter.getFormacao().isEmpty()) {
            query.addCriteria(Criteria.where("formacao").regex(professorFilter.getFormacao(), "i"));
        }
        if (professorFilter.getAreaAtuacao() != null && !professorFilter.getAreaAtuacao().isEmpty()) {
            query.addCriteria(Criteria.where("areaAtuacao").regex(professorFilter.getAreaAtuacao(), "i"));
        }
        if (professorFilter.getDepartamento() != null && !professorFilter.getDepartamento().isEmpty()) {
            query.addCriteria(Criteria.where("departamento").regex(professorFilter.getDepartamento(), "i"));
        }

        return mongoTemplate.find(query, ProfessorDocument.class).stream()
                .map(professorDocumentMapper::convertToProfessor)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Professor> buscarPorId(String id) {
        Optional<ProfessorDocument> professorDocument = professorMongoRepository.findById(id);
        return professorDocument.map(professorDocumentMapper::convertToProfessor);
    }

    @Override
    public Optional<Professor> buscarPorEmail(String email) {
        Optional<ProfessorDocument> professorDocument = professorMongoRepository.findByEmail(email);
        return professorDocument.map(professorDocumentMapper::convertToProfessor);
    }

    @Override
    public Professor atualizar(String id, Professor professor) {
        ProfessorDocument professorDocument = professorDocumentMapper.convertToProfessorDocument(professor);
        professorDocument.setId(id);
        ProfessorDocument professorAtualizado = professorMongoRepository.save(professorDocument);
        return professorDocumentMapper.convertToProfessor(professorAtualizado);
    }

    @Override
    public void deletar(String id) {
        professorMongoRepository.deleteById(id);
    }

    @Override
    public List<Professor> findAll() {
        return professorMongoRepository.findAll().stream()
                .map(professorDocumentMapper::convertToProfessor)
                .collect(Collectors.toList());
    }
}
