/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marco
 */


@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogAtividade.findByNomeAtividade", query = "SELECT a FROM LogAtividade a WHERE a.NomeAtividade= :NomeAtividade")
     
})


@Entity
@Table(name = "logatividade", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idatividade"})})

public class LogAtividade implements Serializable  {
    @Basic(optional = false)
    @NotNull
    @Column(name = "status", nullable = false)
    private int status;
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "idatividade", nullable = false)
    //@SequenceGenerator(name="Banco_Generator", sequenceName="banco_sequence", allocationSize=1)
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Banco_Generator")
    private Integer IdAtividade;
    
    @Basic(optional = false)
    @Column(name = "nomeatividade", nullable = false, length=255)
    private String NomeAtividade;
    
    
 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "LogAtividade", fetch = FetchType.LAZY)
    private Collection<LogEventoPagina> LogEventoPaginas;

    
    

    
    
    
    
    public LogAtividade(){
    
    }
    
    public Integer getIdAtividade() {
        return IdAtividade;
    }

    public void setIdAtividade(Integer idatividade) {
        this.IdAtividade = idatividade;
    }

    public String getNomeAtividade() {
        return NomeAtividade;
    }

    public void setNomeatividade(String nomeatividade) {
        this.NomeAtividade = nomeatividade;
    }


    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (IdAtividade != null ? IdAtividade.hashCode() : 0);
        return hash;
    }
    
    @XmlTransient
    public Collection<LogEventoPagina> getLogEventoPaginas() {
        return LogEventoPaginas;
    }

    public void setLogEventoPaginas(Collection<LogEventoPagina> LogEventoPaginas) {
        this.LogEventoPaginas = LogEventoPaginas;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
