/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import negocio.entidade.Cidade;

/**
 *
 * @author Ivan Joao Foschini 
 */
@Stateless
public class CidadeDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Cidade> recuperarPorEstado(Integer idDoEstado) {
        return em.createQuery("select cid from Cidade as cid join cid.estado as est where est.estadoId = " + idDoEstado + " order by cid.nome").getResultList();
    }

    public Cidade recuperarPorId(Integer id) {
        return em.find(Cidade.class, id);
    }
}