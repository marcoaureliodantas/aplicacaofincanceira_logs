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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author RdOC
 */
@Entity
@Table(name = "conta", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"numero"})})
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo", length = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conta.findAll", query = "SELECT c FROM Conta c"),
    @NamedQuery(name = "Conta.findByContaId", query = "SELECT c FROM Conta c WHERE c.contaId = :contaId"),
    @NamedQuery(name = "Conta.findByNumero", query = "SELECT c FROM Conta c WHERE c.numero = :numero"),
    @NamedQuery(name = "Conta.findBySaldo", query = "SELECT c FROM Conta c WHERE c.saldo = :saldo"),
    @NamedQuery(name = "Conta.findByDataDeAbertura", query = "SELECT c FROM Conta c WHERE c.dataDeAbertura = :dataDeAbertura")})

public abstract class Conta implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "conta_id", nullable = false)
    @SequenceGenerator(name="Conta_Generator", sequenceName="conta_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Conta_Generator")            
    private Integer contaId;
    
    @Basic(optional = false)
    @Column(name = "numero", nullable = false)
    private int numero;
    
    @Basic(optional = false)
    @Column(name = "saldo", nullable = false)
    private float saldo;
    
    @Basic(optional = false)
    @Column(name = "data_de_abertura", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataDeAbertura;
        
    @JoinColumn(name = "agencia_id", referencedColumnName = "agencia_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Agencia agencia;
        
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conta", fetch = FetchType.LAZY)
    private Collection<ClienteTemConta> clienteTemContas;

    public Conta() {
    }

    public Integer getContaId() {
        return contaId;
    }

    public void setContaId(Integer contaId) {
        this.contaId = contaId;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public Date getDataDeAbertura() {
        return dataDeAbertura;
    }

    public void setDataDeAbertura(Date dataDeAbertura) {
        this.dataDeAbertura = dataDeAbertura;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    @XmlTransient
    public Collection<ClienteTemConta> getClienteTemContas() {
        return clienteTemContas;
    }

    public void setClienteTemContas(Collection<ClienteTemConta> clienteTemContas) {
        this.clienteTemContas = clienteTemContas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contaId != null ? contaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conta)) {
            return false;
        }
        Conta other = (Conta) object;
        if ((this.contaId == null && other.contaId != null) || (this.contaId != null && !this.contaId.equals(other.contaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testedojpa2.negocio.Conta[ contaId=" + contaId + " ]";
    }
    
}
