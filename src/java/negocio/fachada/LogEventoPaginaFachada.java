/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.fachada;

import negocio.entidade.LogEventoPagina;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import persistencia.LogAtividadeDAO;
import persistencia.LogEventoPaginaDAO;
import persistencia.LogPaginaDAO;



@Stateless
public class LogEventoPaginaFachada {
    
    @EJB
    private LogPaginaDAO LogPaginaDAO;
    @EJB
    private LogAtividadeDAO LogAtividadeDAO;
    @EJB 
    private LogEventoPaginaDAO LogEventoPaginaDAO;
   
    
    static Date DataAtual = new Date();
    
    public static String getNomeUsuarioLogado() {
        return (String)  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("NomeUsuario");   
    }
    
    
//    public void InsereNovoLog(String NomeAtividade, String NomePagina, Integer ObjId, String NomeUsuario, Date DataAtual) {
//        
//        LogEventoPagina l = new  LogEventoPagina();
//        l.setLogPagina(LogPaginaDAO.getIdPagina(NomePagina));
//        l.setLogAtividade(LogAtividadeDAO.getIdAtividade(NomeAtividade));
//        l.setIdObjeto(ObjId);
//        l.setNomeUsuario(LogEventoPaginaFachada.getNomeUsuarioLogado());
//        l.setDataInsercao(DataAtual);
//        l.setHoraInsercao(DataAtual);
//        
//        LogEventoPaginaDAO.inserir (l);
//               
//    }
    
    
       public void InsereNovoLog(String NomeAtividade, String NomePagina, Integer ObjId) {
        
        LogEventoPagina l = new  LogEventoPagina();
        l.setLogPagina(LogPaginaDAO.getIdPagina(NomePagina));
        l.setLogAtividade(LogAtividadeDAO.getIdAtividade(NomeAtividade));
        l.setIdObjeto(ObjId);
        l.setNomeUsuario(LogEventoPaginaFachada.getNomeUsuarioLogado());
        l.setDataInsercao(DataAtual);
        l.setHoraInsercao(DataAtual);
        
        LogEventoPaginaDAO.inserir (l);
               
    }
         
    
      
  
    
    
}
