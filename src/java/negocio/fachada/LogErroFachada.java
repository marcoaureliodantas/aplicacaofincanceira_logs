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
import negocio.entidade.LogErro;
import persistencia.LogErroDAO;
import persistencia.LogPaginaDAO;

/**
 *
 * @author marco
 */
@Stateless//(mappedName= "Fachada_LogErro")
public class LogErroFachada {

   
    @EJB
    private LogPaginaDAO LogPaginaDAO;
    
    @EJB
    private LogErroDAO logErroDAO;
    
 
    static Date DataAtual = new Date();
    
    public static String getNomeUsuarioLogado() {
        return (String)  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("NomeUsuario");   
    }
    
    
//    public void InsereNovoLog(String NomePagina, Integer ObjId, String NomeUsuario, String MsgErro, Date DataAtual){
//               LogErro l = new LogErro();      
//            l.setLogpagina(LogPaginaDAO.getIdPagina(NomePagina));
//            l.setIdObjeto(ObjId);
//            l.setNomeUsuario(LogErroFachada.getNomeUsuarioLogado());
//            l.setMsgErro(MsgErro);
//            l.setDataInclusao(DataAtual);
//            l.setHoraInclusao(DataAtual);
//            logErroDAO.inserir(l);
//               
//               
//    }
    
      public void InsereNovoLog(String NomePagina, Integer ObjId,  String MsgErro){
               LogErro l = new LogErro();      
            l.setLogpagina(LogPaginaDAO.getIdPagina(NomePagina));
            l.setIdObjeto(ObjId);
            l.setNomeUsuario(LogErroFachada.getNomeUsuarioLogado());
            l.setMsgErro(MsgErro);
            l.setDataInclusao(DataAtual);
            l.setHoraInclusao(DataAtual);
            logErroDAO.inserir(l);
               
               
    }
         
   
}
