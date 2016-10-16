package negocio.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.entidade.ClienteTemConta;
import negocio.entidade.Endereco;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-16T19:45:05")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ { 

    public static volatile SingularAttribute<Cliente, String> status;
    public static volatile CollectionAttribute<Cliente, ClienteTemConta> clienteTemContas;
    public static volatile SingularAttribute<Cliente, Integer> clienteId;
    public static volatile SingularAttribute<Cliente, Date> dataDeInicioDeMoradia;
    public static volatile SingularAttribute<Cliente, String> nome;
    public static volatile CollectionAttribute<Cliente, Endereco> enderecos;

}