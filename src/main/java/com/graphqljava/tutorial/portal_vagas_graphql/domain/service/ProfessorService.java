package com.graphqljava.tutorial.portal_vagas_graphql.domain.service;

import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.mapper.professor.ProfessorMapper;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.professor.*;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.exception.DataNotFoundException;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.repositories.professor.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    private ProfessorRepository professorRepository;
    private ProfessorMapper professorMapper;

    public ProfessorService(ProfessorRepository professorRepository, ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    public ProfessorResponse criarProfessor(ProfessorRequest professorRequest) {
        Professor professor = professorMapper.convertToProfessor(professorRequest);
        professor.setRole(professor.isCoordenador() ? "ADMIN" : "PROFESSOR");
        professor.setSenha(professor.getSenha());
        return professorMapper.convertToProfessorResponse(professorRepository.criarPerfil(professor));
    }

    public List<ProfessorResponse> listarProfessores(ProfessorRequestFilter professorRequestFilter) {
        ProfessorFilter professorFilter = professorMapper.convertToProfessorFilter(professorRequestFilter);
        return professorRepository.listarProfessorPorFiltro(professorFilter)
                .stream().map(professorMapper::convertToProfessorResponse)
                .collect(Collectors.toList());
    }

    public Optional<ProfessorResponse> buscarProfessorPorId(String id) {
        return Optional.ofNullable(professorRepository.buscarPorId(id).map(professorMapper::convertToProfessorResponse)
                .orElseThrow(() -> new DataNotFoundException("Professor não encontrado")));
    }

    public ProfessorResponse atualizarProfessor(String id, ProfessorRequest professorRequest) {
        Professor professor = professorMapper.convertToProfessor(professorRequest);
        professor.setRole(professor.isCoordenador() ? "ADMIN" : "PROFESSOR");
        return professorMapper.convertToProfessorResponse(professorRepository.atualizar(id, professor));
    }

    public void deletarProfessor(String id) {
        professorRepository.deletar(id);
    }

    public List<ProfessorResponse> listarTudo(){
        return professorRepository.findAll().stream().map(professorMapper::convertToProfessorResponse).toList();
    }
}