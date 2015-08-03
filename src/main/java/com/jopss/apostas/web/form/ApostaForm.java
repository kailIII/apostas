package com.jopss.apostas.web.form;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jopss.apostas.util.JsonDateDeserializer;
import com.jopss.apostas.util.JsonDateSerializer;
import java.io.Serializable;
import java.util.Date;

public class ApostaForm implements Serializable{

        Integer paginaAtual;
        Integer quantidadeRegistro;
        Integer totalRegistros;
        
        @JsonSerialize(using=JsonDateSerializer.class)
        @JsonDeserialize(using=JsonDateDeserializer.class)
        Date dataInicial;
        
        @JsonSerialize(using=JsonDateSerializer.class)
        @JsonDeserialize(using=JsonDateDeserializer.class)
        Date dataFinal;

        public Integer getPaginaAtual() {
                return paginaAtual;
        }

        public void setPaginaAtual(Integer paginaAtual) {
                this.paginaAtual = paginaAtual;
        }

        public Integer getQuantidadeRegistro() {
                return quantidadeRegistro;
        }

        public void setQuantidadeRegistro(Integer quantidadeRegistro) {
                this.quantidadeRegistro = quantidadeRegistro;
        }

        public Date getDataInicial() {
                return dataInicial;
        }

        public void setDataInicial(Date dataInicial) {
                this.dataInicial = dataInicial;
        }

        public Date getDataFinal() {
                return dataFinal;
        }

        public void setDataFinal(Date dataFinal) {
                this.dataFinal = dataFinal;
        }

        public Integer getTotalRegistros() {
                return totalRegistros;
        }

        public void setTotalRegistros(Integer totalRegistros) {
                this.totalRegistros = totalRegistros;
        }
        
}
