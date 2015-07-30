package com.jopss.apostas.web;

import com.jopss.apostas.modelos.Usuario;
import com.jopss.apostas.web.util.ApostasController;
import javax.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController extends ApostasController {
	
        @RequestMapping(value = "/", method = RequestMethod.GET)
	public String abrir() {
		return "dashboard";
	}
        
        @ResponseBody
	@RequestMapping(value = "/apostas/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Usuario abrir(HttpSession session) {
                //FIXME: corrigir!!!!!!!!!!!!!!!!
		return super.sessaoUsuario.getUsuarioLogado(session);
	}
}
