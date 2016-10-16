/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.fachada;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import negocio.entidade.Usuario;
import persistencia.UsuarioDAO;




/**
 *
 * @author Ivan Joao Foschini 
 */
@Stateless
public class LoginFachada {

    private static final String NAO_FOI_POSSIVEL_EFETUAR_O_LOGIN = "N\u00e3o foi poss\u00edvel efetuar o login";
    private static final String NAO_FOI_POSSIVEL_EFETUAR_O_LOGOUT = "N\u00e3o foi poss\u00edvel efetuar o logout";

    
    @EJB
    private UsuarioDAO usuarioDAO;
    
    @EJB 
    private LogErroFachada logerro;
    
    @EJB 
    private LogEventoPaginaFachada logevento;
    
        
    static Date DataAtual = new Date();
    
    public Usuario login(String nomeDeUsuario, String senha) throws LoginException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            
            request.login(nomeDeUsuario, senha);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("NomeUsuario", nomeDeUsuario);  
            
            //logevento.InsereNovoLog("Solicitacao","Login",0,nomeDeUsuario, DataAtual);
            logevento.InsereNovoLog("Solicitacao","Login",0);
                                     
            return usuarioDAO.recuperarPorNomeDeUsuario(nomeDeUsuario);
    
        } catch (ServletException ex) {
           
            //logerro.InsereNovoLog("Login",0,nomeDeUsuario,ex.getMessage(),DataAtual);
            logerro.InsereNovoLog("Login",0,ex.getMessage());
          
            throw new LoginException(NAO_FOI_POSSIVEL_EFETUAR_O_LOGIN);
            
        }        
    }
    
    public void logout() throws LoginException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException ex) {
            throw new LoginException(NAO_FOI_POSSIVEL_EFETUAR_O_LOGOUT);
        }
    }
}