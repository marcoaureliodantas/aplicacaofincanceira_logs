/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.entidade;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author RdOC
 */
@Entity
@Table(name = "cliente")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo", length = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByClienteId", query = "SELECT c FROM Cliente c WHERE c.clienteId = :clienteId"),
    @NamedQuery(name = "Cliente.findByNome", query = "SELECT c FROM Cliente c WHERE c.nome = :nome"),
    @NamedQuery(name = "Cliente.findByDataDeInicioDeMoradia", query = "SELECT c FROM Cliente c WHERE c.dataDeInicioDeMoradia = :dataDeInicioDeMoradia"),
    @NamedQuery(name = "Cliente.findByStatus", query = "SELECT c FROM Cliente c WHERE c.status = :status")})

public abstract class Cliente implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "cliente_id", nullable = false)
    @SequenceGenerator(name="Cliente_Generator", sequenceName="cliente_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Cliente_Generator")        
    private Integer clienteId;
    
    @Basic(optional = false)
    @Column(name = "nome", nullable = false, length = 255)
    private String nome;
    
    @Basic(optional = false)
    @Column(name = "data_de_inicio_de_moradia", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataDeInicioDeMoradia;
    
    @Basic(optional = false)
    @Column(name = "status", nullable = false, length = 1)
    private String status;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.LAZY)
    private Collection<ClienteTemConta> clienteTemContas;
    
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Collection<Endereco> enderecos;

    public Cliente() {
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataDeInicioDeMoradia() {
        return dataDeInicioDeMoradia;
    }

    public void setDataDeInicioDeMoradia(Date dataDeInicioDeMoradia) {
        this.dataDeInicioDeMoradia = dataDeInicioDeMoradia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<ClienteTemConta> getClienteTemContas() {
        return clienteTemContas;
    }

    public void setClienteTemContas(Collection<ClienteTemConta> clienteTemContas) {
        this.clienteTemContas = clienteTemContas;
    }

    @XmlTransient
    public Collection<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Collection<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clienteId != null ? clienteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.clienteId == null && other.clienteId != null) || (this.clienteId != null && !this.clienteId.equals(other.clienteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testedojpa2.negocio.Cliente[ clienteId=" + clienteId + " ]";
    }
    
}
