package com.graphqljava.tutorial.portal_vagas_graphql.infrastructure.data.mapper.vaga;

import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.vaga.Vaga;
import com.graphqljava.tutorial.portal_vagas_graphql.infrastructure.data.model.document.vaga.VagaDocument;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VagaDocumentMapper {

    VagaDocument convertToVagaDocument(Vaga vaga);

    Vaga convertToVaga(VagaDocument vagaDocument);
}
