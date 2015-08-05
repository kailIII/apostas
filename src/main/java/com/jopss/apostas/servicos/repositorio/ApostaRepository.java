package com.jopss.apostas.servicos.repositorio;

import com.jopss.apostas.modelos.Aposta;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

public interface ApostaRepository extends CrudRepository<Aposta, Long> {

        @EntityGraph(value = "palpites", type = EntityGraph.EntityGraphType.LOAD)
        Aposta findById(Long id);

        @Override
        @EntityGraph(value = "palpites", type = EntityGraph.EntityGraphType.LOAD)
        List<Aposta> findAll();

}
