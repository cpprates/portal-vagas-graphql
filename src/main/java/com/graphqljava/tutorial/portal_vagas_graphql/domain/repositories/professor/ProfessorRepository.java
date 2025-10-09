package com.graphqljava.tutorial.portal_vagas_graphql.domain.repositories.professor;

import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.professor.Professor;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.professor.ProfessorFilter;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository {

    Professor criarPerfil(Professor professor);

    List<Professor> listarProfessorPorFiltro(ProfessorFilter professorFilter);

    Optional<Professor> buscarPorId(String id);

    Optional<Professor> buscarPorEmail(String email);

    Professor atualizar(String id, Professor professor);

    void deletar(String id);

    List<Professor> findAll();
}
