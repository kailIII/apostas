package com.jopss.apostas.modelos;

import com.jopss.apostas.excecoes.ApostasException;
import com.jopss.apostas.servicos.repositorio.PalpiteRepositorio;
import com.jopss.apostas.util.Modelos;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Palpite extends Modelos {
        
        private static final long serialVersionUID = 8765060059417187982L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "palpiteGenerator")
	@TableGenerator(name = "palpiteGenerator", allocationSize = 1)
	private Long id;
        
        @NotEmpty
        private String descricao;
        
        @NotNull
        private Boolean venceu = false;
        
        @NotNull
        @ManyToOne
        private Usuario usuario;
        
        @NotNull
        @ManyToOne
        private Aposta aposta;

        public Palpite() {
        }
        
        @Override
        protected PalpiteRepositorio getRepositorio(){
                return (PalpiteRepositorio) super.getRepositorio();
        }
        
        public Palpite buscarPorId(Long id) {
		return this.getRepositorio().buscarPorId(id);
	}
        
        /**
         * Regras de unicidade de login e obrigatoriedade está ou banco.
         */
        public Palpite salvar() throws ApostasException{
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

        public Boolean getVenceu() {
                return venceu;
        }

        public void setVenceu(Boolean venceu) {
                this.venceu = venceu;
        }

        public Usuario getUsuario() {
                return usuario;
        }

        public void setUsuario(Usuario usuario) {
                this.usuario = usuario;
        }

        public Aposta getAposta() {
                return aposta;
        }

        public void setAposta(Aposta aposta) {
                this.aposta = aposta;
        }
}
