package negocio.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.entidade.LogAtividade;
import negocio.entidade.LogPagina;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-16T19:45:05")
@StaticMetamodel(LogEventoPagina.class)
public class LogEventoPagina_ { 

    public static volatile SingularAttribute<LogEventoPagina, LogAtividade> LogAtividade;
    public static volatile SingularAttribute<LogEventoPagina, LogPagina> LogPagina;
    public static volatile SingularAttribute<LogEventoPagina, Integer> IdObjeto;
    public static volatile SingularAttribute<LogEventoPagina, Long> IdEvento;
    public static volatile SingularAttribute<LogEventoPagina, String> NomeUsuario;
    public static volatile SingularAttribute<LogEventoPagina, Date> HoraInsercao;
    public static volatile SingularAttribute<LogEventoPagina, Date> DataInsercao;

}