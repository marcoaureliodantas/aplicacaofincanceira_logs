package negocio.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.entidade.CaixaEletronico;
import negocio.entidade.ClienteTemConta;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-16T19:45:05")
@StaticMetamodel(Transacao.class)
public class Transacao_ { 

    public static volatile SingularAttribute<Transacao, Date> dataDeRealizacao;
    public static volatile SingularAttribute<Transacao, Float> valor;
    public static volatile SingularAttribute<Transacao, Date> horaDeRealizacao;
    public static volatile SingularAttribute<Transacao, CaixaEletronico> caixaEletronico;
    public static volatile SingularAttribute<Transacao, Integer> transacaoId;
    public static volatile SingularAttribute<Transacao, ClienteTemConta> clienteTemConta;

}