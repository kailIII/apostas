package com.jopss.apostas.web;

import com.jopss.apostas.excecoes.LoginInvalidoException;
import com.jopss.apostas.modelos.Usuario;
import com.jopss.apostas.web.util.ApostasController;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controlador para acessos a URL genericas do sistema.
 */
@Controller
public class GenericController extends ApostasController {
	
	//-------------------------------------------------------
	// ACESSOS PUBLICOS
	//-------------------------------------------------------
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
	public void login(@RequestBody Usuario usuario, HttpServletResponse resp, HttpSession session) {
                try {
                        super.sessaoUsuario.setUsuarioLogado(usuario.autenticar(), session);
                        resp.setStatus(200);
                } catch (LoginInvalidoException ex) {
                        resp.setStatus(401);
                }
	}
        
	@RequestMapping(value = "/principal/", method = RequestMethod.GET)
	public String abrir() {
		return "template";
	}
        
        
}
