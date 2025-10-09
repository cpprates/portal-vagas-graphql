package com.graphqljava.tutorial.portal_vagas_graphql.infrastructure.data.mapper.professor;

import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.professor.Professor;
import com.graphqljava.tutorial.portal_vagas_graphql.infrastructure.data.model.document.professor.ProfessorDocument;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfessorDocumentMapper {

    ProfessorDocument convertToProfessorDocument(Professor professor);

    Professor convertToProfessor(ProfessorDocument professorDocument);
}
