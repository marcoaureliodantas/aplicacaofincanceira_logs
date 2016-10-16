/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao.managedbean;

import apresentacao.utility.MensagemUtility;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import  javax.servlet.http.HttpSession;
import negocio.entidade.Banco;
import negocio.fachada.BancoFachada;


/**
 *
 * @author Ivan Joao Foschini 
 */
@SessionScoped

@ManagedBean(name = "bancoManagedBean")
public class BancoManagedBean implements Serializable {

    private Banco banco;

    private List<Banco> bancos = new ArrayList<>();

    
    @EJB
    private BancoFachada bancoFachada;
    
    @EJB
    private BancoFachada bancoFachadaOld;

    public BancoManagedBean() {}

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public List<Banco> getBancos() {
        return bancos;
    }

    public String alterar() {
        try {
            
                                  
           
            Banco banco_old  = bancoFachadaOld.ConsultaPK(banco.getBancoId());
            
            
            // banco_old = (Banco)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("yyyyy");
            //HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            //HttpSession session = (HttpSession) request.getSession();
           
            
            bancoFachada.alterar(banco_old, this.getBanco());
            this.recuperarBancos();
            return "/banco/ListarBancos";
        } catch (Exception e) {
            MensagemUtility.adicionarMensagemDeErro("formBanco", e.getMessage());
            return "/banco/AlterarBanco";
        }
    }

    public String excluir() {
         try {
            bancoFachada.excluir(this.getBanco());
            this.recuperarBancos();
            return "/banco/ListarBancos";
        } catch (Exception e) {
            MensagemUtility.adicionarMensagemDeErro("formBanco", e.getMessage());
            return "/banco/ExcluirBanco";
        }            
    }

    public String inserir() {
         try {
            bancoFachada.inserir(this.getBanco());
            this.recuperarBancos();
            return "/banco/ListarBancos";
        } catch (Exception e) {
            MensagemUtility.adicionarMensagemDeErro("formBanco", e.getMessage());
            return "/banco/InserirBanco";
        }
    }

    public String listar() {
     
            this.recuperarBancos();
            return "/banco/ListarBancos";
                
     
    }

    public String montarPaginaParaAlteracao() {
        
        //guardar valores atuais do banco, antes de alteracao
        //banco_old = this.banco;
        
       //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("yyyyy", this.banco);
    
       return "/banco/AlterarBanco";      
        
    }

    public String montarPaginaParaExclusao() {
        return "/banco/ExcluirBanco";
    }

    public String montarPaginaParaInsercao() {
        this.banco = new Banco();
        return "/banco/InserirBanco";
    }

    private void recuperarBancos() {
        this.bancos = bancoFachada.listar();
    }
}