package com.jopss.apostas.servicos.repositorio;

import com.jopss.apostas.excecoes.ApostasException;
import com.jopss.apostas.modelos.Aposta;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ApostaRepositorio extends Repositorio {
        
        @Transactional
        public Aposta buscarPorId(Long id) {
                Aposta aposta = super.buscarPorId(Aposta.class, id);
                if (aposta.getPalpites() != null){
                        aposta.getPalpites().size();
                }
		return aposta;
	}
        
        @Transactional
        public Aposta salvar(Aposta aposta) throws ApostasException{
                return super.salvar(aposta);
        }
        
        @Transactional
        public void remover(Aposta aposta) throws ApostasException{
                aposta = aposta.buscarPorId();
                if(aposta.getPalpites()!=null){
                        aposta.getPalpites().clear();
                }
                super.remover(aposta);
        }
        
        @Transactional
        public List<Aposta> buscarTodos(){
                List<Aposta> apostas = super.buscarTodos(Aposta.class);
                for(Aposta aposta : apostas){
                        Hibernate.initialize(aposta.getPalpites());
                }
                return apostas;
        }
        
}
