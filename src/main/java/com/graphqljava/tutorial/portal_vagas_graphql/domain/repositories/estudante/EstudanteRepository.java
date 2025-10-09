package com.graphqljava.tutorial.portal_vagas_graphql.domain.repositories.estudante;

import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.estudante.Estudante;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.estudante.EstudanteCandidatura;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.estudante.EstudanteFilter;

import java.util.List;
import java.util.Optional;

public interface EstudanteRepository {

    Estudante criar(Estudante estudante);

    EstudanteCandidatura criarCandidatura(Estudante estudante, EstudanteCandidatura candidatura);

    List<Estudante> listarPorFiltro(EstudanteFilter estudanteFilter);

    Optional<Estudante> buscarPorId(String id);

    Optional<Estudante> buscarPorEmail(String email);

    Estudante atualizar(String id, Estudante estudante);

    void deletar(String id);
}
