package com.jopss.apostas.modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jopss.apostas.excecoes.ApostasException;
import com.jopss.apostas.servicos.repositorio.ApostaRepositorio;
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
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
        private Date dateFinalizacao;
        
        @OneToMany(mappedBy = "aposta", orphanRemoval = true, cascade = CascadeType.ALL)
        @JsonIgnore
        private List<Palpite> palpites;

        public Aposta() {
        }
        
        @Override
        protected ApostaRepositorio getRepositorio(){
                return (ApostaRepositorio) super.getRepositorio();
        }
        
        public Aposta buscarPorId(Long id) {
		return this.getRepositorio().buscarPorId(id);
	}
        
        public List<Aposta> buscarTodos(){
                return this.getRepositorio().buscarTodos();
        }
        
        public Aposta salvar() throws ApostasException{
                for(Palpite palpite : this.getPalpites()  ){
                        palpite.setAposta(this);
                }
                return this.getRepositorio().salvar(this);
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

        public List<Palpite> getPalpites() {
                return palpites;
        }

        public void setPalpites(List<Palpite> palpites) {
                this.palpites = palpites;
        }
}
