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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author RdOC
 */
@Entity
@Table(name = "banco", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"numero"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Banco.findAll", query = "SELECT b FROM Banco b"),
    @NamedQuery(name = "Banco.findByBancoId", query = "SELECT b FROM Banco b WHERE b.bancoId = :bancoId"),
    @NamedQuery(name = "Banco.findByNumero", query = "SELECT b FROM Banco b WHERE b.numero = :numero"),
    @NamedQuery(name = "Banco.findByCnpj", query = "SELECT b FROM Banco b WHERE b.cnpj = :cnpj"),
    @NamedQuery(name = "Banco.findByNome", query = "SELECT b FROM Banco b WHERE b.nome = :nome")})

public class Banco implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "banco_id", nullable = false)
    @SequenceGenerator(name="Banco_Generator", sequenceName="banco_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Banco_Generator")
    private Integer bancoId;
    
    @Basic(optional = false)
    @Column(name = "numero", nullable = false)
    private int numero;
    
    @Basic(optional = false)
    @Column(name = "cnpj", nullable = false, length = 14)
    private String cnpj;
    
    @Basic(optional = false)
    @Column(name = "nome", nullable = false, length = 255)
    private String nome;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "banco", fetch = FetchType.LAZY)
    private Collection<Agencia> agencias;

    public Banco() {
    }

    public Integer getBancoId() {
        return bancoId;
    }

    public void setBancoId(Integer bancoId) {
        this.bancoId = bancoId;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public Collection<Agencia> getAgencias() {
        return agencias;
    }

    public void setAgencias(Collection<Agencia> agencias) {
        this.agencias = agencias;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bancoId != null ? bancoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Banco)) {
            return false;
        }
        Banco other = (Banco) object;
        if ((this.bancoId == null && other.bancoId != null) || (this.bancoId != null && !this.bancoId.equals(other.bancoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testedojpa2.negocio.Banco[ bancoId=" + bancoId + " ]";
    }
    
      
    
}
