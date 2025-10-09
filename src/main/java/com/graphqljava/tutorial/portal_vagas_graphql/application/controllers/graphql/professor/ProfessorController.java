package com.graphqljava.tutorial.portal_vagas_graphql.application.controllers.graphql.professor;

import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.professor.ProfessorRequest;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.professor.ProfessorRequestFilter;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.professor.ProfessorResponse;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.service.ProfessorService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProfessorController {

    private ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @QueryMapping
    public List<ProfessorResponse> findAllProfessores(){
        return professorService.listarTudo();
    }

    @QueryMapping
    public List<ProfessorResponse> listarProfessoresPorFiltro(@Argument ProfessorRequestFilter filter) {
        return professorService.listarProfessores(filter);
    }


    @QueryMapping
    public ProfessorResponse buscarProfessorPorId(@Argument String id) {
        return professorService.buscarProfessorPorId(id).orElse(null);
    }


    @MutationMapping
    public ProfessorResponse criarProfessor(@Argument ProfessorRequest request) {
        return professorService.criarProfessor(request);
    }

    @MutationMapping
    public ProfessorResponse atualizarProfessor(@Argument String id, @Argument ProfessorRequest request) {
        return professorService.atualizarProfessor(id, request);
    }

    @MutationMapping
    public Boolean deletarProfessor(@Argument String id) {
        professorService.deletarProfessor(id);
        return true;
    }

}
