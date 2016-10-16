/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RdOC
 */
@Entity
@Table(name = "transacao")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo", length = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transacao.findAll", query = "SELECT t FROM Transacao t"),
    @NamedQuery(name = "Transacao.findByTransacaoId", query = "SELECT t FROM Transacao t WHERE t.transacaoId = :transacaoId"),
    @NamedQuery(name = "Transacao.findByDataDeRealizacao", query = "SELECT t FROM Transacao t WHERE t.dataDeRealizacao = :dataDeRealizacao"),
    @NamedQuery(name = "Transacao.findByHoraDeRealizacao", query = "SELECT t FROM Transacao t WHERE t.horaDeRealizacao = :horaDeRealizacao"),
    @NamedQuery(name = "Transacao.findByValor", query = "SELECT t FROM Transacao t WHERE t.valor = :valor")})

public class Transacao implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "transacao_id", nullable = false)
    @SequenceGenerator(name="Transacao_Generator", sequenceName="transacao_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Transacao_Generator")
    private Integer transacaoId;
    
    @Basic(optional = false)
    @Column(name = "data_de_realizacao", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataDeRealizacao;
    
    @Basic(optional = false)
    @Column(name = "hora_de_realizacao", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horaDeRealizacao;
    
    @Basic(optional = false)
    @Column(name = "valor", nullable = false)
    private float valor;
        
    @JoinColumns({
        @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id", nullable = false),
        @JoinColumn(name = "conta_id", referencedColumnName = "conta_id", nullable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ClienteTemConta clienteTemConta;
    
    @JoinColumn(name = "caixa_eletronico_id", referencedColumnName = "caixa_eletronico_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CaixaEletronico caixaEletronico;

    public Transacao() {
    }

    public Integer getTransacaoId() {
        return transacaoId;
    }

    public void setTransacaoId(Integer transacaoId) {
        this.transacaoId = transacaoId;
    }

    public Date getDataDeRealizacao() {
        return dataDeRealizacao;
    }

    public void setDataDeRealizacao(Date dataDeRealizacao) {
        this.dataDeRealizacao = dataDeRealizacao;
    }

    public Date getHoraDeRealizacao() {
        return horaDeRealizacao;
    }

    public void setHoraDeRealizacao(Date horaDeRealizacao) {
        this.horaDeRealizacao = horaDeRealizacao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public ClienteTemConta getClienteTemConta() {
        return clienteTemConta;
    }

    public void setClienteTemConta(ClienteTemConta clienteTemConta) {
        this.clienteTemConta = clienteTemConta;
    }

    public CaixaEletronico getCaixaEletronico() {
        return caixaEletronico;
    }

    public void setCaixaEletronico(CaixaEletronico caixaEletronico) {
        this.caixaEletronico = caixaEletronico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transacaoId != null ? transacaoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transacao)) {
            return false;
        }
        Transacao other = (Transacao) object;
        if ((this.transacaoId == null && other.transacaoId != null) || (this.transacaoId != null && !this.transacaoId.equals(other.transacaoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testedojpa2.negocio.Transacao[ transacaoId=" + transacaoId + " ]";
    }
    
}