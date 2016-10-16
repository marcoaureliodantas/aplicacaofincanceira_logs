/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "caixa_eletronico", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"endereco_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaixaEletronico.findAll", query = "SELECT c FROM CaixaEletronico c"),
    @NamedQuery(name = "CaixaEletronico.findByCaixaEletronicoId", query = "SELECT c FROM CaixaEletronico c WHERE c.caixaEletronicoId = :caixaEletronicoId")})

public class CaixaEletronico implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "caixa_eletronico_id", nullable = false)
    @SequenceGenerator(name="CaixaEletronico_Generator", sequenceName="cxeletronico_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CaixaEletronico_Generator")    
    private Integer caixaEletronicoId;
    
    @OneToMany(mappedBy = "caixaEletronico", fetch = FetchType.LAZY)
    private Collection<Transacao> transacoes;
    
    @JoinColumn(name = "endereco_id", referencedColumnName = "endereco_id", nullable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Endereco endereco;

    public CaixaEletronico() {
    }

    public CaixaEletronico(Integer caixaEletronicoId) {
        this.caixaEletronicoId = caixaEletronicoId;
    }

    public Integer getCaixaEletronicoId() {
        return caixaEletronicoId;
    }

    public void setCaixaEletronicoId(Integer caixaEletronicoId) {
        this.caixaEletronicoId = caixaEletronicoId;
    }

    @XmlTransient
    public Collection<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(Collection<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (caixaEletronicoId != null ? caixaEletronicoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaixaEletronico)) {
            return false;
        }
        CaixaEletronico other = (CaixaEletronico) object;
        if ((this.caixaEletronicoId == null && other.caixaEletronicoId != null) || (this.caixaEletronicoId != null && !this.caixaEletronicoId.equals(other.caixaEletronicoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testedojpa2.negocio.CaixaEletronico[ caixaEletronicoId=" + caixaEletronicoId + " ]";
    }
    
}
