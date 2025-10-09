package com.graphqljava.tutorial.portal_vagas_graphql.domain.service;

import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.mapper.estudante.EstudanteMapper;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.estudante.*;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.vaga.Vaga;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.exception.ConflictException;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.exception.DataNotFoundException;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.repositories.estudante.EstudanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstudanteService {

    private EstudanteRepository estudanteRepository;
    private EstudanteMapper estudanteMapper;
    private VagaService vagaService;
//    private PasswordEncoder passwordEncoder;

    public EstudanteService(
            EstudanteRepository estudanteRepository,
            EstudanteMapper estudanteMapper,
            VagaService vagaService) {
        this.estudanteRepository = estudanteRepository;
        this.estudanteMapper = estudanteMapper;
        this.vagaService = vagaService;
    }

    public EstudanteResponse criarEstudante(EstudanteRequest estudanteRequest) {
        Estudante estudante = estudanteMapper.convertToEstudante(estudanteRequest);
        estudante.setRole("ESTUDANTE");
        estudante.setSenha(estudante.getSenha());
        return estudanteMapper.convertToEstudanteResponse(estudanteRepository.criar(estudante));
    }

    public EstudanteCandidatura criarCandidatura(String id, String idVaga) {
        Estudante estudante = validarEstudante(id);
        Vaga vaga = validarVaga(idVaga);
        EstudanteCandidatura candidatura = estudanteMapper.convertToEstudanteCandidatura(vaga);
        return estudanteRepository.criarCandidatura(estudante, candidatura);
    }

    public void deletarCandidatura(String id, String idVaga) {
        Estudante estudante = validarEstudante(id);
        deletarCandidaturaHandler(id, idVaga, estudante);
    }

    public List<EstudanteResponse> listarEstudantes(EstudanteRequestFilter estudanteRequestFilter) {
        EstudanteFilter estudanteFilter = estudanteMapper.convertToEstudanteFilter(estudanteRequestFilter);
        return estudanteRepository.listarPorFiltro(estudanteFilter)
                .stream().map(estudanteMapper::convertToEstudanteResponse)
                .collect(Collectors.toList());
    }

    public Optional<EstudanteResponse> buscarEstudantePorId(String id) {
        return Optional.ofNullable(estudanteRepository.buscarPorId(id)
                .map(estudanteMapper::convertToEstudanteResponse)
                .orElseThrow(() -> new DataNotFoundException("Estudante não encontrado")));
    }

    public EstudanteResponse atualizarEstudante(String id, EstudanteRequest estudanteRequest) {
        Estudante estudante = estudanteMapper.convertToEstudante(estudanteRequest);
        return estudanteMapper.convertToEstudanteResponse(estudanteRepository.atualizar(id, estudante));
    }

    public void deletarEstudante(String id) {
        estudanteRepository.deletar(id);
    }

    private Estudante validarEstudante(String id) {
        Optional<Estudante> estudante = estudanteRepository.buscarPorId(id);
        if (estudante.isEmpty()) {
            throw new DataNotFoundException(String.format("Estudante com id [%s] não encontrado", id));
        }
        return estudante.get();
    }

    private Vaga validarVaga(String id) {
        Optional<Vaga> vaga = vagaService.buscarVagaPorId(id);
        if (vaga.isEmpty()) {
            throw new DataNotFoundException(String.format("Vaga com id [%s] não encontrada", id));
        }
        return vaga.get();
    }

    private void deletarCandidaturaHandler(String id, String idVaga, Estudante estudante) {
        if (estudante.getCandidaturas() != null && !estudante.getCandidaturas().isEmpty()) {
            boolean removido = estudante.getCandidaturas().removeIf(candidatura1 ->
                    candidatura1.getVaga() != null && idVaga.equals(candidatura1.getVaga().getId())
            );
            if (removido) {
                estudanteRepository.atualizar(id, estudante);
            } else {
                throw new DataNotFoundException(
                        String.format("Candidatura com idVaga [%s] não encontrada para o estudante", idVaga));
            }
        } else {
            throw new ConflictException("O estudante não possui candidaturas");
        }
    }
}
