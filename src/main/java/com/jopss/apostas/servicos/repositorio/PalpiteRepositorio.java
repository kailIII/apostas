package com.jopss.apostas.servicos.repositorio;

import com.jopss.apostas.excecoes.ApostasException;
import com.jopss.apostas.modelos.Palpite;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PalpiteRepositorio extends Repositorio {
        
        public Palpite buscarPorId(Long id) {
		return super.buscarPorId(Palpite.class, id);
	}
        
        @Transactional
        public Palpite salvar(Palpite palpite) throws ApostasException{
                return super.salvar(palpite);
        }
        
}
