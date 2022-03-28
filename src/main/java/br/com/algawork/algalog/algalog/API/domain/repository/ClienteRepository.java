package br.com.algawork.algalog.algalog.API.domain.repository;

import br.com.algawork.algalog.algalog.API.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.monitor.GaugeMonitor;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findAllById(long clienteId);
    Optional<Cliente> findByEmail(String email);
}