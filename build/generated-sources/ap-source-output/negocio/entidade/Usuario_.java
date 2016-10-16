package negocio.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.entidade.Grupo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-16T19:45:05")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> nomeDeUsuario;
    public static volatile SingularAttribute<Usuario, String> senha;
    public static volatile CollectionAttribute<Usuario, Grupo> grupos;

}