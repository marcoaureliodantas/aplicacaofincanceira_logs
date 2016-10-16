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
import negocio.fachada.TransacaoFachada;

/**
 *
 * @author Ivan Joao Foschini 
 */
@SessionScoped
@ManagedBean(name = "transacaoManagedBean")
public class TransacaoManagedBean implements Serializable {

    private static final Integer QUANTIDADE_DE_TIPOS_DE_TRANSACAO = 3;

    private static final String DEPOSITO = "Deposito";
    private static final String DEPOSITO_EFETUADO_COM_SUCESSO = "Dep\u00f3sito efetuado com sucesso";
    private static final String RETIRADA = "Retirada";
    private static final String RETIRADA_EFETUADA_COM_SUCESSO = "Retirada efetuada com sucesso";
    private static final String TRANSFERENCIA = "Transferencia";
    private static final String TRANSFERENCIA_EFETUADA_COM_SUCESSO = "Transfer\u00eancia efetuada com sucesso";

    private String tipoDeTransacao;
    private Boolean exibirCamposContaDeOrigemEContaDeDestino;
    private Integer numeroDaContaDeOrigem;
    private Integer numeroDaContaDeDestino;
    private Integer numeroDaConta;
    private Float valor;

    @EJB
    private TransacaoFachada transacaoFachada;

    public TransacaoManagedBean() {}

    public String getTipoDeTransacao() {
        return tipoDeTransacao;
    }

    public void setTipoDeTransacao(String tipoDeTransacao) {
        this.tipoDeTransacao = tipoDeTransacao;
    }

    public Boolean getExibirCamposContaDeOrigemEContaDeDestino() {
        return exibirCamposContaDeOrigemEContaDeDestino;
    }

    public void setExibirCamposContaDeOrigemEContaDeDestino(Boolean exibirCamposContaDeOrigemEContaDeDestino) {
        this.exibirCamposContaDeOrigemEContaDeDestino = exibirCamposContaDeOrigemEContaDeDestino;
    }

    public Integer getNumeroDaContaDeOrigem() {
        return numeroDaContaDeOrigem;
    }

    public void setNumeroDaContaDeOrigem(Integer numeroDaContaDeOrigem) {
        this.numeroDaContaDeOrigem = numeroDaContaDeOrigem;
    }

    public Integer getNumeroDaContaDeDestino() {
        return numeroDaContaDeDestino;
    }

    public void setNumeroDaContaDeDestino(Integer numeroDaContaDeDestino) {
        this.numeroDaContaDeDestino = numeroDaContaDeDestino;
    }

    public Integer getNumeroDaConta() {
        return numeroDaConta;
    }

    public void setNumeroDaConta(Integer numeroDaConta) {
        this.numeroDaConta = numeroDaConta;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public List<SelectItem> getTiposDeTransacao() {
        List<SelectItem> tiposDeTransacao = new ArrayList<>(QUANTIDADE_DE_TIPOS_DE_TRANSACAO);
        tiposDeTransacao.add(new SelectItem(DEPOSITO, DEPOSITO));
        tiposDeTransacao.add(new SelectItem(RETIRADA, RETIRADA));
        tiposDeTransacao.add(new SelectItem(TRANSFERENCIA, TRANSFERENCIA));

        return tiposDeTransacao;
    }

    public void alterarTipoDeTransacao() {
        if (DEPOSITO.equals(this.getTipoDeTransacao()) || RETIRADA.equals(this.getTipoDeTransacao())) {
            this.exibirCamposContaDeOrigemEContaDeDestino = Boolean.FALSE;
        } else if (TRANSFERENCIA.equals(this.getTipoDeTransacao())) {
            this.exibirCamposContaDeOrigemEContaDeDestino = Boolean.TRUE;
        }
    }

    public String efetuarTransacao() {
        try {
            if (DEPOSITO.equals(this.getTipoDeTransacao())) {
                transacaoFachada.depositar(this.getNumeroDaConta(), this.getValor());
                MensagemUtility.adicionarMensagemDeSucesso("formTransacao", DEPOSITO_EFETUADO_COM_SUCESSO);
            } else if (RETIRADA.equals(this.getTipoDeTransacao())) {
                transacaoFachada.retirar(this.getNumeroDaConta(), this.getValor());
                MensagemUtility.adicionarMensagemDeSucesso("formTransacao", RETIRADA_EFETUADA_COM_SUCESSO);
            } else if (TRANSFERENCIA.equals(this.getTipoDeTransacao())) {
                transacaoFachada.transferir(this.getNumeroDaContaDeOrigem(), this.getNumeroDaContaDeDestino(), this.getValor());
                MensagemUtility.adicionarMensagemDeSucesso("formTransacao", TRANSFERENCIA_EFETUADA_COM_SUCESSO);
            }
        } catch (Exception e) {
            MensagemUtility.adicionarMensagemDeErro("formTransacao", e.getMessage());
        }

        this.inicializarInterface();
        return "/transacao/EfetuarTransacao";
    }

    private void inicializarInterface() {
        this.tipoDeTransacao = DEPOSITO;
        this.exibirCamposContaDeOrigemEContaDeDestino = Boolean.FALSE;
        this.numeroDaContaDeOrigem = 0;
        this.numeroDaContaDeDestino = 0;
        this.numeroDaConta = 0;
        this.valor = new Float(0.0);
    }

    public String montarPaginaParaEfetuarTransacao() {
        this.inicializarInterface();
        return "/transacao/EfetuarTransacao";
    }
}