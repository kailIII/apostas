package com.jopss.apostas.servicos.repositorio;

import com.jopss.apostas.modelos.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
        
        Usuario findByLogin(String login);
        
}
