package negocio.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.entidade.LogPagina;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-16T19:45:05")
@StaticMetamodel(LogErro.class)
public class LogErro_ { 

    public static volatile SingularAttribute<LogErro, Date> HoraInclusao;
    public static volatile SingularAttribute<LogErro, LogPagina> LogPagina;
    public static volatile SingularAttribute<LogErro, Integer> IdObjeto;
    public static volatile SingularAttribute<LogErro, String> NomeUsuario;
    public static volatile SingularAttribute<LogErro, String> MsgErro;
    public static volatile SingularAttribute<LogErro, Long> IdErro;
    public static volatile SingularAttribute<LogErro, Date> DataInclusao;

}