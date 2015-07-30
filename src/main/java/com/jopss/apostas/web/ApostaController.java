package com.jopss.apostas.web;

import com.jopss.apostas.modelos.Aposta;
import com.jopss.apostas.modelos.Palpite;
import com.jopss.apostas.web.forms.Resposta;
import com.jopss.apostas.web.util.ApostasController;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/aposta")
public class ApostaController extends ApostasController {

        @RequestMapping(value = "/", method = RequestMethod.GET)
	public String abrir() {
		return "aposta/cadastro";
	}
        
        @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public Resposta salvar(@RequestBody Aposta aposta, HttpServletResponse resp, HttpSession session) {
                Resposta resposta = new Resposta();
                try {
                        aposta = aposta.salvar();
                        resposta.setModelo(aposta, resp);
                        
                } catch (TransactionSystemException ex) {
                        log.error(ex);
                        resposta.addErros(ex, resp);
                } catch (Exception ex) {
                        log.error(ex);
                        resposta.addErroGenerico(ex, resp);
                }
                return resposta;
        }

}
