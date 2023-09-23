package dev.andreia.labpadroesprojetospring.repository;

import dev.andreia.labpadroesprojetospring.model.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, String> {
}
