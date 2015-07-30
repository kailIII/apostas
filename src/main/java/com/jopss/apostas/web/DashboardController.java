package com.jopss.apostas.web;

import com.jopss.apostas.modelos.Aposta;
import com.jopss.apostas.web.forms.Resposta;
import com.jopss.apostas.web.util.ApostasController;
import javax.servlet.http.HttpServletResponse;
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
        
        @RequestMapping(value = "/apostas/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
	public Resposta buscarTodos(HttpServletResponse resp, HttpSession session) {
                Resposta resposta = new Resposta();
                try{
                        
                        resposta.setLista((new Aposta()).buscarTodos(), resp);
                } catch (Exception ex) {
                        log.error(ex);
                        resposta.addErroGenerico(ex, resp);
                }
                return resposta;
	}
}
