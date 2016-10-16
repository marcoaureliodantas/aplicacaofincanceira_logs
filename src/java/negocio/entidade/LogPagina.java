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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    @NamedQuery(name = "LogPagina.findByNomePagina", query = "SELECT a FROM LogPagina a WHERE a.NomePagina= :NomePagina")
     
})

@Entity
@Table(name = "logpagina", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idpagina"})})
public class LogPagina implements Serializable  {
    @Basic(optional = false)
    @NotNull
    @Column(name = "status", nullable = false)
    private int status;
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "idpagina", nullable = false)
    //@SequenceGenerator(name="Banco_Generator", sequenceName="banco_sequence", allocationSize=1)
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Banco_Generator")
    private Integer IdPagina;
    
    @Basic(optional = false)
    @Column(name = "nomepagina", nullable = false, length=255)
    private String NomePagina;
    
    
   
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "LogPagina", fetch = FetchType.LAZY)
    private Collection<LogEventoPagina> LogEventoPaginas;
    
    
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "LogPagina", fetch = FetchType.LAZY)
    private Collection<LogPaginaHistoricoDados> LogHistoricos;
     
      @OneToMany(cascade = CascadeType.ALL, mappedBy = "LogPagina", fetch = FetchType.LAZY)
    private Collection<LogErro> LogErros;

      
    @XmlTransient  
    public Collection<LogPaginaHistoricoDados> getLogHistoricos() {
        return LogHistoricos;
    }

    public void setLogHistoricos(Collection<LogPaginaHistoricoDados> LogHistoricos) {
        this.LogHistoricos = LogHistoricos;
    }

    
    @XmlTransient
    public Collection<LogErro> getLogErros() {
        return LogErros;
    }

    public void setLogErros(Collection<LogErro> LogErros) {
        this.LogErros = LogErros;
    }
    
    
    @XmlTransient
    public Collection<LogEventoPagina> getLogEventoPaginas() {
        return LogEventoPaginas;
    }

    public void setLogEventoPaginas(Collection<LogEventoPagina> LogEventoPaginas) {
        this.LogEventoPaginas = LogEventoPaginas;
    }
      
      
    public LogPagina(){
    
    }

    public Integer getIdPagina() {
        return IdPagina;
    }

    public void setIdPagina(Integer idpagina) {
        this.IdPagina = idpagina;
    }

    public String getNomePagina() {
        return NomePagina;
    }

    public void setNomePagina(String nomepagina) {
        this.NomePagina = nomepagina;
    }


    
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (IdPagina != null ? IdPagina.hashCode() : 0);
        return hash;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
