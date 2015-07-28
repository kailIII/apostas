package com.jopss.apostas.excecoes;

public class LoginInvalidoException extends ApostasException {

        public LoginInvalidoException() {
                super();
        }
        
        public LoginInvalidoException(String message) {
                super(message);
        }

        public LoginInvalidoException(Throwable cause) {
                super(cause);
        }
        
}
