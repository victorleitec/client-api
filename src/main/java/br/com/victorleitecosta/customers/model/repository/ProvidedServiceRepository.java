package br.com.victorleitecosta.customers.model.repository;

import br.com.victorleitecosta.customers.model.entity.ProvidedService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProvidedServiceRepository extends JpaRepository<ProvidedService, Integer> {

    @Query("select s from ProvidedService  s join s.client c " +
            "where upper( c.name ) like upper( :name ) and function('MONTH', s.date) =:month ")
    List<ProvidedService> findByClientNameAndMonth(
            @Param("name") String name,
            @Param("month") Integer month);
}
