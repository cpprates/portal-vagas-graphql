package com.graphqljava.tutorial.portal_vagas_graphql.application.controllers.rest.vaga;

import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.vaga.Vaga;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.vaga.VagaRequest;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.data.model.vaga.VagaRequestFilter;
import com.graphqljava.tutorial.portal_vagas_graphql.domain.service.VagaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("portal-vagas/vagas/v1/vagas")
public class VagaRestController implements VagaApi {

    private VagaService vagaService;

    public VagaRestController(VagaService vagaService) {
        this.vagaService = vagaService;
    }

    @PostMapping("/{idProfessor}")
    public ResponseEntity<Vaga> criarVaga(@PathVariable String idProfessor, @RequestBody VagaRequest vagaRequest) {
        Vaga novaVaga = vagaService.criarVaga(idProfessor, vagaRequest);
        return new ResponseEntity<>(novaVaga, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Vaga>> listarVagas(VagaRequestFilter vagaRequestFilter) {
        List<Vaga> vagas = vagaService.listarVagas(vagaRequestFilter);
        return new ResponseEntity<>(vagas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaga> buscarVagaPorId(@PathVariable String id) {
        return vagaService.buscarVagaPorId(id)
                .map(vaga -> new ResponseEntity<>(vaga, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}/professor/{idProfessor}")
    public ResponseEntity<Vaga> atualizarVaga(@PathVariable String id, @PathVariable String idProfessor, @RequestBody VagaRequest vagaRequest) {
        Vaga vagaAtualizada = vagaService.atualizarVaga(id, idProfessor, vagaRequest);
        return new ResponseEntity<>(vagaAtualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/professor/{idProfessor}")
    public ResponseEntity<Void> deletarVaga(@PathVariable String id, @PathVariable String idProfessor) {
        vagaService.deletarVaga(id, idProfessor);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
