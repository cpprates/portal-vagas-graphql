package com.graphqljava.tutorial.portal_vagas_graphql.application.controllers.graphql.estudante;

import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.estudante.EstudanteCandidatura;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.estudante.EstudanteRequest;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.estudante.EstudanteRequestFilter;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.estudante.EstudanteResponse;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.service.EstudanteService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EstudanteController {

    private final EstudanteService estudanteService;

    public EstudanteController(EstudanteService estudanteService) {
        this.estudanteService = estudanteService;
    }

    @QueryMapping
    public List<EstudanteResponse> listarEstudantesPorFiltro(@Argument EstudanteRequestFilter filter) {
        return estudanteService.listarEstudantes(filter);
    }

    @QueryMapping
    public EstudanteResponse buscarEstudantePorId(@Argument String id) {
        return estudanteService.buscarEstudantePorId(id).orElse(null);
    }

    @MutationMapping
    public EstudanteResponse criarEstudante(@Argument EstudanteRequest estudanteRequest) {
        return estudanteService.criarEstudante(estudanteRequest);
    }

    @MutationMapping
    public EstudanteCandidatura criarCandidatura(@Argument String idEstudante, @Argument String idVaga) {
        return estudanteService.criarCandidatura(idEstudante, idVaga);
    }

    @MutationMapping
    public EstudanteResponse atualizarEstudante(@Argument String id, @Argument EstudanteRequest request) {
        return estudanteService.atualizarEstudante(id, request);
    }

    @MutationMapping
    public Boolean deletarCandidatura(@Argument String id, @Argument String idVaga) {
        estudanteService.deletarCandidatura(id, idVaga);
        return true;
    }

    @MutationMapping
    public Boolean deletarEstudante(@Argument String id) {
        estudanteService.deletarEstudante(id);
        return true;
    }
}

