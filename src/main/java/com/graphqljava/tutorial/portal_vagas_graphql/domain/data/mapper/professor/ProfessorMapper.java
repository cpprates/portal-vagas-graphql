package com.graphqljava.tutorial.portal_vagas_graphql.domain.data.mapper.professor;

import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.professor.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfessorMapper {

    Professor convertToProfessor(ProfessorRequest professorRequest);

    ProfessorFilter convertToProfessorFilter(ProfessorRequestFilter professorRequestFilter);

    ProfessorResponse convertToProfessorResponse(Professor professor);
}
