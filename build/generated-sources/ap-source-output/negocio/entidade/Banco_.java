package negocio.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.entidade.Agencia;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-16T19:45:05")
@StaticMetamodel(Banco.class)
public class Banco_ { 

    public static volatile SingularAttribute<Banco, Integer> bancoId;
    public static volatile CollectionAttribute<Banco, Agencia> agencias;
    public static volatile SingularAttribute<Banco, String> nome;
    public static volatile SingularAttribute<Banco, String> cnpj;
    public static volatile SingularAttribute<Banco, Integer> numero;

}