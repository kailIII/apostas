package com.jopss.apostas.modelos;

import com.jopss.apostas.excecoes.ApostasException;
import com.jopss.apostas.excecoes.LoginInvalidoException;
import com.jopss.apostas.servicos.repositorio.UsuarioRepositorio;
import com.jopss.apostas.util.Modelos;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Usuario extends Modelos {
        
        private static final long serialVersionUID = 8765060059417187982L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "usuarioGenerator")
	@TableGenerator(name = "usuarioGenerator", allocationSize = 1)
	private Long id;
        
        @NotEmpty
        private String nome;
        
        @NotEmpty
        @Size(max = 20)
        @Column(unique = true)
        private String login;
        
        @NotEmpty
        @Size(max = 10)
        private String senha;

        public Usuario() {
        }

        public Usuario(Long id) {
                this.id = id;
        }
        
        @Override
        protected UsuarioRepositorio getRepositorio(){
                return (UsuarioRepositorio) super.getRepositorio();
        }
        
        public Usuario buscarPorId() {
		return this.getRepositorio().buscarPorId(this.getId());
	}
        
        public List<Usuario> buscarTodos(){
                return this.getRepositorio().buscarTodos();
        }
        
        public Usuario autenticar() throws LoginInvalidoException {
                Usuario usuarioBase =  this.getRepositorio().buscarPorLoginESenha(this.login, this.senha);
                if(usuarioBase != null){
                        return usuarioBase;
                }else{
                        throw new LoginInvalidoException();
                }
        }
        
        /**
         * Regras de unicidade de login e obrigatoriedade está ou banco.
         */
        public Usuario salvar() throws ApostasException{
                return this.getRepositorio().salvar(this);
        }
        
        @Override
        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public String getLogin() {
                return login;
        }

        public void setLogin(String login) {
                this.login = login;
        }

        public String getSenha() {
                return senha;
        }

        public void setSenha(String senha) {
                this.senha = senha;
        }
        
}
