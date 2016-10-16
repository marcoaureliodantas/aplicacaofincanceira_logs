/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import negocio.entidade.LogPagina;

/**
 *
 * @author marco
 */
@Stateless
public class LogPaginaDAO {
 
    @PersistenceContext
    private EntityManager em;
    
    public LogPagina recuperarPorId(Integer id) {
        return em.find(LogPagina.class, id);
    }

    public List<LogPagina> recuperarTodos() {
        return em.createQuery("select pag from LogPagina as pag order by pag.nomepagina").getResultList();
    }
    
    
    public LogPagina   getIdPagina(String NomePagina) {
            
         return (LogPagina) em.createQuery("select  a from LogPagina a where a.NomePagina = :NomePagina").setParameter("NomePagina", NomePagina).getSingleResult();
    }
    
}
