package com.jopss.apostas.servicos.repositorio;

import com.jopss.apostas.excecoes.ApostasException;
import com.jopss.apostas.modelos.Aposta;
import com.jopss.apostas.util.ApostaHQLGenerator;
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
        public List<Aposta> buscaPaginada(ApostaForm form){
                
                Query query = buildQuery(form);

                List<Aposta> apostas = query.getResultList();
                for (Aposta aposta : apostas) {
                        Hibernate.initialize(aposta.getPalpites());
                }

                return apostas;
        }
        
        private Query buildQuery(ApostaForm form){
                
                ApostaHQLGenerator queryGenerator = new ApostaHQLGenerator();
                queryGenerator.filtrarPorDatas(form.getDataInicial(), form.getDataFinal());
                queryGenerator.aplicarFiltros();
                queryGenerator.ordenarPorDataDesc();

                Query query = getEntityManager().createQuery(queryGenerator.getQuery());
                queryGenerator.setParameters(query);
                this.configurarPaginacao(query, queryGenerator, form);
                
                return query;
        }
}
