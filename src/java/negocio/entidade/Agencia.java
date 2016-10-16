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
@Table(name = "agencia", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"endereco_id"}),
    @UniqueConstraint(columnNames = {"numero"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agencia.findAll", query = "SELECT a FROM Agencia a"),
    @NamedQuery(name = "Agencia.findByAgenciaId", query = "SELECT a FROM Agencia a WHERE a.agenciaId = :agenciaId"),
    @NamedQuery(name = "Agencia.findByNumero", query = "SELECT a FROM Agencia a WHERE a.numero = :numero"),
    @NamedQuery(name = "Agencia.findByNome", query = "SELECT a FROM Agencia a WHERE a.nome = :nome")})

public class Agencia implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "agencia_id", nullable = false)
    @SequenceGenerator(name="Agencia_Generator", sequenceName="agencia_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Agencia_Generator")
    private Integer agenciaId;
    
    @Basic(optional = false)
    @Column(name = "numero", nullable = false)
    private int numero;
    
    @Basic(optional = false)
    @Column(name = "nome", nullable = false, length = 255)
    private String nome;
    
    @JoinColumn(name = "endereco_id", referencedColumnName = "endereco_id", nullable = false)
    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    private Endereco endereco;
    
    @JoinColumn(name = "banco_id", referencedColumnName = "banco_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Banco banco;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agencia", fetch = FetchType.LAZY)
    private Collection<Conta> contas;

    public Agencia() {
    }

    public Integer getAgenciaId() {
        return agenciaId;
    }

    public void setAgenciaId(Integer agenciaId) {
        this.agenciaId = agenciaId;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    @XmlTransient
    public Collection<Conta> getContas() {
        return contas;
    }

    public void setContas(Collection<Conta> contas) {
        this.contas = contas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (agenciaId != null ? agenciaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agencia)) {
            return false;
        }
        Agencia other = (Agencia) object;
        if ((this.agenciaId == null && other.agenciaId != null) || (this.agenciaId != null && !this.agenciaId.equals(other.agenciaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testedojpa2.negocio.Agencia[ agenciaId=" + agenciaId + " ]";
    }
    
    
   
    
}
