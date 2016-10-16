/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import negocio.entidade.Estado;

/**
 *
 * @author Ivan Joao Foschini 
 */
@Stateless
public class EstadoDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Estado> recuperarTodos() {
        return em.createQuery("select est from Estado as est order by est.nome").getResultList();
    }
}