/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import negocio.entidade.Transacao;

/**
 *
 * @author Ivan Joao Foschini 
 */
@Stateless
public class TransacaoDAO {
    
    @PersistenceContext
    private EntityManager em;
    
    public void inserir(Transacao transacao) {
        em.persist(transacao);
    }    
}