/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author RdOC
 */
@Embeddable
public class ClienteTemContaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "cliente_id", nullable = false)
    private int clienteId;
    @Basic(optional = false)
    @Column(name = "conta_id", nullable = false)
    private int contaId;

    public ClienteTemContaPK() {
    }

    public ClienteTemContaPK(int clienteId, int contaId) {
        this.clienteId = clienteId;
        this.contaId = contaId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getContaId() {
        return contaId;
    }

    public void setContaId(int contaId) {
        this.contaId = contaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) clienteId;
        hash += (int) contaId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClienteTemContaPK)) {
            return false;
        }
        ClienteTemContaPK other = (ClienteTemContaPK) object;
        if (this.clienteId != other.clienteId) {
            return false;
        }
        if (this.contaId != other.contaId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testedojpa2.negocio.ClienteTemContaPK[ clienteId=" + clienteId + ", contaId=" + contaId + " ]";
    }
    
}
