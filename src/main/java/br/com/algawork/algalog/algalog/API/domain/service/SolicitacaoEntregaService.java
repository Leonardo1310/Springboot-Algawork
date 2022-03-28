package br.com.algawork.algalog.algalog.API.domain.service;

import br.com.algawork.algalog.algalog.API.domain.exception.NegocioException;
import br.com.algawork.algalog.algalog.API.domain.model.Cliente;
import br.com.algawork.algalog.algalog.API.domain.model.Entrega;
import br.com.algawork.algalog.algalog.API.domain.model.StatusEntrega;
import br.com.algawork.algalog.algalog.API.domain.repository.ClienteRepository;
import br.com.algawork.algalog.algalog.API.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

    private CatalogoClienteService catalogoClienteService;
    private EntregaRepository entregaRepository;

    @Transactional
    public Entrega solicitar(Entrega entrega){

        Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);

    }
}
