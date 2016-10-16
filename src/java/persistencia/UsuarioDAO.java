/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import negocio.entidade.Usuario;

/**
 *
 * @author Ivan Joao Foschini 
 */
@Stateless
public class UsuarioDAO {

    @PersistenceContext
    private EntityManager em;

    public Usuario recuperarPorNomeDeUsuario(String nomeDeUsuario) {
         try {
            return (Usuario) em.createQuery("select usu from Usuario as usu where usu.nomeDeUsuario = '" + nomeDeUsuario + "'").getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}