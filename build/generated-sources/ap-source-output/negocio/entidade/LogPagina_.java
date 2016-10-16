package negocio.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.entidade.LogErro;
import negocio.entidade.LogEventoPagina;
import negocio.entidade.LogPaginaHistoricoDados;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-16T19:45:05")
@StaticMetamodel(LogPagina.class)
public class LogPagina_ { 

    public static volatile CollectionAttribute<LogPagina, LogPaginaHistoricoDados> LogHistoricos;
    public static volatile SingularAttribute<LogPagina, String> NomePagina;
    public static volatile SingularAttribute<LogPagina, Integer> status;
    public static volatile CollectionAttribute<LogPagina, LogErro> LogErros;
    public static volatile SingularAttribute<LogPagina, Integer> IdPagina;
    public static volatile CollectionAttribute<LogPagina, LogEventoPagina> LogEventoPaginas;

}