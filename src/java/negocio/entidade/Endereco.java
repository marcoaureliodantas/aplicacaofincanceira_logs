/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.entidade;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RdOC
 */
@Entity
@Table(name = "endereco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Endereco.findAll", query = "SELECT e FROM Endereco e"),
    @NamedQuery(name = "Endereco.findByEnderecoId", query = "SELECT e FROM Endereco e WHERE e.enderecoId = :enderecoId"),
    @NamedQuery(name = "Endereco.findByLogradouro", query = "SELECT e FROM Endereco e WHERE e.logradouro = :logradouro"),
    @NamedQuery(name = "Endereco.findByNumero", query = "SELECT e FROM Endereco e WHERE e.numero = :numero"),
    @NamedQuery(name = "Endereco.findByComplemento", query = "SELECT e FROM Endereco e WHERE e.complemento = :complemento"),
    @NamedQuery(name = "Endereco.findByBairro", query = "SELECT e FROM Endereco e WHERE e.bairro = :bairro"),
    @NamedQuery(name = "Endereco.findByCep", query = "SELECT e FROM Endereco e WHERE e.cep = :cep")})

public class Endereco implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "endereco_id", nullable = false)
    @SequenceGenerator(name="Endereco_Generator", sequenceName="endereco_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Endereco_Generator")    
    private Integer enderecoId;
    
    @Basic(optional = false)
    @Column(name = "logradouro", nullable = false, length = 255)
    private String logradouro;
    
    @Basic(optional = false)
    @Column(name = "numero", nullable = false)
    private int numero;
    
    @Column(name = "complemento", length = 255)
    private String complemento;
    
    @Basic(optional = false)
    @Column(name = "bairro", nullable = false, length = 255)
    private String bairro;
    
    @Basic(optional = false)
    @Column(name = "cep", nullable = false)
    private int cep;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "endereco", fetch = FetchType.LAZY)
    private Agencia agencia;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "endereco", fetch = FetchType.LAZY)
    private CaixaEletronico caixaEletronico;
    
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;
    
    @JoinColumn(name = "cidade_id", referencedColumnName = "cidade_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cidade cidade;

    public Endereco() {
    }

    public Integer getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Integer enderecoId) {
        this.enderecoId = enderecoId;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public CaixaEletronico getCaixaEletronico() {
        return caixaEletronico;
    }

    public void setCaixaEletronico(CaixaEletronico caixaEletronico) {
        this.caixaEletronico = caixaEletronico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enderecoId != null ? enderecoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Endereco)) {
            return false;
        }
        Endereco other = (Endereco) object;
        if ((this.enderecoId == null && other.enderecoId != null) || (this.enderecoId != null && !this.enderecoId.equals(other.enderecoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testedojpa2.negocio.Endereco[ enderecoId=" + enderecoId + " ]";
    }
    
}
