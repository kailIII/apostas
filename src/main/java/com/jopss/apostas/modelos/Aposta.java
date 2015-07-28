package com.jopss.apostas.modelos;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
        
        @NotNull
        @Temporal(TemporalType.DATE)
        private Date dateFinalizacao;
        
        @ManyToOne
        private Usuario vencedor;
        
        @NotNull
        @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
        private List<Usuario> usuarios;

        public Aposta() {
        }
        
        @Override
        protected ApostaRepositorio getRepositorio(){
                return (ApostaRepositorio) super.getRepositorio();
        }
        
        public Aposta buscarPorId(Long id) {
		return this.getRepositorio().buscarPorId(id);
	}
        
        public Aposta salvar() throws ApostasException{
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

        public Usuario getVencedor() {
                return vencedor;
        }

        public List<Usuario> getUsuarios() {
                return usuarios;
        }

}
