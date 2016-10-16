/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.entidade;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marco
 */


@Entity
@Table(name = "logeventopagina", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idevento"})})


@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogEventoPagina.findAll", query = "SELECT a FROM LogEventoPagina a"),
    @NamedQuery(name = "LogEventoPagina.findByIdEvento", query = "SELECT a FROM LogEventoPagina a WHERE a.IdEvento= :idevento"),
    
    @NamedQuery(name = "LogEventoPagina.findByIdObjeto", query = "SELECT b FROM LogEventoPagina b WHERE b.IdObjeto = :idobjeto"),

})
    
public class LogEventoPagina implements Serializable  {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "idevento", nullable = false)
    @SequenceGenerator(name="Evento_Generator", sequenceName="id_evento_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Evento_Generator")
    private Long IdEvento;
    
    @JoinColumn(name = "idatividade", referencedColumnName = "idatividade", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LogAtividade LogAtividade;
    
    @JoinColumn(name = "idpagina", referencedColumnName = "idpagina", nullable = true)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LogPagina LogPagina;
    
      
    @Basic(optional = false)
    @Column(name = "idobjeto", nullable = true)
    private Integer IdObjeto;
    
    @Basic(optional = false)
    @Column(name = "nomeusuario", nullable = false, length = 255)
    private String NomeUsuario;
    
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datainsercao", nullable = false)
    private Date DataInsercao;
    
     @Basic(optional = false)
    @Temporal(TemporalType.TIME)
    @Column(name = "horainsercao", nullable = false)
    private Date HoraInsercao;
    
    
    
    
    public LogEventoPagina() {
    }

    public Long getIdevento() {
        return IdEvento;
    }

    public void setIdevento(Long idevento) {
        this.IdEvento = idevento;
    }

    

    public Integer getIdObjeto() {
        return IdObjeto;
    }

    public void setIdObjeto(Integer IdObjeto) {
        this.IdObjeto = IdObjeto;
    }

    public String getNomeUsuario() {
        return NomeUsuario;
    }

    public void setNomeUsuario(String NomeUsuario) {
        this.NomeUsuario = NomeUsuario;
    }

    public Date getDataInsercao() {
        return DataInsercao;
    }

    public void setDataInsercao(Date DataInsercao) {
        this.DataInsercao = DataInsercao;
    }
    
   
    public Date getHoraInsercao() {
        return HoraInsercao;
    }
     
     public void setHoraInsercao(Date HoraInsercao) {
        this.HoraInsercao = HoraInsercao;
    }
    
    public LogAtividade getLogAtividade() {
        return LogAtividade;
    }
     
    public void setLogAtividade(LogAtividade logatividade) {
        this.LogAtividade = logatividade;
    }

    public LogPagina getLogPagina() {
        return LogPagina;
    }

    public void setLogPagina(LogPagina logpagina) {
        this.LogPagina = logpagina;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (IdEvento != null ? IdEvento.hashCode() : 0);
        return hash;
    }
    
    


    
}
