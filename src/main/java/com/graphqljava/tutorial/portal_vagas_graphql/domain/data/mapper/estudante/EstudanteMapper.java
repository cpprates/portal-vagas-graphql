package com.graphqljava.tutorial.portal_vagas_graphql.domain.data.mapper.estudante;

import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.estudante.*;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.vaga.Vaga;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EstudanteMapper {

    Estudante convertToEstudante(EstudanteRequest estudanteRequest);

    EstudanteCandidatura convertToEstudanteCandidatura(Vaga vaga);

    @AfterMapping
    default void setDataCandidatura(@MappingTarget EstudanteCandidatura estudanteCandidatura) {
        estudanteCandidatura.setDataCandidatura(LocalDateTime.now());
    }

    EstudanteFilter convertToEstudanteFilter(EstudanteRequestFilter estudanteRequestFilter);

    EstudanteResponse convertToEstudanteResponse(Estudante estudante);
}
