package com.jopss.apostas.modelos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jopss.apostas.excecoes.ApostasException;
import com.jopss.apostas.excecoes.DataNaoPermitidaException;
import com.jopss.apostas.servicos.repositorio.ApostaRepositorio;
import com.jopss.apostas.util.DateUtilsApostas;
import com.jopss.apostas.util.JsonDateDeserializer;
import com.jopss.apostas.util.JsonDateSerializer;
import com.jopss.apostas.util.Modelos;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Aposta extends Modelos {
        
        private static final long serialVersionUID = 8765060059417187982L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "apostaGenerator")
	@TableGenerator(name = "apostaGenerator", allocationSize = 1)
	private Long id;
        
        @NotEmpty
        private String descricao;
        
        @Future
        @NotNull
        @Temporal(TemporalType.DATE)
        @JsonSerialize(using=JsonDateSerializer.class)
        @JsonDeserialize(using=JsonDateDeserializer.class)
        private Date dateFinalizacao;
        
        @OneToMany(mappedBy = "aposta", orphanRemoval = true, cascade = CascadeType.ALL)
        private List<Palpite> palpites;

        public Aposta() {
        }

        public Aposta(Long id) {
                this.id = id;
        }
        
        @Override
        protected ApostaRepositorio getRepositorio(){
                return (ApostaRepositorio) super.getRepositorio();
        }
        
        public Aposta buscarPorId() {
		return this.getRepositorio().buscarPorId(this.getId());
	}
        
        public List<Aposta> buscarTodos(){
                return this.getRepositorio().buscarTodos();
        }
        
        public Aposta salvar() throws ApostasException{
                
                if(this.dateFinalizacao.before(DateUtilsApostas.arredondaDataZerandoHora(new Date()))){
                        throw new DataNaoPermitidaException("aposta.falha.data_nao_permitida");
                }
                
                for(Palpite palpite : this.getPalpites()  ){
                        palpite.setAposta(this);
                }
                return this.getRepositorio().salvar(this);
        }
        
        public void remover() throws ApostasException{
                this.getRepositorio().remover(this);
        }
        
        @Override
        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getDescricao() {
                return descricao;
        }

        public void setDescricao(String descricao) {
                this.descricao = descricao;
        }

        public Date getDateFinalizacao() {
                return dateFinalizacao;
        }
        
        public void setDateFinalizacao(Date dateFinalizacao) {
                this.dateFinalizacao = dateFinalizacao;
        }

        public List<Palpite> getPalpites() {
                return palpites;
        }

        public void setPalpites(List<Palpite> palpites) {
                this.palpites = palpites;
        }
}
