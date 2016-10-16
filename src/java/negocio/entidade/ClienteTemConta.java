/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author RdOC
 */
@Entity
@Table(name = "cliente_tem_conta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteTemConta.findAll", query = "SELECT c FROM ClienteTemConta c"),
    @NamedQuery(name = "ClienteTemConta.findByClienteId", query = "SELECT c FROM ClienteTemConta c WHERE c.clienteTemContaPK.clienteId = :clienteId"),
    @NamedQuery(name = "ClienteTemConta.findByContaId", query = "SELECT c FROM ClienteTemConta c WHERE c.clienteTemContaPK.contaId = :contaId"),
    @NamedQuery(name = "ClienteTemConta.findByTitularidade", query = "SELECT c FROM ClienteTemConta c WHERE c.titularidade = :titularidade")})

public class ClienteTemConta implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected ClienteTemContaPK clienteTemContaPK;
    
    @Basic(optional = false)
    @Column(name = "titularidade", nullable = false, length = 1)
    private String titularidade;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteTemConta", fetch = FetchType.LAZY)
    private Collection<Transacao> transacoes;
    
    @JoinColumn(name = "conta_id", referencedColumnName = "conta_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Conta conta;
    
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente cliente;

    public ClienteTemConta() {
    }

    public ClienteTemConta(ClienteTemContaPK clienteTemContaPK) {
        this.clienteTemContaPK = clienteTemContaPK;
    }

    public ClienteTemConta(ClienteTemContaPK clienteTemContaPK, String titularidade) {
        this.clienteTemContaPK = clienteTemContaPK;
        this.titularidade = titularidade;
    }

    public ClienteTemConta(int clienteId, int contaId) {
        this.clienteTemContaPK = new ClienteTemContaPK(clienteId, contaId);
    }

    public ClienteTemContaPK getClienteTemContaPK() {
        return clienteTemContaPK;
    }

    public void setClienteTemContaPK(ClienteTemContaPK clienteTemContaPK) {
        this.clienteTemContaPK = clienteTemContaPK;
    }

    public String getTitularidade() {
        return titularidade;
    }

    public void setTitularidade(String titularidade) {
        this.titularidade = titularidade;
    }

    @XmlTransient
    public Collection<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(Collection<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clienteTemContaPK != null ? clienteTemContaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClienteTemConta)) {
            return false;
        }
        ClienteTemConta other = (ClienteTemConta) object;
        if ((this.clienteTemContaPK == null && other.clienteTemContaPK != null) || (this.clienteTemContaPK != null && !this.clienteTemContaPK.equals(other.clienteTemContaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testedojpa2.negocio.ClienteTemConta[ clienteTemContaPK=" + clienteTemContaPK + " ]";
    }
    
}
