/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import negocio.entidade.Agencia;

/**
 *
 * @author Ivan Joao Foschini 
 */
@Stateless
public class AgenciaDAO {

    @PersistenceContext
    private EntityManager em;

    public void excluir(Agencia agencia) {
        Agencia agenciaASerExcluida = em.merge(agencia);
        em.remove(agenciaASerExcluida);
    }

    public void alterar(Agencia agencia) {
        em.merge(agencia);
    }

    public void inserir(Agencia agencia) {
        em.persist(agencia);
    }

    public List<Agencia> recuperarTodos() {
        return em.createQuery("select age from Agencia as age order by age.nome").getResultList();
    }

    public boolean verificarSeONumeroFornecidoJaPertenceAOutraAgencia(Integer numero) {
        if (!em.createQuery("select age from Agencia as age where age.numero = " + numero).getResultList().isEmpty()) {
            return true;
        }

        return false;
    }

    public boolean verificarSeONumeroFornecidoJaPertenceAOutraAgencia(Integer numero, Integer idDaAgenciaCujosDadosEstaoSendoAlterados) {
        if (!em.createQuery("select age from Agencia as age where age.numero = " + numero + " and age.agenciaId <> " + idDaAgenciaCujosDadosEstaoSendoAlterados).getResultList().isEmpty()) {
            return true;
        }

        return false;
    }
    
    public Agencia recuperarPorId(Integer id) {
        return em.find(Agencia.class, id);
    }
    
}