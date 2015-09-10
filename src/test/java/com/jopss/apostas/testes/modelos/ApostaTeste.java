package com.jopss.apostas.testes.modelos;

import com.jopss.apostas.excecoes.ApostasException;
import com.jopss.apostas.excecoes.DataNaoPermitidaException;
import com.jopss.apostas.modelos.Aposta;
import com.jopss.apostas.servicos.repositorio.ApostaRepositorio;
import java.util.Date;
import org.joda.time.DateTime;
import org.junit.Test;

public class ApostaTeste {
        
        @Test(expected = DataNaoPermitidaException.class)
        public void tentarSalvarAposDataFinalizacao() throws ApostasException{
                Aposta aposta = new Aposta();
                aposta.setId(1L);
                aposta.setDateFinalizacao(new Date(1L));
                
                aposta.salvar();
                
        }
        
        @Test(expected = DataNaoPermitidaException.class)
        public void tentarSalvarAntesDataFinalizacao() throws ApostasException{
                Aposta aposta = new Aposta();
                aposta.setId(1L);
                aposta.setDateFinalizacao((new DateTime()).plusDays(1).toDate());
                
                aposta.salvar();
                
        }
        
        private ApostaRepositorio mockarApostaRepositorio(){
                
                Aposta apostaMockado = new Aposta();
                apostaMockado.setId(1L);
                apostaMockado.setDateFinalizacao(new Date(1L));

                
                return null;
        }
        
}
