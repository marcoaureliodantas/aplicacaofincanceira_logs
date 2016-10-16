package negocio.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.entidade.Endereco;
import negocio.entidade.Estado;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-16T19:45:05")
@StaticMetamodel(Cidade.class)
public class Cidade_ { 

    public static volatile SingularAttribute<Cidade, Estado> estado;
    public static volatile SingularAttribute<Cidade, String> nome;
    public static volatile CollectionAttribute<Cidade, Endereco> enderecos;
    public static volatile SingularAttribute<Cidade, Integer> cidadeId;

}