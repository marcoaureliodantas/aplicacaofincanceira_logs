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
@DiscriminatorValue(value="J")
@NamedQueries({@NamedQuery(name = "Cliente.findByCnpj", query = "SELECT c FROM ClienteJuridico c WHERE c.cnpj = :cnpj")})
public class ClienteJuridico extends Cliente implements Serializable {
    
    @Column(name = "cnpj", length = 14)
    private String cnpj;


    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
