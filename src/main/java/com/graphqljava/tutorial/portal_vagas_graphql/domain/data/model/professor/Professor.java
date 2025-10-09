package com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.professor;

import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.usuario.UsuarioModel;

public class Professor extends UsuarioModel {

    private String formacao;
    private String areaAtuacao;
    private String departamento;
    private boolean coordenador;

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public boolean isCoordenador() {
        return coordenador;
    }

    public void setCoordenador(boolean coordenador) {
        this.coordenador = coordenador;
    }
}
