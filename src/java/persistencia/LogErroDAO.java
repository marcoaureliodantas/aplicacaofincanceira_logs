/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import negocio.entidade.LogErro;

/**
 *
 * @author marco
 */

@Stateless //(mappedName= "DAO_LogErro")
public class LogErroDAO {
    
        
      
    @PersistenceContext
    private EntityManager em;
    
    
    public void inserir(LogErro logerro) {
        em.persist(logerro);
    }
    
      /*public void InsereNovoLog(String NomePagina, Integer ObjId, String NomeUsuario, String MsgErro, Date DataAtual) {
         
                               
            l.setLogpagina(LogPaginaDAO.getIdPagina(NomePagina));
            l.setIdObjeto(ObjId);
            l.setNomeUsuario(NomeUsuario);
            l.setMsgErro(MsgErro);
            l.setDataInclusao(DataAtual);
            em.persist(l);
               
      
  
    }*/
    
    
   
    
}
