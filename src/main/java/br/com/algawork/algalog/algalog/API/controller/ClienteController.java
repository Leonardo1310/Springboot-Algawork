package br.com.algawork.algalog.algalog.API.controller;

import br.com.algawork.algalog.algalog.API.domain.model.Cliente;
import br.com.algawork.algalog.algalog.API.domain.repository.ClienteRepository;
import br.com.algawork.algalog.algalog.API.domain.service.CatalogoClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")

public class ClienteController {

    private ClienteRepository clienteRepository;
    private CatalogoClienteService catalogoClienteService;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable long clienteId){
        return clienteRepository.findAllById(clienteId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente){return catalogoClienteService.salvar(cliente);}

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId,
       @Valid @RequestBody Cliente cliente){
        if(!clienteRepository.existsById(clienteId)){return ResponseEntity.notFound().build();}

        cliente.setId(clienteId);
        cliente = catalogoClienteService.salvar(cliente);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> deletar(@PathVariable Long clienteId, Cliente cliente){
        if(!clienteRepository.existsById(clienteId)){return ResponseEntity.notFound().build();}

        catalogoClienteService.excluir(clienteId);

        return ResponseEntity.noContent().build();
    }
}
