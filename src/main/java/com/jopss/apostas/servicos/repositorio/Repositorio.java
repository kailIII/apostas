package com.jopss.apostas.servicos.repositorio;

import com.jopss.apostas.util.*;
import com.jopss.apostas.excecoes.ApostasException;
import com.jopss.apostas.web.form.PaginacaoForm;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

public abstract class Repositorio implements Serializable {

        protected static final Logger logger = Logger.getLogger(Repositorio.class);
        private static final long serialVersionUID = -1340481266616282366L;

        protected EntityManager getEntityManager() {
                EntityManager em = AppContextUtil.getApplicationContext().getBean(ConnectionFactory.class).getEmMain();
                if (em == null) {
                        throw new RuntimeException("EntityManager esta nulo. Verifique a configuracao do hibernate/spring.");
                }
                return em;
        }

        protected <E> E salvar(Modelos modelo) throws ApostasException {
                try {
                        return (E) getEntityManager().merge(modelo);
                } catch (Exception ex) {
                        throw new ApostasException(ex);
                }
        }

        protected void remover(Modelos modelo) throws ApostasException {
                try {

                        EntityManager em = getEntityManager();
                        em.remove( salvar(modelo) );

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

        protected <T extends Modelos> List<T> buscarTodos(Class clazz, EntityManager... entityManagers) {
                EntityManager em = selecionarEntityManager(entityManagers);
                Query q = em.createQuery("FROM " + clazz.getSimpleName());
                return (List<T>) q.getResultList();
        }

        private EntityManager selecionarEntityManager(EntityManager... entityManagers) {
                if (entityManagers == null || entityManagers.length == 0) {
                        return getEntityManager();
                }
                return entityManagers[0];
        }
        
        protected void configurarPaginacao(Query query, HQLGenerator queryGenerator, PaginacaoForm form){
                int inicio = form.getPaginaAtual() * form.getQuantidadeRegistro() - form.getQuantidadeRegistro();
                query.setFirstResult(inicio);
                query.setMaxResults(form.getQuantidadeRegistro());
                form.setTotalRegistros(countRegistros(queryGenerator));
        }
        
        @Transactional
        private Long countRegistros(HQLGenerator queryGenerator) {
                Query query = getEntityManager().createQuery(queryGenerator.getCountQuery());
                queryGenerator.setParameters(query);
                return (Long) query.getSingleResult();
        }

}
