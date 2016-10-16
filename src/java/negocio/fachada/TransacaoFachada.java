/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.fachada;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import negocio.entidade.ClienteTemConta;
import negocio.entidade.Conta;
import negocio.entidade.Deposito;
import negocio.entidade.Retirada;
import negocio.entidade.Transacao;
import negocio.entidade.Transferencia;
import negocio.excecao.ContaNaoExistenteException;
import negocio.excecao.SaldoInsuficienteException;
import persistencia.ContaDAO;
import persistencia.TransacaoDAO;

/**
 *
 * @author Ivan Joao Foschini 
 */
@Stateless
public class TransacaoFachada {
    
    private static final String A_CONTA_DE_NUMERO = "A conta de n\u00famero ";
    private static final String NAO_EXISTE = " n\u00e3o existe";
    private static final String POSSUI_UM_SALDO_INSUFICIENTE_PARA_SE_REALIZAR_A_TRANSACAO_SOLICITADA = " possui um saldo insuficiente para se realizar a transa\u00e7\u00e3o solicitada";

    @EJB
    private ContaDAO contaDAO;
    @EJB
    private TransacaoDAO transacaoDAO;
    
    @EJB 
    private LogEventoPaginaFachada logevento;
    
    @EJB 
    private LogErroFachada logerro;
    
    
    public void depositar(Integer numeroDaConta, Float valor) throws ContaNaoExistenteException {
        try {
        
            Conta contaParaDeposito = contaDAO.recuperarPorNumero(numeroDaConta);

            if (contaParaDeposito == null) {
                throw new ContaNaoExistenteException(A_CONTA_DE_NUMERO + numeroDaConta + NAO_EXISTE);
            }

            contaParaDeposito.setSaldo(contaParaDeposito.getSaldo() + valor);

            Deposito deposito = new Deposito();                
            deposito.setValor(valor);
            deposito.setDataDeRealizacao(new java.util.Date());
            deposito.setHoraDeRealizacao(new java.util.Date());
            this.associarTransacaoAoTitularDaConta(contaParaDeposito, deposito);

            transacaoDAO.inserir(deposito);
            logevento.InsereNovoLog("Solicitacao","Transacao",numeroDaConta);
        
        }
        
        catch (ContaNaoExistenteException  ex) {
                 logerro.InsereNovoLog("Transacao", numeroDaConta ,ex.getMessage());
                  throw new ContaNaoExistenteException(A_CONTA_DE_NUMERO + numeroDaConta + NAO_EXISTE);
        }
    }
    
    public void retirar(Integer numeroDaConta, Float valor) throws ContaNaoExistenteException, SaldoInsuficienteException {
        try {
        
                Conta contaParaRetirada = contaDAO.recuperarPorNumero(numeroDaConta);

                if (contaParaRetirada == null) {
                    throw new ContaNaoExistenteException(A_CONTA_DE_NUMERO + numeroDaConta + NAO_EXISTE);
                }

                if (!this.verificarSeOSaldoEhSuficienteParaEfetuarATransacao(contaParaRetirada, valor)) {
                    throw new SaldoInsuficienteException(A_CONTA_DE_NUMERO + numeroDaConta + POSSUI_UM_SALDO_INSUFICIENTE_PARA_SE_REALIZAR_A_TRANSACAO_SOLICITADA);
                }

                contaParaRetirada.setSaldo(contaParaRetirada.getSaldo() - valor);

                Retirada retirada = new Retirada();
                retirada.setValor(valor);
                retirada.setDataDeRealizacao(new java.util.Date());
                retirada.setHoraDeRealizacao(new java.util.Date());
                this.associarTransacaoAoTitularDaConta(contaParaRetirada, retirada);

                transacaoDAO.inserir(retirada);
                logevento.InsereNovoLog("Solicitacao","Transacao",numeroDaConta);
        }
        
        catch (ContaNaoExistenteException  ex) {
                 logerro.InsereNovoLog("Transacao", numeroDaConta ,ex.getMessage());
                  throw new ContaNaoExistenteException(A_CONTA_DE_NUMERO + numeroDaConta + NAO_EXISTE);
        }
        
        catch (SaldoInsuficienteException  ex) {
                 logerro.InsereNovoLog("Transacao", numeroDaConta ,ex.getMessage());
                  throw new SaldoInsuficienteException(A_CONTA_DE_NUMERO + numeroDaConta + POSSUI_UM_SALDO_INSUFICIENTE_PARA_SE_REALIZAR_A_TRANSACAO_SOLICITADA);
        }
        
        
        
    }

    public void transferir(Integer numeroDaContaDeOrigem, Integer numeroDaContaDeDestino, Float valor) throws ContaNaoExistenteException, SaldoInsuficienteException {        
        
        try {
        
                Conta contaDeOrigemRecuperada = contaDAO.recuperarPorNumero(numeroDaContaDeOrigem);

                if (contaDeOrigemRecuperada == null) {
                    throw new ContaNaoExistenteException(A_CONTA_DE_NUMERO + numeroDaContaDeOrigem + NAO_EXISTE);
                }

                Conta contaDeDestinoRecuperada = contaDAO.recuperarPorNumero(numeroDaContaDeDestino);

                if (contaDeDestinoRecuperada == null) {
                    throw new ContaNaoExistenteException(A_CONTA_DE_NUMERO + numeroDaContaDeDestino + NAO_EXISTE);
                }

                if (!this.verificarSeOSaldoEhSuficienteParaEfetuarATransacao(contaDeOrigemRecuperada, valor)) {
                    throw new SaldoInsuficienteException(A_CONTA_DE_NUMERO + numeroDaContaDeOrigem + POSSUI_UM_SALDO_INSUFICIENTE_PARA_SE_REALIZAR_A_TRANSACAO_SOLICITADA);
                }

                contaDeOrigemRecuperada.setSaldo(contaDeOrigemRecuperada.getSaldo() - valor);
                contaDeDestinoRecuperada.setSaldo(contaDeDestinoRecuperada.getSaldo() + valor);

                Transferencia transferenciaReferenteAContaDeOrigem = new Transferencia();
                transferenciaReferenteAContaDeOrigem.setValor(valor);
                transferenciaReferenteAContaDeOrigem.setDataDeRealizacao(new java.util.Date());
                transferenciaReferenteAContaDeOrigem.setHoraDeRealizacao(new java.util.Date());
                this.associarTransacaoAoTitularDaConta(contaDeOrigemRecuperada, transferenciaReferenteAContaDeOrigem);

                Transferencia transferenciaReferenteAContaDeDestino = new Transferencia();                
                transferenciaReferenteAContaDeDestino.setValor(transferenciaReferenteAContaDeOrigem.getValor());
                transferenciaReferenteAContaDeDestino.setDataDeRealizacao(transferenciaReferenteAContaDeOrigem.getDataDeRealizacao());
                transferenciaReferenteAContaDeDestino.setHoraDeRealizacao(transferenciaReferenteAContaDeOrigem.getHoraDeRealizacao());
                this.associarTransacaoAoTitularDaConta(contaDeDestinoRecuperada, transferenciaReferenteAContaDeDestino);

                transacaoDAO.inserir(transferenciaReferenteAContaDeOrigem);        
                transacaoDAO.inserir(transferenciaReferenteAContaDeDestino);
                
                logevento.InsereNovoLog("Solicitacao","Transacao",numeroDaContaDeOrigem);
                logevento.InsereNovoLog("Solicitacao","Transacao",numeroDaContaDeDestino);
                
        }
        
         catch (ContaNaoExistenteException  ex) {
                 logerro.InsereNovoLog("Transacao", numeroDaContaDeOrigem ,ex.getMessage());
                  throw new ContaNaoExistenteException(A_CONTA_DE_NUMERO + numeroDaContaDeOrigem + NAO_EXISTE);
        }
        
        catch (SaldoInsuficienteException  ex) {
                 logerro.InsereNovoLog("Transacao", numeroDaContaDeOrigem ,ex.getMessage());
                  throw new SaldoInsuficienteException(A_CONTA_DE_NUMERO + numeroDaContaDeOrigem + POSSUI_UM_SALDO_INSUFICIENTE_PARA_SE_REALIZAR_A_TRANSACAO_SOLICITADA);
        }
                
    }
    
    private void associarTransacaoAoTitularDaConta(Conta conta, Transacao transacao) {
        for (ClienteTemConta clienteTemConta: conta.getClienteTemContas()) {
            if ("S".equals(clienteTemConta.getTitularidade())) {
                transacao.setClienteTemConta(clienteTemConta);
            }
        }
    }    
    
    private boolean verificarSeOSaldoEhSuficienteParaEfetuarATransacao(Conta conta, Float valor) {
        if (conta.getSaldo() - valor < 0.0) {
            return false;
        }

        return true;
    }
}