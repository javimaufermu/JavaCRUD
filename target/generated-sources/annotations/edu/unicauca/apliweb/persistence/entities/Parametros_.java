package edu.unicauca.apliweb.persistence.entities;

import edu.unicauca.apliweb.persistence.entities.Dispositivo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-03-08T00:09:19")
@StaticMetamodel(Parametros.class)
public class Parametros_ { 

    public static volatile SingularAttribute<Parametros, Date> fecha;
    public static volatile SingularAttribute<Parametros, Dispositivo> iddispositivo;
    public static volatile SingularAttribute<Parametros, Float> humedadsuelo;
    public static volatile SingularAttribute<Parametros, Boolean> precipitaciones;
    public static volatile SingularAttribute<Parametros, Date> hora;
    public static volatile SingularAttribute<Parametros, Integer> id;
    public static volatile SingularAttribute<Parametros, Float> temperatura;
    public static volatile SingularAttribute<Parametros, Float> humedadambiente;
    public static volatile SingularAttribute<Parametros, Float> intensidadluzsolar;

}