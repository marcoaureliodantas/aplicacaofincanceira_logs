/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package apresentacao.managedbean;

import apresentacao.utility.MensagemUtility;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import negocio.entidade.Agencia;
import negocio.entidade.Banco;
import negocio.entidade.Cidade;
import negocio.entidade.Endereco;
import negocio.entidade.Estado;
import negocio.fachada.AgenciaFachada;

/**
 *
 * @author Ivan Joao Foschini 
 */
@SessionScoped
@ManagedBean(name = "agenciaManagedBean")
public class AgenciaManagedBean implements Serializable {

    private Agencia agencia;
    private List<Agencia> agencias = new ArrayList<>();
    private Integer idDoEstadoSelecionado;
    private Integer idDaCidadeSelecionada;
    private Integer idDoBancoSelecionado;

    @EJB
    private AgenciaFachada agenciaFachada;

    @EJB
    private AgenciaFachada agenciaFachadaOld;
    
    public AgenciaManagedBean() {}

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public List<Agencia> getAgencias() {
        return agencias;
    }

    public Integer getIdDoEstadoSelecionado() {
        return idDoEstadoSelecionado;
    }

    public void setIdDoEstadoSelecionado(Integer idDoEstadoSelecionado) {
        this.idDoEstadoSelecionado = idDoEstadoSelecionado;
    }

    public Integer getIdDaCidadeSelecionada() {
        return idDaCidadeSelecionada;
    }

    public void setIdDaCidadeSelecionada(Integer idDaCidadeSelecionada) {
        this.idDaCidadeSelecionada = idDaCidadeSelecionada;
    }

    public Integer getIdDoBancoSelecionado() {
        return idDoBancoSelecionado;
    }

    public void setIdDoBancoSelecionado(Integer idDoBancoSelecionado) {
        this.idDoBancoSelecionado = idDoBancoSelecionado;
    }

    public List<SelectItem> getBancos() {
        List<Banco> bancosCadastrados = agenciaFachada.recuperarBancos();

        List<SelectItem> bancos = new ArrayList<>(bancosCadastrados.size());

        for (Banco banco: bancosCadastrados) {
            bancos.add(new SelectItem(banco.getBancoId(), banco.getNome()));
        }

        return bancos;
    }

    public List<SelectItem> getCidadesPorEstado() {
        List<Cidade> cidadesCadastradas = agenciaFachada.recuperarCidadesPorEstado(this.getIdDoEstadoSelecionado());

        List<SelectItem> cidades = new ArrayList<>(cidadesCadastradas.size());

        for (Cidade cidade: cidadesCadastradas) {
            cidades.add(new SelectItem(cidade.getCidadeId(), cidade.getNome()));
        }

        return cidades;
    }

    public List<SelectItem> getEstados() {
        List<Estado> estadosCadastrados = agenciaFachada.recuperarEstados();

        List<SelectItem> estados = new ArrayList<>(estadosCadastrados.size());

        for (Estado estado: estadosCadastrados) {
            estados.add(new SelectItem(estado.getEstadoId(), estado.getNome()));
        }

        return estados;
    }

    public String alterar() {     
         try {
             
            Agencia agencia_old  = agenciaFachadaOld.ConsultaPK(agencia.getAgenciaId());
            agenciaFachada.alterar(agencia_old,this.getAgencia(), this.getIdDaCidadeSelecionada(), this.getIdDoBancoSelecionado());
            this.recuperarAgencias();
            return "/agencia/ListarAgencias";
        } catch (Exception e) {
            MensagemUtility.adicionarMensagemDeErro("formAgencia", e.getMessage());
            return "/agencia/AlterarAgencia";
        }
    }

    public String excluir() {
        agenciaFachada.excluir(this.getAgencia());
        this.recuperarAgencias();
        return "/agencia/ListarAgencias";
    }

    public String listar() {
        this.recuperarAgencias();
        return "/agencia/ListarAgencias";
    }

    public String inserir() {
        try {
            agenciaFachada.inserir(this.getAgencia(), this.getIdDaCidadeSelecionada(), this.getIdDoBancoSelecionado());
            this.recuperarAgencias();
            return "/agencia/ListarAgencias";
        } catch (Exception e) {
            MensagemUtility.adicionarMensagemDeErro("formAgencia", e.getMessage());
            return "/agencia/InserirAgencia";
        }
    }

    public String montarPaginaParaAlteracao() {
        this.setIdDoEstadoSelecionado(this.getAgencia().getEndereco().getCidade().getEstado().getEstadoId());
        this.setIdDaCidadeSelecionada(this.getAgencia().getEndereco().getCidade().getCidadeId());
        this.setIdDoBancoSelecionado(this.getAgencia().getBanco().getBancoId());
        return "/agencia/AlterarAgencia";
    }

    public String montarPaginaParaExclusao() {
        return "/agencia/ExcluirAgencia";
    }

    public String montarPaginaParaInsercao() {
        this.agencia = new Agencia();
        this.agencia.setEndereco(new Endereco());
        this.setIdDoEstadoSelecionado(1); /** Corresponde ao identificador do estado de SP, selecionado como default. */
        this.setIdDaCidadeSelecionada(1); /** Corresponde ao identificador da cidade de Sao Carlos, selecionada como default. */
        return "/agencia/InserirAgencia";
    }

    private void recuperarAgencias() {
        this.agencias = agenciaFachada.listar();
    }

    public void recuperarCidadesPorEstado() {
        this.getCidadesPorEstado();
    }
}