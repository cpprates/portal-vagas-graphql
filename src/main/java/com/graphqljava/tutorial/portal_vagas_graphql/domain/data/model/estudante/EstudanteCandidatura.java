package com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.estudante;

import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.vaga.Vaga;

import java.time.LocalDateTime;

public class EstudanteCandidatura {

    private LocalDateTime dataCandidatura;
    private Vaga vaga;

    public LocalDateTime getDataCandidatura() {
        return dataCandidatura;
    }

    public void setDataCandidatura(LocalDateTime dataCandidatura) {
        this.dataCandidatura = dataCandidatura;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }
}
