/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package apresentacao.validacao;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Ivan Joao Foschini
 */
@FacesValidator("validateCnpj")
public class CnpjValidator implements Validator {

    private static final String O_CNPJ_FORNECIDO_E_INVALIDO = "O CNPJ fornecido \u00e9 inv\u00e1lido";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!verificarCnpj(value.toString())) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, O_CNPJ_FORNECIDO_E_INVALIDO, O_CNPJ_FORNECIDO_E_INVALIDO));
        }
    }

    private boolean verificarCnpj(String cnpj) {
       
        //para funcionar sem validar //marco
       return true;
       
//    if (cnpj.length() != 14) {
//            return false;
//        }
//
//        int soma = 0;
//        int dig = 0;
//
//        String digitosIniciais = cnpj.substring(0, 12);
//        char[] cnpjCharArray = cnpj.toCharArray();
//
//        /* Primeira parte da validaÃƒÆ’Ã‚Â§ÃƒÆ’Ã‚Â£o */
//        for (int i = 0; i < 4; i++) {
//            if (cnpjCharArray[i] - 48 >= 0 && cnpjCharArray[i] - 48 <= 9) {
//                soma += (cnpjCharArray[i] - 48) * (6 - (i + 1));
//            }
//        }
//
//        for (int i = 0; i < 8; i++) {
//            if (cnpjCharArray[i + 4] - 48 >= 0 && cnpjCharArray[i + 4] - 48 <= 9) {
//                soma += (cnpjCharArray[i + 4] - 48) * (10 - (i + 1));
//            }
//        }
//
//        dig = 11 - (soma % 11);
//
//        digitosIniciais += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
//
//        /* Segunda parte da validaÃƒÆ’Ã‚Â§ÃƒÆ’Ã‚Â£o */
//        soma = 0;
//
//        for (int i = 0; i < 5; i++) {
//            if (cnpjCharArray[i] - 48 >= 0 && cnpjCharArray[i] - 48 <= 9) {
//                soma += (cnpjCharArray[i] - 48) * (7 - (i + 1));
//            }
//        }
//
//        for (int i = 0; i < 8; i++) {
//            soma += (cnpjCharArray[i + 5] - 48) * (10 - (i + 1));
//        }
//
//        dig = 11 - (soma % 11);
//        digitosIniciais += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

//       return cnpj.equals(digitosIniciais);
    }
}