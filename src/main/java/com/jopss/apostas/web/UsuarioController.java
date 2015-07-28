package com.jopss.apostas.web;

import com.jopss.apostas.modelos.Usuario;
import com.jopss.apostas.web.forms.Resposta;
import com.jopss.apostas.web.util.ApostasController;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsuarioController extends ApostasController{
        
        @RequestMapping(value = "/usuario/salvar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
	public Resposta salvar(@RequestBody Usuario usuario, HttpServletResponse resp, HttpSession session) {
                Resposta resposta = new Resposta();
                
                try {
                        usuario = usuario.salvar();
                        resp.setStatus(200);
                        resposta.setModelo(usuario);
                        
                } catch (TransactionSystemException ex) {
                        log.error(ex);
                        if(ex.getRootCause() instanceof ConstraintViolationException){
                               resposta.addErros( ((ConstraintViolationException)ex.getRootCause()) );
                               resp.setStatus(403);
                        }else{
                                resposta.addErroGenerico(ex);
                                resp.setStatus(500);
                        }
                } catch (Exception ex) {
                        log.error(ex);
                        resposta.addErroGenerico(ex);
                        resp.setStatus(500);
                        
                }
                
                return resposta;
	}
        
}
