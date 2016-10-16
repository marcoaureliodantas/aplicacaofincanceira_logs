/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao.managedbean;

import negocio.fachada.LoginFachada;
import apresentacao.utility.MensagemUtility;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import negocio.entidade.Grupo;
import negocio.entidade.Usuario;


/**
 *
 * @author Ivan Joao Foschini 
 */
@SessionScoped
@ManagedBean(name = "loginManagedBean")
public class LoginManagedBean implements Serializable {

    private static final String ADMINISTRADORES = "administradores";
    private static final String CLIENTES = "clientes";

    static Date DataAtual = new Date();
    
    @EJB
    private LoginFachada loginFachada;

  
    
    private Usuario usuario;
    private String nomeDeUsuario;
    private String senha;
    private boolean usuarioLogado = false;
    private boolean administrador = false;
    private boolean cliente = false;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(boolean usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public boolean isCliente() {
        return cliente;
    }

    public void setCliente(boolean cliente) {
        this.cliente = cliente;
    }

    public String efetuarLogin() {
        try {
            
            
            this.usuario = loginFachada.login(this.getNomeDeUsuario(), this.getSenha());
            this.recuperarGruposAosQuaisOUsuarioPertence(this.getUsuario());
            this.setUsuarioLogado(true);
                        
            return "Principal.xhtml";
        } catch (Exception e) {
            
            MensagemUtility.adicionarMensagemDeErro("formLogin", e.getMessage());
            this.setUsuarioLogado(false);
            return "/index.xhtml";
        }
    }

    public String efetuarLogout() {
        try {
            loginFachada.logout();
            this.setUsuarioLogado(false);
            return "/index.xhtml";
        } catch (Exception e) {
            return "Principal.xhtml";
        }
    }

    private void recuperarGruposAosQuaisOUsuarioPertence(Usuario usuario) {
        boolean usuarioPertenceAoGrupoDeAdministradores = false;
        boolean usuarioPertenceAoGrupoDeClientes = false;

        for (Grupo grupo : usuario.getGrupos()) {
            if (ADMINISTRADORES.equals(grupo.getGrupoPK().getNomeDoGrupo())) {
                usuarioPertenceAoGrupoDeAdministradores = true;
            }

            if (CLIENTES.equals(grupo.getGrupoPK().getNomeDoGrupo())) {
                usuarioPertenceAoGrupoDeClientes = true;
            }
        }

        if (usuarioPertenceAoGrupoDeAdministradores) {
            this.setAdministrador(true);
        } else {
            this.setAdministrador(false);
        }

        if (usuarioPertenceAoGrupoDeClientes) {
            this.setCliente(true);
        } else {
            this.setCliente(false);
        }
    }
}