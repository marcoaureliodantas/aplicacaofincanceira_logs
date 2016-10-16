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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlElementRef.DEFAULT;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marco
 */

@Entity
@Table(name = "logerro", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"iderro"})})

@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogErro.findAll", query = "SELECT a FROM LogErro a"),
    @NamedQuery(name = "LogErro.findByIdErro", query = "SELECT b FROM LogErro b WHERE b.IdErro = :iderro"),
    @NamedQuery(name = "LogErro.findByIdObjeto", query = "SELECT b FROM LogErro b WHERE b.IdObjeto = :idobjeto"),
    //@NamedQuery(name = "LogErro.findByIdPaginaObjeto", query = "SELECT b FROM LogErro b WHERE b.IdObjeto = :idobjeto AND b.IdPagina= :idpagina")
    })

public class LogErro implements Serializable  {
    
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @Basic(optional = false)
    @Column(name = "iderro", nullable = false)
    @SequenceGenerator(name="Erro_Generator", sequenceName="id_erro_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Erro_Generator")
    public Long IdErro;
    
    @JoinColumn(name = "idpagina", referencedColumnName = "idpagina", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public LogPagina LogPagina;
    
       
    @Basic(optional = false)
    @Column(name = "idobjeto", nullable = true)
    public Integer IdObjeto;
    
    @Basic(optional = false)
    @Column(name = "nomeusuario", nullable = false, length = 255)
    public String NomeUsuario;
    
    @Basic(optional = false)
    @Column(name = "msgerro", nullable = false, length = 4000)
    public String MsgErro;
    
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datainclusao", nullable = false)
    public Date DataInclusao;
    
    @Basic(optional = false)
    @Temporal(TemporalType.TIME)
    @Column(name = "horainclusao", nullable = false)
    public Date HoraInclusao;
    
    
   

    
    
    
    public LogErro() {
        
    }
    
    
    public Long getIdErro() {
        return IdErro;
    }

    public void setIdErro(Long IdErro) {
        this.IdErro = IdErro;
    }

    public LogPagina getLogPagina() {
        return LogPagina;
    }

    public void setLogpagina(LogPagina logpagina) {
        this.LogPagina = logpagina;
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

    public String getMsgErro() {
        return MsgErro;
    }

    public void setMsgErro(String MsgErro) {
        this.MsgErro = MsgErro;
    }

    public Date getDataInclusao() {
        return DataInclusao;
    }

    public void setDataInclusao(Date DataInclusao) {
        this.DataInclusao = DataInclusao;
    }

    public Date getHoraInclusao() {
        return HoraInclusao;
    }

    public void setHoraInclusao(Date HoraInclusao) {
        this.HoraInclusao = HoraInclusao;
    }
   
    
      
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (IdErro != null ? IdErro.hashCode() : 0);
        return hash;
    }

    
    
}
