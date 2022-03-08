package edu.unicauca.apliweb.persistence.entities;

import edu.unicauca.apliweb.persistence.entities.Dispositivo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-03-08T02:06:25")
@StaticMetamodel(Alerta.class)
public class Alerta_ { 

    public static volatile SingularAttribute<Alerta, Date> fecha;
    public static volatile SingularAttribute<Alerta, Dispositivo> iddispositivo;
    public static volatile SingularAttribute<Alerta, String> tipo;
    public static volatile SingularAttribute<Alerta, Float> minimo;
    public static volatile SingularAttribute<Alerta, Boolean> revisada;
    public static volatile SingularAttribute<Alerta, Date> hora;
    public static volatile SingularAttribute<Alerta, String> variable;
    public static volatile SingularAttribute<Alerta, Integer> valor;
    public static volatile SingularAttribute<Alerta, Float> maximo;
    public static volatile SingularAttribute<Alerta, Integer> id;

}