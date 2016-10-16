/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.fachada;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import negocio.entidade.LogPaginaHistoricoDados;
import persistencia.LogPaginaDAO;
import persistencia.LogPaginaHistoricoDadosDAO;

/**
 *
 * @author marco
 */
@Stateless
public class LogPaginaHistoricoDadosFachada {
    
    
    @EJB
    private LogPaginaDAO LogPaginaDAO;
    
    @EJB
    private LogPaginaHistoricoDadosDAO LogPaginaHistoricoDadosDAO;
    
    
    static Date DataAtual = new Date();
    
    public static String getNomeUsuarioLogado() {
        return (String)  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("NomeUsuario");   
    }
       
//    public void InsereNovoLog(String NomePagina, Integer ObjId, String NomeUsuario, String CampoAlterado, Object ConteudoAnterior, Object ConteudoAtual,  Date DataAtual) {
//        System.out.println("AQUI InsereNovoLog = "+NomePagina+" "+ObjId+" "+NomeUsuario+" "+CampoAlterado+" "+ConteudoAnterior+" "+ConteudoAtual);
//        LogPaginaHistoricoDados l = new  LogPaginaHistoricoDados();
//        l.setIdPagina(LogPaginaDAO.getIdPagina(NomePagina));
//        l.setIdObjeto(ObjId);
//        l.setNomeUsuario(LogPaginaHistoricoDadosFachada.getNomeUsuarioLogado());
//        l.setCampoAlterado(CampoAlterado);
//        l.setConteudoAnterior(ConteudoAnterior.toString());
//        l.setConteudoAtual(ConteudoAtual.toString());
//        l.setDataAlteracao(DataAtual);
//        l.setHoraAlteracao(DataAtual);
//        
//        LogPaginaHistoricoDadosDAO.inserir (l);
//               
//    }
    
     public void InsereNovoLog(String NomePagina, Integer ObjId, String CampoAlterado, Object ConteudoAnterior, Object ConteudoAtual) {
        
        LogPaginaHistoricoDados l = new  LogPaginaHistoricoDados();
        l.setIdPagina(LogPaginaDAO.getIdPagina(NomePagina));
        l.setIdObjeto(ObjId);
        l.setNomeUsuario(LogPaginaHistoricoDadosFachada.getNomeUsuarioLogado());
        l.setCampoAlterado(CampoAlterado);
        l.setConteudoAnterior(ConteudoAnterior.toString());
        l.setConteudoAtual(ConteudoAtual.toString());
        l.setDataAlteracao(DataAtual);
        l.setHoraAlteracao(DataAtual);
        
        LogPaginaHistoricoDadosDAO.inserir (l);
               
    }
    
    
}
