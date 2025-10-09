package com.graphqljava.tutorial.portal_vagas_graphql.domain.repositories.vaga;

import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.vaga.Vaga;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.vaga.VagaFilter;

import java.util.List;
import java.util.Optional;

public interface VagaRepository {
    Vaga criar(Vaga vaga);

    List<Vaga> buscarVagaPorFiltro(VagaFilter vagaFilter);

    Optional<Vaga> buscarPorId(String id);

    Vaga atualizar(String id, String idProfessor, Vaga vaga);

    void deletar(String id, String idProfessor);
}
