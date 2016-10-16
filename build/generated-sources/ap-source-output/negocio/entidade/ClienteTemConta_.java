package negocio.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.entidade.Cliente;
import negocio.entidade.ClienteTemContaPK;
import negocio.entidade.Conta;
import negocio.entidade.Transacao;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-16T19:45:05")
@StaticMetamodel(ClienteTemConta.class)
public class ClienteTemConta_ { 

    public static volatile CollectionAttribute<ClienteTemConta, Transacao> transacoes;
    public static volatile SingularAttribute<ClienteTemConta, Cliente> cliente;
    public static volatile SingularAttribute<ClienteTemConta, String> titularidade;
    public static volatile SingularAttribute<ClienteTemConta, Conta> conta;
    public static volatile SingularAttribute<ClienteTemConta, ClienteTemContaPK> clienteTemContaPK;

}