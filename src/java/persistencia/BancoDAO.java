/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import negocio.entidade.Banco;

/**
 *
 * @author Ivan Joao Foschini
 */
@Stateless
public class BancoDAO {

    @PersistenceContext
    private EntityManager em;

    //jpa - transiente,persistente,desligado,removido
    public void alterar(Banco banco) {
        em.merge(banco);
    }

    public void excluir(Banco banco) {
        Banco bancoASerExcluido = em.merge(banco);
        em.remove(bancoASerExcluido);
    }

    public void inserir(Banco banco) {
        em.persist(banco);
    }

    public Banco recuperarPorId(Integer id) {
        return em.find(Banco.class, id);
    }

    public List<Banco> recuperarTodos() {
        return em.createQuery("select ban from Banco as ban order by ban.nome").getResultList();
    }

    public boolean verificarSeOBancoSelecionadoPossuiAgenciasAssociadas(Integer idDoBanco) {
        Banco banco = (Banco) em.createQuery("select ban from Banco as ban where ban.bancoId = " + idDoBanco).getSingleResult();

        if (!banco.getAgencias().isEmpty()) {
            return true;
        }

        return false;
    }

    public boolean verificarSeONumeroFornecidoJaPertenceAOutroBanco(Integer numero) {
        if (!em.createQuery("select ban from Banco as ban where ban.numero = " + numero).getResultList().isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean verificarSeONumeroFornecidoJaPertenceAOutroBanco(Integer numero, Integer idDoBancoCujosDadosEstaoSendoAlterados) {
        if (!em.createQuery("select ban from Banco as ban where ban.numero = " + numero + " and ban.bancoId <> " + idDoBancoCujosDadosEstaoSendoAlterados).getResultList().isEmpty()) {
            return true;
        }

        //em.createNamedQuery("Banco.findByNumero")
        return false;
    }
}
