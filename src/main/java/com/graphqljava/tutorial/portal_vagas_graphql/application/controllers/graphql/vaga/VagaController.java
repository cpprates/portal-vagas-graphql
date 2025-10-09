package com.graphqljava.tutorial.portal_vagas_graphql.application.controllers.graphql.vaga;

import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.estudante.EstudanteRequestFilter;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.estudante.EstudanteResponse;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.vaga.Vaga;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.vaga.VagaRequest;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.vaga.VagaRequestFilter;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.service.VagaService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VagaController {

    private final VagaService vagaService;

    public VagaController(VagaService vagaService) {
        this.vagaService = vagaService;
    }

    @MutationMapping
    public Vaga criarVaga(@Argument String idProfessor, @Argument VagaRequest vaga) {
        return vagaService.criarVaga(idProfessor, vaga);
    }

    @QueryMapping
    public Vaga buscarVagaPorId(@Argument String id) {
        return vagaService.buscarVagaPorId(id).orElse(null);
    }

    @QueryMapping
    public List<Vaga> listarVagasPorFiltro(@Argument VagaRequestFilter filter) {
        return vagaService.listarVagas(filter);
    }

    @MutationMapping
    public Vaga atualizarVaga(@Argument String id, @Argument String idProfessor, @Argument VagaRequest request) {
        return vagaService.atualizarVaga(id, idProfessor, request);
    }

    @MutationMapping
    public Boolean deletarVaga(@Argument String id, @Argument String idProfessor) {
        vagaService.deletarVaga(id, idProfessor);
        return true;
    }


}

