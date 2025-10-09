package com.graphqljava.tutorial.portal_vagas_graphql.domain.data.mapper.vaga;

import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.vaga.Vaga;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.vaga.VagaFilter;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.vaga.VagaRequest;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.vaga.VagaRequestFilter;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VagaMapper {

    Vaga convertToVaga(String idProfessor, VagaRequest vagaRequest);

    VagaFilter convertToVagaFilter(VagaRequestFilter vagaRequestFilter);

    @AfterMapping
    default void setDataCriacaoVaga(@MappingTarget Vaga vaga) {
        vaga.setDataCriacao(LocalDateTime.now());
    }
}
