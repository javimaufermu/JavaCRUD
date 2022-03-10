package edu.unicauca.apliweb.persistence.entities;

import edu.unicauca.apliweb.persistence.entities.Dispositivo;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-03-10T00:20:33")
@StaticMetamodel(RangoParametros.class)
public class RangoParametros_ { 

    public static volatile SingularAttribute<RangoParametros, Float> temperaturamax;
    public static volatile ListAttribute<RangoParametros, Dispositivo> dispositivoList;
    public static volatile SingularAttribute<RangoParametros, Float> intensidadluzmax;
    public static volatile SingularAttribute<RangoParametros, Float> humedadambientemax;
    public static volatile SingularAttribute<RangoParametros, Float> intensidadluzmin;
    public static volatile SingularAttribute<RangoParametros, Float> humedadambientemin;
    public static volatile SingularAttribute<RangoParametros, Float> temperaturamin;
    public static volatile SingularAttribute<RangoParametros, Integer> id;
    public static volatile SingularAttribute<RangoParametros, Float> humedadmin;
    public static volatile SingularAttribute<RangoParametros, Float> humedadmax;

}