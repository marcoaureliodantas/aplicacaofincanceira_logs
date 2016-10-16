/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.entidade;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author RdOC
 */
@Entity
@DiscriminatorValue(value="R")
public class Retirada extends Transacao implements Serializable {

}
