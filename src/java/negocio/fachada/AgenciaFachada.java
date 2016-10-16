/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.fachada;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.convert.ConverterException;
import negocio.entidade.Agencia;
import negocio.entidade.Banco;
import negocio.entidade.Cidade;
import negocio.entidade.Estado;
import negocio.excecao.CampoUniqueException;
import persistencia.AgenciaDAO;
import persistencia.BancoDAO;
import persistencia.CidadeDAO;
import persistencia.EstadoDAO;

/**
 *
 * @author Ivan Joao Foschini 
 */
@Stateless
public class AgenciaFachada {
    
    private static final String O_NUMERO_FORNECIDO_JA_PERTENCE_A_OUTRA_AGENCIA = "O numero fornecido j\u00e1 pertence a outra ag\u00eancia";

//    static Date DataAtual = new Date();
//    
//    public static String getNomeUsuarioLogado() {
//        return (String)  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("NomeUsuario");   
//    }
    
    @EJB
    private AgenciaDAO agenciaDAO;
    @EJB
    private BancoDAO bancoDAO;
    @EJB
    private CidadeDAO cidadeDAO;
    @EJB
    private EstadoDAO estadoDAO;
    @EJB 
    private LogEventoPaginaFachada logevento;
    @EJB 
    private LogErroFachada logerro;
    @EJB 
    private LogPaginaHistoricoDadosFachada logalteracao;
    
    public void alterar(Agencia agencia_old, Agencia agencia, Integer idDaCidadeSelecionada, Integer idDoBancoSelecionado) throws CampoUniqueException {
        try {
            this.verificarSeONumeroFornecidoJaPertenceAOutraAgencia(agencia.getNumero(), agencia.getAgenciaId());
            agencia.getEndereco().setCidade(this.recuperarCidadePorId(idDaCidadeSelecionada));
            agencia.setBanco(this.recuperarBancoPorId(idDoBancoSelecionado));        
            agenciaDAO.alterar(agencia);
            //logevento.InsereNovoLog("Alteracao","Agencia",agencia.getAgenciaId(),BancoFachada.getNomeUsuarioLogado(),DataAtual);
            logevento.InsereNovoLog("Alteracao","Agencia",agencia.getAgenciaId());
            this.ValidaCamposAgencia(agencia_old,agencia);
        }
        
        catch(Exception ex ) {
            //logerro.InsereNovoLog("Agencia",0,AgenciaFachada.getNomeUsuarioLogado(),ex.getMessage(),DataAtual);
            logerro.InsereNovoLog("Agencia",0,ex.getMessage());
        }
    }

    public void excluir(Agencia agencia) {
        try {
            agenciaDAO.excluir(agencia);
            //logevento.InsereNovoLog("Remocao","Agencia",agencia.getAgenciaId(),BancoFachada.getNomeUsuarioLogado(),DataAtual);
            logevento.InsereNovoLog("Remocao","Agencia",agencia.getAgenciaId());
        }
        
        catch(Exception ex ){
             //logerro.InsereNovoLog("Agencia",0,AgenciaFachada.getNomeUsuarioLogado(),ex.getMessage(),DataAtual);
            logerro.InsereNovoLog("Agencia",0,ex.getMessage());
        }
        
    }

    public void inserir(Agencia agencia, Integer idDaCidadeSelecionada, Integer idDoBancoSelecionado) throws CampoUniqueException {   
        try {
            
            this.verificarSeONumeroFornecidoJaPertenceAOutraAgencia(agencia.getNumero());
            agencia.getEndereco().setCidade(this.recuperarCidadePorId(idDaCidadeSelecionada));
            agencia.setBanco(this.recuperarBancoPorId(idDoBancoSelecionado));                
            agenciaDAO.inserir(agencia);
            //logevento.InsereNovoLog("Insercao","Agencia",agencia.getAgenciaId(),BancoFachada.getNomeUsuarioLogado(),DataAtual);
            logevento.InsereNovoLog("Insercao","Agencia",agencia.getAgenciaId());
        }
        
        catch (Exception ex) {
             //logerro.InsereNovoLog("Agencia",0,AgenciaFachada.getNomeUsuarioLogado(),ex.getMessage(),DataAtual);
            logerro.InsereNovoLog("Agencia",0,ex.getMessage());
            
        }
        
    }

    public List<Agencia> listar() {
        
        try {
        
        //logevento.InsereNovoLog("Consulta","Agencia",0,AgenciaFachada.getNomeUsuarioLogado(), DataAtual);
            logevento.InsereNovoLog("Consulta","Agencia",0);
        return agenciaDAO.recuperarTodos();
        }
        
        catch(Exception ex ) {
            //logerro.InsereNovoLog("Agencia",0,AgenciaFachada.getNomeUsuarioLogado(),ex.getMessage(),DataAtual);
            logerro.InsereNovoLog("Agencia",0,ex.getMessage());
            
            FacesMessage message = new FacesMessage(
            FacesMessage.SEVERITY_ERROR, "Internal Error!",  "Length = 8");
            throw new ConverterException(message);
        }
    }

    public List<Banco> recuperarBancos() {
        return bancoDAO.recuperarTodos();
    }

    public Banco recuperarBancoPorId(Integer id) {
        return bancoDAO.recuperarPorId(id);
    }

    public Cidade recuperarCidadePorId(Integer idDaCidade) {
        return cidadeDAO.recuperarPorId(idDaCidade);
    }

    public List<Cidade> recuperarCidadesPorEstado(Integer idDoEstado) {
        return cidadeDAO.recuperarPorEstado(idDoEstado);
    }

    public List<Estado> recuperarEstados() {
        return estadoDAO.recuperarTodos();
    }
    
    private void verificarSeONumeroFornecidoJaPertenceAOutraAgencia(Integer numero) throws CampoUniqueException {
        if (agenciaDAO.verificarSeONumeroFornecidoJaPertenceAOutraAgencia(numero)) {
            throw new CampoUniqueException(O_NUMERO_FORNECIDO_JA_PERTENCE_A_OUTRA_AGENCIA);
        }
    }

    private void verificarSeONumeroFornecidoJaPertenceAOutraAgencia(Integer numero, Integer idDaAgenciaCujosDadosEstaoSendoAlterados) throws CampoUniqueException {
        if (agenciaDAO.verificarSeONumeroFornecidoJaPertenceAOutraAgencia(numero, idDaAgenciaCujosDadosEstaoSendoAlterados)) {
            throw new CampoUniqueException(O_NUMERO_FORNECIDO_JA_PERTENCE_A_OUTRA_AGENCIA);
        }
    }
    
     public Agencia ConsultaPK(Integer ID){
        
        return this.agenciaDAO.recuperarPorId(ID);
        
    }
     
     
      private void ValidaCamposAgencia(Agencia agencia_old, Agencia agencia){
        
          if (!(agencia_old.getNome()).equals(agencia.getNome())) {
           logalteracao.InsereNovoLog("Agencia",agencia.getAgenciaId(), "Nome",agencia_old.getNome(),agencia.getNome() );
        }
        
        if (agencia_old.getNumero()!= agencia.getNumero()) {
           logalteracao.InsereNovoLog("Agencia",agencia.getAgenciaId(), "Numero",agencia_old.getNumero(),agencia.getNumero() );
        }
        
        if (!agencia_old.getEndereco().getLogradouro().equals(agencia.getEndereco().getLogradouro())) {
           logalteracao.InsereNovoLog("Agencia",agencia.getAgenciaId(),  "Endereco",agencia_old.getEndereco().getLogradouro(),agencia.getEndereco().getLogradouro() );
        }
        
        if (agencia_old.getEndereco().getNumero()!= agencia.getEndereco().getNumero()) {
           logalteracao.InsereNovoLog("Agencia",agencia.getAgenciaId(), "Numero Endereco",agencia_old.getEndereco().getNumero(),agencia.getEndereco().getNumero() );
        }
        
        if (!agencia_old.getEndereco().getComplemento().equals(agencia.getEndereco().getComplemento())) {
           logalteracao.InsereNovoLog("Agencia",agencia.getAgenciaId(),  
                                        "Complemento Endereco",agencia_old.getEndereco().getComplemento(),agencia.getEndereco().getComplemento()
                                     );
        }
        
        if (!agencia_old.getEndereco().getBairro().equals(agencia.getEndereco().getBairro())) {
           logalteracao.InsereNovoLog("Agencia",agencia.getAgenciaId(),  
                                        "Bairro Endereco",agencia_old.getEndereco().getBairro(),agencia.getEndereco().getBairro()
                                     );
        }
        
        
        if (agencia_old.getEndereco().getCep()!= agencia.getEndereco().getCep()) {
           logalteracao.InsereNovoLog("Agencia",agencia.getAgenciaId(),  
                                        "CEP Endereco",agencia_old.getEndereco().getCep(),agencia.getEndereco().getCep()
                                     );
        }
        
        if (!agencia_old.getEndereco().getCidade().equals(agencia.getEndereco().getCidade())) {
           logalteracao.InsereNovoLog("Agencia",agencia.getAgenciaId(),
                                        "Cidade Endereco",agencia_old.getEndereco().getCidade().getNome(),agencia.getEndereco().getCidade().getNome()
                                     );
        }
        
        if (!agencia_old.getEndereco().getCidade().getEstado().equals(agencia.getEndereco().getCidade().getEstado())) {
           logalteracao.InsereNovoLog("Agencia",agencia.getAgenciaId(),
                                        "Estado Endereco",agencia_old.getEndereco().getCidade().getEstado().getNome(),agencia.getEndereco().getCidade().getEstado().getNome()
                                     );
        }
        
        if (!agencia_old.getBanco().equals(agencia.getBanco())) {
           logalteracao.InsereNovoLog("Agencia",agencia.getAgenciaId(),
                                        "Banco",agencia_old.getBanco().getNome(),agencia.getBanco().getNome()
                                     );
        }
          
    }  
          
          
          
          
          
          
//        if (!(agencia_old.getNome()).equals(agencia.getNome())) {
//           logalteracao.InsereNovoLog("Agencia",agencia.getAgenciaId(),AgenciaFachada.getNomeUsuarioLogado(),  
//                                        "Nome",agencia_old.getNome(),agencia.getNome(),DataAtual
//                                     );
//        }
//        
//        if (agencia_old.getNumero()!= agencia.getNumero()) {
//           logalteracao.InsereNovoLog("Agencia",agencia.getAgenciaId(),AgenciaFachada.getNomeUsuarioLogado(),  
//                                        "Numero",agencia_old.getNumero(),agencia.getNumero(),DataAtual
//                                     );
//        }
//        
//        if (!agencia_old.getEndereco().getLogradouro().equals(agencia.getEndereco().getLogradouro())) {
//           logalteracao.InsereNovoLog("Agencia",agencia.getAgenciaId(),AgenciaFachada.getNomeUsuarioLogado(),  
//                                        "Endereco",agencia_old.getEndereco().getLogradouro(),agencia.getEndereco().getLogradouro(),DataAtual
//                                     );
//        }
//        
//        if (agencia_old.getEndereco().getNumero()!= agencia.getEndereco().getNumero()) {
//           logalteracao.InsereNovoLog("Agencia",agencia.getAgenciaId(),AgenciaFachada.getNomeUsuarioLogado(),  
//                                        "Numero Endereco",agencia_old.getEndereco().getNumero(),agencia.getEndereco().getNumero(),DataAtual
//                                     );
//        }
//        
//        if (!agencia_old.getEndereco().getComplemento().equals(agencia.getEndereco().getComplemento())) {
//           logalteracao.InsereNovoLog("Agencia",agencia.getAgenciaId(),AgenciaFachada.getNomeUsuarioLogado(),  
//                                        "Complemento Endereco",agencia_old.getEndereco().getComplemento(),agencia.getEndereco().getComplemento(),DataAtual
//                                     );
//        }
//        
//        if (!agencia_old.getEndereco().getBairro().equals(agencia.getEndereco().getBairro())) {
//           logalteracao.InsereNovoLog("Agencia",agencia.getAgenciaId(),AgenciaFachada.getNomeUsuarioLogado(),  
//                                        "Bairro Endereco",agencia_old.getEndereco().getBairro(),agencia.getEndereco().getBairro(),DataAtual
//                                     );
//        }
//        
//        
//        if (agencia_old.getEndereco().getCep()!= agencia.getEndereco().getCep()) {
//           logalteracao.InsereNovoLog("Agencia",agencia.getAgenciaId(),AgenciaFachada.getNomeUsuarioLogado(),  
//                                        "CEP Endereco",agencia_old.getEndereco().getCep(),agencia.getEndereco().getCep(),DataAtual
//                                     );
//        }
//        
//        if (!agencia_old.getEndereco().getCidade().equals(agencia.getEndereco().getCidade())) {
//           logalteracao.InsereNovoLog("Agencia",agencia.getAgenciaId(),AgenciaFachada.getNomeUsuarioLogado(),  
//                                        "Cidade Endereco",agencia_old.getEndereco().getCidade().getNome(),agencia.getEndereco().getCidade().getNome(),DataAtual
//                                     );
//        }
//        
//        if (!agencia_old.getEndereco().getCidade().getEstado().equals(agencia.getEndereco().getCidade().getEstado())) {
//           logalteracao.InsereNovoLog("Agencia",agencia.getAgenciaId(),AgenciaFachada.getNomeUsuarioLogado(),  
//                                        "Estado Endereco",agencia_old.getEndereco().getCidade().getEstado().getNome(),agencia.getEndereco().getCidade().getEstado().getNome(),DataAtual
//                                     );
//        }
//        
//        
//        if (!agencia_old.getBanco().equals(agencia.getBanco())) {
//           logalteracao.InsereNovoLog("Agencia",agencia.getAgenciaId(),AgenciaFachada.getNomeUsuarioLogado(),  
//                                        "Banco",agencia_old.getBanco().getNome(),agencia.getBanco().getNome(),DataAtual
//                                     );
//        }
          
          
          
          
              
        
        
   
     
    
}