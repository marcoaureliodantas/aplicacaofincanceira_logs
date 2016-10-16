package negocio.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.entidade.Endereco;
import negocio.entidade.Transacao;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-16T19:45:05")
@StaticMetamodel(CaixaEletronico.class)
public class CaixaEletronico_ { 

    public static volatile CollectionAttribute<CaixaEletronico, Transacao> transacoes;
    public static volatile SingularAttribute<CaixaEletronico, Integer> caixaEletronicoId;
    public static volatile SingularAttribute<CaixaEletronico, Endereco> endereco;

}