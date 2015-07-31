package com.jopss.apostas.web.util;

import com.jopss.apostas.modelos.Usuario;
import com.jopss.apostas.servicos.security.SessionUserSupport;
import com.jopss.apostas.util.FormatterAndValues;
import com.jopss.apostas.util.NumbersUtils;
import com.jopss.apostas.servicos.ParametrosSistema;
import com.jopss.apostas.util.ValidatorUtil;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.PropertyValuesEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Superclasse de todo Controlador do sistema.
 */
public abstract class ApostasController {
	
	protected Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	protected SessionUserSupport sessionUserSupport;
	
        @Autowired
        protected ParametrosSistema parametrosSistema;
        
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		
		binder.registerCustomEditor(Usuario.class, new PropertyValuesEditor() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				if (ValidatorUtil.isNotNullAndNotEmpty(text)) {
					setValue(new Usuario(Long.valueOf(text)).buscarPorId());
				} else {
					setValue(null);
				}
			}

			@Override
			public String getAsText() {
				if (getValue() == null || ((Usuario) getValue()).getId() == null) {
					return "";
				}
				return ((Usuario) getValue()).getId().toString();
			}
		});

		binder.registerCustomEditor(BigDecimal.class, new PropertyValuesEditor() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(NumbersUtils.convertStringToBigDecimal(text));
			}

			@Override
			public String getAsText() {
				return NumbersUtils.convertBigDecimalToStringFormat((BigDecimal) getValue());
			}
		});

		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}
	
	/**
	 * Adiciona mensagem do tipo sucesso (barra verde) para uma mensagem fixa.
	 * 
	 * Eh utilizado o escopo flash (entre requests) para redicionamentos. Esta conforme a tag 'adv:messages' espera, sendo assim,
	 * nao eh necessario adicionar outras tags no jsp.
	 */
	protected void addFlashSuccessMessage(final RedirectAttributes redirectAttrs, String keyMessage, String... args) {
		if (ValidatorUtil.isNotNullAndNotEmpty(keyMessage)) {
			redirectAttrs.addFlashAttribute("flashSuccessMessage", FormatterAndValues.getMessage(keyMessage, args));
		} else {
			redirectAttrs.addFlashAttribute("flashSuccessMessage", "Ops. Null! :(");
		}
	}
	
	/**
	 * Adiciona mensagem do tipo alerta (barra laranja) para uma mensagem fixa. Esta mensagem NAO caracteriza algum problema ou erro, mas validacao de regras.
	 * 
	 * Eh utilizado o escopo flash (entre requests) para redicionamentos. Esta conforme a tag 'adv:messages' espera, sendo assim,
	 * nao eh necessario adicionar outras tags no jsp.
	 */
        protected void addFlashValidationMessage(final RedirectAttributes redirectAttrs, String keyMessage, String... args) {
		if (ValidatorUtil.isNotNullAndNotEmpty(keyMessage)) {
			redirectAttrs.addFlashAttribute("flashValidationMessage", FormatterAndValues.getMessage(keyMessage, args));
		} else {
			redirectAttrs.addFlashAttribute("flashValidationMessage", "Ops. Null! :(");
		}
	}
	
	/**
	 * Adiciona mensagem do tipo erro (barra vermelha) para uma mensagem fixa. Esta mensagem caracteriza algum problema ou erro,
	 * e nao possui uma key relativa no messages.properties.
	 * 
	 * Eh utilizado o escopo flash (entre requests) para redicionamentos. Esta conforme a tag 'adv:messages' espera, sendo assim,
	 * nao eh necessario adicionar outras tags no jsp.
	 */
	protected void addFlashErrorMessage(final RedirectAttributes redirectAttrs, String messageError) {
		if (ValidatorUtil.isNotNullAndNotEmpty(messageError)) {
			redirectAttrs.addFlashAttribute("flashErrorMessage", messageError);
		} else {
			redirectAttrs.addFlashAttribute("flashErrorMessage", "Ops. Null! :(");
		}
	}
	
	protected void addDataInFlashScope(final RedirectAttributes redirectAttrs, final String key, final Object data) {
		redirectAttrs.addFlashAttribute(key, data);
	}
}
