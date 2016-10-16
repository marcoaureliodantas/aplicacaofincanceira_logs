/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RdOC
 */
@Entity
@Table(name = "conta_corrente")
@DiscriminatorValue(value = "C")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContaCorrente.findAll", query = "SELECT c FROM ContaCorrente c"),
    @NamedQuery(name = "ContaCorrente.findByLimite", query = "SELECT c FROM ContaCorrente c WHERE c.limite = :limite")})

public class ContaCorrente extends Conta implements Serializable {
 
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "limite", nullable = false)
    private int limite;

    public ContaCorrente() {
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }
    
}
