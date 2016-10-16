/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.excecao;

/**
 *
 * @author Ivan Joao Foschini 
 */
public class SaldoInsuficienteException extends Exception {

    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
}