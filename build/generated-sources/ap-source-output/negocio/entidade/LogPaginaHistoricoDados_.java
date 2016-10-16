package negocio.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.entidade.LogPagina;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-16T19:45:05")
@StaticMetamodel(LogPaginaHistoricoDados.class)
public class LogPaginaHistoricoDados_ { 

    public static volatile SingularAttribute<LogPaginaHistoricoDados, Date> DataAlteracao;
    public static volatile SingularAttribute<LogPaginaHistoricoDados, LogPagina> LogPagina;
    public static volatile SingularAttribute<LogPaginaHistoricoDados, Integer> IdObjeto;
    public static volatile SingularAttribute<LogPaginaHistoricoDados, Date> HoraAlteracao;
    public static volatile SingularAttribute<LogPaginaHistoricoDados, Long> IdHistorico;
    public static volatile SingularAttribute<LogPaginaHistoricoDados, String> NomeUsuario;
    public static volatile SingularAttribute<LogPaginaHistoricoDados, String> CampoAlterado;
    public static volatile SingularAttribute<LogPaginaHistoricoDados, String> ConteudoAtual;
    public static volatile SingularAttribute<LogPaginaHistoricoDados, String> ConteudoAnterior;

}