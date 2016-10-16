package negocio.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-16T19:45:05")
@StaticMetamodel(ContaPoupanca.class)
public class ContaPoupanca_ extends Conta_ {

    public static volatile SingularAttribute<ContaPoupanca, Integer> correcaoMonetaria;
    public static volatile SingularAttribute<ContaPoupanca, Integer> juros;
    public static volatile SingularAttribute<ContaPoupanca, Date> dataDeAniversario;

}