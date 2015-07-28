package com.jopss.apostas.web.forms;

import com.jopss.apostas.util.Modelos;
import java.io.Serializable;
import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class Resposta implements Serializable{
        
        private Modelos modelo;
        private List<Modelos> lista;
        private List<Retorno> mensagens;

        public void setModelo(Modelos modelo) {
                this.modelo = modelo;
        }

        public void addErroGenerico(Exception ex){  
                Throwable cause = ex.getCause();
                if(cause instanceof org.hibernate.exception.ConstraintViolationException){
                        org.hibernate.exception.ConstraintViolationException hib = (org.hibernate.exception.ConstraintViolationException)cause;
                        if(hib.getCause() instanceof BatchUpdateException){
                                BatchUpdateException bete = ((BatchUpdateException)hib.getCause());
                                String str = bete.getNextException().getMessage();
                                if(str!=null && str.toLowerCase().contains("duplicate key")){
                                        getMensagens().add(new Retorno("erro", "Campo duplicado."));
                                        return;
                                }
                        }
                }
                getMensagens().add(new Retorno("erro", ex.getMessage()));
        }
        
        public void addErros(ConstraintViolationException ex){
                Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
                if(constraintViolations!=null){
                        for(ConstraintViolation cons : constraintViolations){
                                getMensagens().add(new Retorno(cons.getPropertyPath().toString(), cons.getMessage()));
                        }
                }
        }
        
        public List<Retorno> getMensagens(){
                if(mensagens == null){
                        mensagens = new ArrayList<>();
                }
                return mensagens;
        }
        
        public static class Retorno implements Serializable{
                private String chave;
                private String valor;

                public Retorno(String chave, String valor) {
                        this.chave = chave;
                        this.valor = valor;
                }

                public String getChave() {
                        return chave;
                }

                public String getValor() {
                        return valor;
                }
                
        }

        public Modelos getModelo() {
                return modelo;
        }

        public List<Modelos> getLista() {
                return lista;
        }
        
}
