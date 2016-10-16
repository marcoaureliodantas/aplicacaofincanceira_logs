/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.excecao;

/**
 *
 * @author Ivan Joao Foschini 
 */
public class ExclusaoDeBancoException extends Exception {
    
    public ExclusaoDeBancoException(String mensagem) {
        super(mensagem);
    }
}