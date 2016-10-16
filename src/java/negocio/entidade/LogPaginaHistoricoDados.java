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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marco
 */


@Entity
@Table(name = "logpaginahistoricodados", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idhistorico"})})

@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogPaginaHistoricoDados.findAll", query = "SELECT a FROM LogPaginaHistoricoDados a"),
    //@NamedQuery(name = "LogPaginaHistoricoDados.findByIdPagina", query = "SELECT b FROM LogPaginaHistoricoDados b WHERE b.IdPagina = :idpagina"),
    @NamedQuery(name = "LogPaginaHistoricoDados.findByIdObjeto", query = "SELECT b FROM LogPaginaHistoricoDados b WHERE b.IdObjeto = :idobjeto"),
    //@NamedQuery(name = "LogPaginaHistoricoDados.findByIdPaginaObjeto", query = "SELECT b FROM LogPaginaHistoricoDados b WHERE b.IdObjeto = :idobjeto AND b.IdPagina= :idpagina")
        
})

public class LogPaginaHistoricoDados implements Serializable  {
       
    private static final long serialVersionUID = 1L;
        
    @Id
    @Basic(optional = false)
    @Column(name = "idhistorico", nullable = false)
    @SequenceGenerator(name="Historico_Generator", sequenceName="id_historico_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Historico_Generator")
    public Long IdHistorico;
    
    
    @JoinColumn(name = "idpagina", referencedColumnName = "idpagina", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public LogPagina LogPagina;
    
    @Basic(optional = false)
    @Column(name = "idobjeto", nullable = true)   
    public Integer  IdObjeto;
    
    
    @Basic(optional = false)
    @Column(name = "nomeusuario", nullable = false, length = 255)
    public String NomeUsuario;
    
    
    @Basic(optional = false)
    @Column(name = "campoalterado", nullable = false, length = 255)
    public String CampoAlterado;   
    
    @Basic(optional = false)
    @Column(name = "conteudoanterior", nullable = false, length = 255)
    public String ConteudoAnterior;
    
    @Basic(optional = false)
    @Column(name = "conteudoatual", nullable = false, length = 255)
    public String ConteudoAtual;
    
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dataalteracao", nullable = false)
    public Date DataAlteracao;
    
    @Basic(optional = false)
    @Temporal(TemporalType.TIME)
    @Column(name = "horaalteracao", nullable = false)
    public Date HoraAlteracao;
    
    
    
    public Long getIdHistorico() {
        return IdHistorico;
    }

    public void setIdHistorico(Long IdHistorico) {
        this.IdHistorico = IdHistorico;
    }

    public LogPagina getLogPagina() {
        return LogPagina;
    }

    public void setIdPagina(LogPagina logpagina) {
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

    public String getCampoAlterado() {
        return CampoAlterado;
    }

    public void setCampoAlterado(String CampoAlterado) {
        this.CampoAlterado = CampoAlterado;
    }

    public String getConteudoAnterior() {
        return ConteudoAnterior;
    }

    public void setConteudoAnterior(String ConteudoAnterior) {
        this.ConteudoAnterior = ConteudoAnterior;
    }

    public String getConteudoAtual() {
        return ConteudoAtual;
    }

    public void setConteudoAtual(String ConteudoAtual) {
        this.ConteudoAtual = ConteudoAtual;
    }

    public Date getDataAlteracao() {
        return DataAlteracao;
    }

    public void setDataAlteracao(Date DataAlteracao) {
        this.DataAlteracao = DataAlteracao;
    }
    
     public Date getHoraAlteracao() {
        return HoraAlteracao;
    }

    public void setHoraAlteracao(Date HoraAlteracao) {
        this.HoraAlteracao = HoraAlteracao;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (IdHistorico != null ? IdHistorico.hashCode() : 0);
        return hash;
    }

    public LogPaginaHistoricoDados() {
    }

   
    
    
    
}
