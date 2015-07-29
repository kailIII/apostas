package com.jopss.apostas.servicos;

import com.jopss.apostas.excecoes.UsuarioNaoLogadoException;
import com.jopss.apostas.modelos.Usuario;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessaoUsuario {

	public Usuario getUsuarioLogado(HttpSession session) {
                Object obj = session.getAttribute("USUARIO_LOGADO");
                if(obj == null) throw new UsuarioNaoLogadoException("Usuário não logado");
                return (Usuario) obj;
	}
        
        public void setUsuarioLogado(Usuario usuario, HttpSession session) {
                session.setAttribute("USUARIO_LOGADO", usuario);
	}
        
}
