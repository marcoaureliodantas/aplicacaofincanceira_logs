/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Reinaldo de Oliveira Castro
 */
@Entity
@DiscriminatorValue(value="F")
@NamedQueries({
    @NamedQuery(name = "Cliente.findByRg",  query = "SELECT c FROM ClienteFisico c WHERE c.rg = :rg"), 
    @NamedQuery(name = "Cliente.findByCpf", query = "SELECT c FROM ClienteFisico c WHERE c.cpf = :cpf")})
public class ClienteFisico extends Cliente implements Serializable {
    
    @Column(name = "rg", length = 11)
    private String rg;
    
    @Column(name = "cpf", length = 11)
    private String cpf;

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
