package com.jopss.apostas.servicos.repositorio;

import com.jopss.apostas.excecoes.ApostasException;
import com.jopss.apostas.modelos.Usuario;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UsuarioRepositorio extends Repositorio {
        
        public Usuario buscarPorId(Long id) {
		return super.buscarPorId(Usuario.class, id);
	}
        
        public Usuario buscarPorLogin(String login) {
		TypedQuery<Usuario> q = getEntityManager().createQuery("FROM Usuario WHERE login = :login", Usuario.class);
                q.setParameter("login", login);
                
                try{
                        return q.getSingleResult();
                }catch(NoResultException ex){
                        return null;
                }
	}
        
        public List<Usuario> buscarTodos(){
                return super.buscarTodos(Usuario.class);
        }
        
        @Transactional
        public Usuario salvar(Usuario usuario) throws ApostasException{
                return super.salvar(usuario);
        }
        
}
