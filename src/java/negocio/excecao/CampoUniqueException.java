/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.excecao;

/**
 *
 * @author Ivan Joao Foschini 
 */
public class CampoUniqueException extends Exception {
    
    public CampoUniqueException(String mensagem) {
        super(mensagem);
    }
} 