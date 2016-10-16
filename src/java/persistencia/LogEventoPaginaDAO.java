/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import negocio.entidade.LogEventoPagina;

/**
 *
 * @author marco
 */

@Stateless //(mappedName= "DAO_LogEventoPagina")
public class LogEventoPaginaDAO {
    
    
    @PersistenceContext
    private EntityManager em;
    
    public void inserir(LogEventoPagina logeventopagina) {
        em.persist(logeventopagina);
    }
    
   
}
