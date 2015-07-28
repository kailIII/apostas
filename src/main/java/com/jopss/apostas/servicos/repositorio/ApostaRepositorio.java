package com.jopss.apostas.servicos.repositorio;

import com.jopss.apostas.excecoes.ApostasException;
import com.jopss.apostas.modelos.Aposta;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ApostaRepositorio extends Repositorio {
        
        public Aposta buscarPorId(Long id) {
		return super.buscarPorId(Aposta.class, id);
	}
        
        @Transactional
        public Aposta salvar(Aposta aposta) throws ApostasException{
                return super.salvar(aposta);
        }
        
}
