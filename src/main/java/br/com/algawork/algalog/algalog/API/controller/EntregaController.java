package br.com.algawork.algalog.algalog.API.controller;

import br.com.algawork.algalog.algalog.API.assembler.EntregaAssembler;
import br.com.algawork.algalog.algalog.API.domain.model.Entrega;
import br.com.algawork.algalog.algalog.API.domain.repository.EntregaRepository;
import br.com.algawork.algalog.algalog.API.domain.service.SolicitacaoEntregaService;
import br.com.algawork.algalog.algalog.API.model.EntregaModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private EntregaRepository entregaRepository;
    private SolicitacaoEntregaService solicitacaoEntregaService;
    private EntregaAssembler entregaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solicitar(@Valid @RequestBody Entrega entrega){
        return entregaAssembler.toModel(solicitacaoEntregaService.solicitar(entrega));
    }

    @GetMapping
    public List<EntregaModel> listar(){
        return entregaAssembler.tocollectionModel(entregaRepository.findAll());
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId){
        return entregaRepository.findById(entregaId)
                .map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }

}
