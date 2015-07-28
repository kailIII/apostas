package com.jopss.apostas.servicos.repositorio;

import com.jopss.apostas.util.*;
import com.jopss.apostas.excecoes.ApostasException;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;

public abstract class Repositorio implements Serializable {
        
	protected static final Logger logger = Logger.getLogger(Repositorio.class);
	private static final long serialVersionUID = -1340481266616282366L;
        
	protected static EntityManager getEntityManagerMain() {
		EntityManager em = AppContextUtil.getApplicationContext().getBean(ConnectionFactory.class).getEmMain();
		if (em == null) {
			throw new RuntimeException("EntityManager esta nulo. Verifique a configuracao do hibernate/spring.");
		}
		return em;
	}
        
        protected EntityManager getEntityManager() {
		EntityManager em = AppContextUtil.getApplicationContext().getBean(ConnectionFactory.class).getEmMain();
		if (em == null) {
			throw new RuntimeException("EntityManager esta nulo. Verifique a configuracao do hibernate/spring.");
		}
		return em;
	}
        
	protected <E> E salvar(Modelos modelo) throws ApostasException {
		try {
			return (E) getEntityManagerMain().merge(modelo);
		} catch (Exception ex) {
			throw new ApostasException(ex);
		}
	}

	protected void remover(Modelos modelo) throws ApostasException {
		try {

			EntityManager em = getEntityManagerMain();
			em.remove(modelo);

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	protected <T extends Modelos> T buscarPorId(Class clazz, Long id, EntityManager... entityManagers) {
		EntityManager em = selecionarEntityManager(entityManagers);
		Query q = em.createQuery("FROM " + clazz.getSimpleName() + " WHERE id = ?");
		q.setParameter(1, id);

		try {
			return (T) q.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	private EntityManager selecionarEntityManager(EntityManager... entityManagers) {
		if(entityManagers == null || entityManagers.length == 0) {
			return getEntityManagerMain();
		}
		return entityManagers[0];
	}
        
}
