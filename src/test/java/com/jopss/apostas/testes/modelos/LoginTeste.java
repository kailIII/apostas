package com.jopss.apostas.testes.modelos;

import com.jopss.apostas.excecoes.LoginInvalidoException;
import com.jopss.apostas.modelos.Usuario;
import com.jopss.apostas.servicos.repositorio.UsuarioRepositorio;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginTeste {
        
        @Test(expected = LoginInvalidoException.class)
        public void autenticarComLoginInexistente() throws LoginInvalidoException {

                Usuario usuarioRequisicao = new Usuario();
                usuarioRequisicao.setRepositorio(mockarUsuarioRepositorio());
                usuarioRequisicao.setLogin("testeLoginErrado");
                usuarioRequisicao.setSenha("senhaTeste");

                usuarioRequisicao.autenticar();
        }
        
        @Test(expected = LoginInvalidoException.class)
        public void autenticarComSenhaErrada() throws LoginInvalidoException {

                Usuario usuarioRequisicao = new Usuario();
                usuarioRequisicao.setRepositorio(mockarUsuarioRepositorio());
                usuarioRequisicao.setLogin("testeLogin");
                usuarioRequisicao.setSenha("123");

                usuarioRequisicao.autenticar();
        }
        
        @Test
        public void autenticarComSucesso() throws LoginInvalidoException {

                Usuario usuarioRequisicao = new Usuario();
                usuarioRequisicao.setRepositorio(mockarUsuarioRepositorio());
                usuarioRequisicao.setLogin("testeLogin");
                usuarioRequisicao.setSenha("senhaTeste");

                Usuario ret = usuarioRequisicao.autenticar();
                Assert.assertNotNull(ret.getId());
                Assert.assertEquals(usuarioRequisicao.getLogin(), ret.getLogin());
                Assert.assertEquals(usuarioRequisicao.getSenha(), ret.getSenha());
        }
        
        private UsuarioRepositorio mockarUsuarioRepositorio(){
                
                Usuario userMockado = new Usuario();
                userMockado.setId(1L);
                userMockado.setLogin("testeLogin");
                userMockado.setSenha("senhaTeste");

                UsuarioRepositorio repoMock = mock(UsuarioRepositorio.class);
                when(repoMock.buscarPorLoginESenha("testeLogin","senhaTeste")).thenReturn(userMockado);
                
                return repoMock;
        }
}
