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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cidade", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nome"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cidade.findAll", query = "SELECT c FROM Cidade c"),
    @NamedQuery(name = "Cidade.findByCidadeId", query = "SELECT c FROM Cidade c WHERE c.cidadeId = :cidadeId"),
    @NamedQuery(name = "Cidade.findByNome", query = "SELECT c FROM Cidade c WHERE c.nome = :nome")})

public class Cidade implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "cidade_id", nullable = false)
    @SequenceGenerator(name="Cidade_Generator", sequenceName="cidade_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Cidade_Generator")    
    private Integer cidadeId;
    
    @Basic(optional = false)
    @Column(name = "nome", nullable = false, length = 255)
    private String nome;
    
    @JoinColumn(name = "estado_id", referencedColumnName = "estado_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estado estado;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cidade", fetch = FetchType.LAZY)
    private Collection<Endereco> enderecos;

    public Cidade() {
    }

    public Cidade(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }

    public Cidade(Integer cidadeId, String nome) {
        this.cidadeId = cidadeId;
        this.nome = nome;
    }

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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
        hash += (cidadeId != null ? cidadeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cidade)) {
            return false;
        }
        Cidade other = (Cidade) object;
        if ((this.cidadeId == null && other.cidadeId != null) || (this.cidadeId != null && !this.cidadeId.equals(other.cidadeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testedojpa2.negocio.Cidade[ cidadeId=" + cidadeId + " ]";
    }
    
}
