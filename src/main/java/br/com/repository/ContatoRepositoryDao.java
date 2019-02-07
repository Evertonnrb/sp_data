package br.com.repository;

import br.com.entity.Contato;
import org.springframework.data.repository.CrudRepository;

public interface ContatoRepositoryDao extends CrudRepository<Contato,Long> {

}
