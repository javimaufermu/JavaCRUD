package edu.unicauca.apliweb.persistence.entities;

import edu.unicauca.apliweb.persistence.entities.Alerta;
import edu.unicauca.apliweb.persistence.entities.Parametros;
import edu.unicauca.apliweb.persistence.entities.RangoParametros;
import edu.unicauca.apliweb.persistence.entities.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-03-10T00:20:33")
@StaticMetamodel(Dispositivo.class)
public class Dispositivo_ { 

    public static volatile SingularAttribute<Dispositivo, String> estado;
    public static volatile SingularAttribute<Dispositivo, String> latitud;
    public static volatile SingularAttribute<Dispositivo, String> longitud;
    public static volatile SingularAttribute<Dispositivo, Integer> id;
    public static volatile SingularAttribute<Dispositivo, RangoParametros> idrango;
    public static volatile SingularAttribute<Dispositivo, String> nombre;
    public static volatile ListAttribute<Dispositivo, Alerta> alertaList;
    public static volatile SingularAttribute<Dispositivo, Usuario> idusuario;
    public static volatile ListAttribute<Dispositivo, Parametros> parametrosList;

}