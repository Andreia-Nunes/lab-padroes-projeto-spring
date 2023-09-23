package dev.andreia.labpadroesprojetospring.repository;

import dev.andreia.labpadroesprojetospring.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
