package negocio.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.entidade.Banco;
import negocio.entidade.Conta;
import negocio.entidade.Endereco;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-16T19:45:05")
@StaticMetamodel(Agencia.class)
public class Agencia_ { 

    public static volatile SingularAttribute<Agencia, Banco> banco;
    public static volatile SingularAttribute<Agencia, String> nome;
    public static volatile SingularAttribute<Agencia, Endereco> endereco;
    public static volatile CollectionAttribute<Agencia, Conta> contas;
    public static volatile SingularAttribute<Agencia, Integer> agenciaId;
    public static volatile SingularAttribute<Agencia, Integer> numero;

}