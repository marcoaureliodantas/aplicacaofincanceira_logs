package negocio.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.entidade.LogEventoPagina;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-16T19:45:05")
@StaticMetamodel(LogAtividade.class)
public class LogAtividade_ { 

    public static volatile SingularAttribute<LogAtividade, Integer> status;
    public static volatile SingularAttribute<LogAtividade, String> NomeAtividade;
    public static volatile CollectionAttribute<LogAtividade, LogEventoPagina> LogEventoPaginas;
    public static volatile SingularAttribute<LogAtividade, Integer> IdAtividade;

}