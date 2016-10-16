/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Ivan Joao Foschini 
 */
@Embeddable
public class GrupoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "nomedeusuario")
    private String nomeDeUsuario;
    @Basic(optional = false)
    @Column(name = "nomedogrupo")
    private String nomeDoGrupo;

    public GrupoPK() {
    }

    public GrupoPK(String nomeDeUsuario, String nomeDoGrupo) {
        this.nomeDeUsuario = nomeDeUsuario;
        this.nomeDoGrupo = nomeDoGrupo;
    }

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }

    public String getNomeDoGrupo() {
        return nomeDoGrupo;
    }

    public void setNomeDoGrupo(String nomeDoGrupo) {
        this.nomeDoGrupo = nomeDoGrupo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GrupoPK other = (GrupoPK) obj;
        if ((this.nomeDeUsuario == null) ? (other.nomeDeUsuario != null) : !this.nomeDeUsuario.equals(other.nomeDeUsuario)) {
            return false;
        }
        if ((this.nomeDoGrupo == null) ? (other.nomeDoGrupo != null) : !this.nomeDoGrupo.equals(other.nomeDoGrupo)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.nomeDeUsuario != null ? this.nomeDeUsuario.hashCode() : 0);
        hash = 79 * hash + (this.nomeDoGrupo != null ? this.nomeDoGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "negocio.entidade.GrupoPK[nomedeusuario=" + nomeDeUsuario + ", nomedogrupo=" + nomeDoGrupo + "]";
    }
}