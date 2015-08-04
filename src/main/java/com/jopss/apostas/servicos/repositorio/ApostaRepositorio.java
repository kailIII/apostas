package com.jopss.apostas.servicos.repositorio;

import com.jopss.apostas.excecoes.ApostasException;
import com.jopss.apostas.modelos.Aposta;
import com.jopss.apostas.web.form.ApostaForm;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ApostaRepositorio extends Repositorio {

        @Transactional
        public Aposta buscarPorId(Long id) {
                Aposta aposta = super.buscarPorId(Aposta.class, id);
                if (aposta.getPalpites() != null) {
                        aposta.getPalpites().size();
                }
                return aposta;
        }

        @Transactional
        public Aposta salvar(Aposta aposta) throws ApostasException {
                return super.salvar(aposta);
        }

        @Transactional
        public void remover(Aposta aposta) throws ApostasException {
                aposta = aposta.buscarPorId();
                if (aposta.getPalpites() != null) {
                        aposta.getPalpites().clear();
                }
                super.remover(aposta);
        }

        @Transactional
        public List<Aposta> buscarTodos() {
                List<Aposta> apostas = super.buscarTodos(Aposta.class);
                for (Aposta aposta : apostas) {
                        Hibernate.initialize(aposta.getPalpites());
                }
                return apostas;
        }

        @Transactional
        public List<Aposta> buscaPaginada(ApostaForm form) {
                StringBuilder sb = new StringBuilder("FROM Aposta a");
                boolean contemDatas = (form.getDataInicial() != null) && (form.getDataFinal() != null);
                if (contemDatas) {
                        sb.append(" WHERE ");
                        sb.append("a.dateFinalizacao < :dataFinal ");
                        sb.append(" AND a.dateFinalizacao > :dataInicial ");
                }
                sb.append(" ORDER BY a.dateFinalizacao DESC ");

                Query query = getEntityManager().createQuery(sb.toString());

                if (contemDatas) {
                        query.setParameter("dataFinal", form.getDataFinal());
                        query.setParameter("dataInicial", form.getDataInicial());
                }

                int inicio = form.getPaginaAtual() * form.getQuantidadeRegistro() - form.getQuantidadeRegistro();
                query.setFirstResult(inicio);
                query.setMaxResults(form.getQuantidadeRegistro());
                List<Aposta> apostas = query.getResultList();
                for (Aposta aposta : apostas) {
                        Hibernate.initialize(aposta.getPalpites());
                }

                form.setTotalRegistros(countRegistros(form));

                return apostas;
        }

        @Transactional
        public Long countRegistros(ApostaForm form) {
                StringBuilder sb = new StringBuilder("SELECT COUNT (a) FROM Aposta a");

                boolean contemDatas = (form.getDataInicial() != null) && (form.getDataFinal() != null);
                if (contemDatas) {
                        sb.append(" WHERE ");
                        sb.append("a.dateFinalizacao < :dataFinal ");
                        sb.append(" AND a.dateFinalizacao > :dataInicial ");
                }

                Query query = getEntityManager().createQuery(sb.toString());

                if (contemDatas) {
                        query.setParameter("dataFinal", form.getDataFinal());
                        query.setParameter("dataInicial", form.getDataInicial());
                }

                return (Long) query.getSingleResult();
        }

}
