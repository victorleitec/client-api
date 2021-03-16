package br.com.victorleitecosta.customers.model.repository;

import br.com.victorleitecosta.customers.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
