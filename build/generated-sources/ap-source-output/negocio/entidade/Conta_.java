package negocio.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.entidade.Agencia;
import negocio.entidade.ClienteTemConta;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-16T19:45:05")
@StaticMetamodel(Conta.class)
public abstract class Conta_ { 

    public static volatile SingularAttribute<Conta, Integer> contaId;
    public static volatile SingularAttribute<Conta, Agencia> agencia;
    public static volatile CollectionAttribute<Conta, ClienteTemConta> clienteTemContas;
    public static volatile SingularAttribute<Conta, Date> dataDeAbertura;
    public static volatile SingularAttribute<Conta, Float> saldo;
    public static volatile SingularAttribute<Conta, Integer> numero;

}