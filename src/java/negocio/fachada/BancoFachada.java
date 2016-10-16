/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.fachada;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import negocio.entidade.Banco;
import negocio.excecao.CampoUniqueException;
import negocio.excecao.ExclusaoDeBancoException;
import persistencia.BancoDAO;
import javax.faces.application.FacesMessage;
import javax.faces.convert.ConverterException;


/**
 *
 * @author Ivan Joao Foschini
 */
@Stateless
public class BancoFachada {

    private static final String O_BANCO_SELECIONADO_NAO_PODE_SER_EXCLUIDO_PORQUE_POSSUI_AGENCIAS_ASSOCIADAS = "O banco selecionado n\u00e3o pode ser exclu\u00eddo porque possui ag\u00eancias associadas";
    private static final String O_NUMERO_FORNECIDO_JA_PERTENCE_A_OUTRO_BANCO = "O numero fornecido j\u00e1 pertence a outro banco";

    @EJB
    private BancoDAO bancoDAO;
    
    @EJB 
    private LogEventoPaginaFachada logevento;
    
    @EJB 
    private LogErroFachada logerro;
    
    @EJB 
    private LogPaginaHistoricoDadosFachada logalteracao;
    
    
//    static Date DataAtual = new Date();
//    
//    public static String getNomeUsuarioLogado() {
//        return (String)  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("NomeUsuario");   
//    }
      
    public List<Banco> listar() {
     
       try {
                      
              //logevento.InsereNovoLog("Consulta","Banco",0,BancoFachada.getNomeUsuarioLogado(), DataAtual);
            logevento.InsereNovoLog("Consulta","Banco",0);
  
              return bancoDAO.recuperarTodos();        
        }
        
        catch(Exception ex ) {
            
            //logerro.InsereNovoLog("Banco",0,BancoFachada.getNomeUsuarioLogado(),ex.getMessage(),DataAtual);
             logerro.InsereNovoLog("Banco",0,ex.getMessage());
                  
            FacesMessage message = new FacesMessage(
            FacesMessage.SEVERITY_ERROR, "Internal Error!",  "Length = 8");
            throw new ConverterException(message);
        }
        
    }
    
    public Banco ConsultaPK(Integer ID){
        
        return this.bancoDAO.recuperarPorId(ID);
        
    }
    
    public void alterar(Banco banco_old, Banco banco) throws CampoUniqueException {
        
        try{
            this.verificarSeONumeroFornecidoJaPertenceAOutroBanco(banco.getNumero(), banco.getBancoId());
            
               
            this.ValidaCamposBanco(banco_old,banco);
            
            bancoDAO.alterar(banco);
            
//            logevento.InsereNovoLog("Alteracao","Banco",banco.getBancoId(),BancoFachada.getNomeUsuarioLogado(),DataAtual);
            
            logevento.InsereNovoLog("Alteracao","Banco",banco.getBancoId());
        }
        
        catch(Exception ex ) {
            
//            logerro.InsereNovoLog("Banco",0,BancoFachada.getNomeUsuarioLogado(),ex.getMessage(),DataAtual);
            logerro.InsereNovoLog("Banco",banco.getBancoId(),ex.getMessage());
        }
        
    }

    public void excluir(Banco banco) throws ExclusaoDeBancoException {
        try{
            this.verificarSeOBancoSelecionadoPossuiAgenciasAssociadas(banco.getBancoId());
            bancoDAO.excluir(banco);
            
            
            //logevento.InsereNovoLog("Remocao","Banco",banco.getBancoId(),BancoFachada.getNomeUsuarioLogado(),DataAtual);
            
            logevento.InsereNovoLog("Remocao","Banco",banco.getBancoId());
        }
        catch (Exception ex ){
            
               
//             logerro.InsereNovoLog("Banco",0,BancoFachada.getNomeUsuarioLogado(),ex.getMessage(),DataAtual);
            logerro.InsereNovoLog("Banco",banco.getBancoId(),ex.getMessage());
        }
        
    }

    public void inserir(Banco banco) throws CampoUniqueException {
    
        try {
                   
            this.verificarSeONumeroFornecidoJaPertenceAOutroBanco(banco.getNumero());
            bancoDAO.inserir(banco);
            
            //logevento.InsereNovoLog("Insercao","Banco",banco.getBancoId(),BancoFachada.getNomeUsuarioLogado(),DataAtual);
             logevento.InsereNovoLog("Insercao","Banco",banco.getBancoId());
        }
        
        catch(Exception ex) {
            //logerro.InsereNovoLog("Banco",0,BancoFachada.getNomeUsuarioLogado(),ex.getMessage(),DataAtual);
            logerro.InsereNovoLog("Banco",banco.getBancoId(),ex.getMessage());
        }
        
    }

    

    private void verificarSeOBancoSelecionadoPossuiAgenciasAssociadas(Integer idDoBanco) throws ExclusaoDeBancoException {
        if (bancoDAO.verificarSeOBancoSelecionadoPossuiAgenciasAssociadas(idDoBanco)) {
            throw new ExclusaoDeBancoException(O_BANCO_SELECIONADO_NAO_PODE_SER_EXCLUIDO_PORQUE_POSSUI_AGENCIAS_ASSOCIADAS);
        }
    }

    private void verificarSeONumeroFornecidoJaPertenceAOutroBanco(Integer numero) throws CampoUniqueException {
        try {

            if (bancoDAO.verificarSeONumeroFornecidoJaPertenceAOutroBanco(numero)) {
                throw new CampoUniqueException(O_NUMERO_FORNECIDO_JA_PERTENCE_A_OUTRO_BANCO);
            }
        } catch (PersistenceException e) { //carlos
            //popular o objeto de log
            //chama o dao de log para inserir este objeto de log
            //criar o objeto logerro... logerro.setPagina(PaginaDao.getPagina('banco'))
        }
    }

    private void verificarSeONumeroFornecidoJaPertenceAOutroBanco(Integer numero, Integer idDoBancoCujosDadosEstaoSendoAlterados) throws CampoUniqueException {
        if (bancoDAO.verificarSeONumeroFornecidoJaPertenceAOutroBanco(numero, idDoBancoCujosDadosEstaoSendoAlterados)) {
            throw new CampoUniqueException(O_NUMERO_FORNECIDO_JA_PERTENCE_A_OUTRO_BANCO);
        }
    }
    
    
    private void ValidaCamposBanco(Banco banco_old, Banco banco){
        
//        if (banco_old.getNome() != banco.getNome()) {
//           logalteracao.InsereNovoLog("Banco",banco.getBancoId(),BancoFachada.getNomeUsuarioLogado(),  
//                                        "Nome",banco_old.getNome(),banco.getNome(),DataAtual
//                                     );
//        }
//        
//        if (banco_old.getCnpj() != banco.getCnpj()) {
//           logalteracao.InsereNovoLog("Banco",banco.getBancoId(),BancoFachada.getNomeUsuarioLogado(),
//                                        "CNPJ",banco_old.getCnpj(),banco.getCnpj(),DataAtual
//                                     );
//        }
//        
//        if (banco_old.getNumero() != banco.getNumero()) {
//          logalteracao.InsereNovoLog("Banco",banco.getBancoId(),BancoFachada.getNomeUsuarioLogado(),
//                                        "Numero" ,banco_old.getNumero() ,banco.getNumero() ,DataAtual
//                                    );
//        }
        
        
        if (!banco_old.getNome().equals(banco.getNome())) {
           logalteracao.InsereNovoLog("Banco",banco.getBancoId(),"Nome",banco_old.getNome(),banco.getNome());
        }
        
        if (!banco_old.getCnpj().equals(banco.getCnpj())) {
           logalteracao.InsereNovoLog("Banco",banco.getBancoId(),"CNPJ",banco_old.getCnpj(),banco.getCnpj());
        }
        
        if (banco_old.getNumero() != banco.getNumero()) {
          logalteracao.InsereNovoLog("Banco",banco.getBancoId(),"Numero" ,banco_old.getNumero() ,banco.getNumero());
        }
        
        
        
        
    }
    
    
}
