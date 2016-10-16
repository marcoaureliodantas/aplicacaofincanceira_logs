/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RdOC
 */
@Entity
@Table(name = "conta_poupanca")
@DiscriminatorValue(value = "P")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContaPoupanca.findAll", query = "SELECT c FROM ContaPoupanca c"),
    @NamedQuery(name = "ContaPoupanca.findByDataDeAniversario", query = "SELECT c FROM ContaPoupanca c WHERE c.dataDeAniversario = :dataDeAniversario"),
    @NamedQuery(name = "ContaPoupanca.findByCorrecaoMonetaria", query = "SELECT c FROM ContaPoupanca c WHERE c.correcaoMonetaria = :correcaoMonetaria"),
    @NamedQuery(name = "ContaPoupanca.findByJuros", query = "SELECT c FROM ContaPoupanca c WHERE c.juros = :juros")})

public class ContaPoupanca extends Conta implements Serializable {
    
    private static final long serialVersionUID = 1L;
        
    @Basic(optional = false)
    @Column(name = "data_de_aniversario", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataDeAniversario;
    
    @Basic(optional = false)
    @Column(name = "correcao_monetaria", nullable = false)
    private int correcaoMonetaria;
    
    @Basic(optional = false)
    @Column(name = "juros", nullable = false)
    private int juros;

    public ContaPoupanca() {
    }


    public Date getDataDeAniversario() {
        return dataDeAniversario;
    }

    public void setDataDeAniversario(Date dataDeAniversario) {
        this.dataDeAniversario = dataDeAniversario;
    }

    public int getCorrecaoMonetaria() {
        return correcaoMonetaria;
    }

    public void setCorrecaoMonetaria(int correcaoMonetaria) {
        this.correcaoMonetaria = correcaoMonetaria;
    }

    public int getJuros() {
        return juros;
    }

    public void setJuros(int juros) {
        this.juros = juros;
    }    
}
