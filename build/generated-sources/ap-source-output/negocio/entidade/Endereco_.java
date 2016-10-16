package negocio.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.entidade.Agencia;
import negocio.entidade.CaixaEletronico;
import negocio.entidade.Cidade;
import negocio.entidade.Cliente;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-16T19:45:05")
@StaticMetamodel(Endereco.class)
public class Endereco_ { 

    public static volatile SingularAttribute<Endereco, String> bairro;
    public static volatile SingularAttribute<Endereco, Agencia> agencia;
    public static volatile SingularAttribute<Endereco, Cidade> cidade;
    public static volatile SingularAttribute<Endereco, String> complemento;
    public static volatile SingularAttribute<Endereco, Integer> cep;
    public static volatile SingularAttribute<Endereco, Cliente> cliente;
    public static volatile SingularAttribute<Endereco, Integer> enderecoId;
    public static volatile SingularAttribute<Endereco, String> logradouro;
    public static volatile SingularAttribute<Endereco, CaixaEletronico> caixaEletronico;
    public static volatile SingularAttribute<Endereco, Integer> numero;

}