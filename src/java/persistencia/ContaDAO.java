/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import negocio.entidade.Conta;

/**
 *
 * @author Ivan Joao Foschini 
 */
@Stateless
public class ContaDAO {

    @PersistenceContext
    private EntityManager em;

    public Conta recuperarPorNumero(Integer numero) {
        try {
            return (Conta) em.createQuery("select con from Conta as con where con.numero = " + numero).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}