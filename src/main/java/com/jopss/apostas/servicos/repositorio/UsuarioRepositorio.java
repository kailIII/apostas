package com.jopss.apostas.servicos.repositorio;

import com.jopss.apostas.excecoes.ApostasException;
import com.jopss.apostas.modelos.Usuario;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UsuarioRepositorio extends Repositorio {
        
        public Usuario buscarPorLoginESenha(String login, String senha){
                TypedQuery<Usuario> q = getEntityManagerMain().createQuery("FROM Usuario WHERE login = :login AND senha = :senha", Usuario.class);
                q.setParameter("login", login);
                q.setParameter("senha", senha);
                
                try{
                        return q.getSingleResult();
                }catch(NoResultException ex){
                        return null;
                }
        }
        
        public Usuario buscarPorId(Long id) {
		return super.buscarPorId(Usuario.class, id);
	}
        
        @Transactional
        public Usuario salvar(Usuario usuario) throws ApostasException{
                return super.salvar(usuario);
        }
        
}
