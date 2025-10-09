package com.graphqljava.tutorial.portal_vagas_graphql.infrastructure.data.mapper.estudante;

import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.estudante.Estudante;
import com.graphqljava.tutorial.portal_vagas_graphql.infrastructure.data.model.document.estudante.EstudanteDocument;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EstudanteDocumentMapper {

    EstudanteDocument convertToEstudanteDocument(Estudante estudante);

    Estudante convertToEstudante(EstudanteDocument estudanteDocument);
}
