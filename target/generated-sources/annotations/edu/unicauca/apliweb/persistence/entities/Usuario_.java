package edu.unicauca.apliweb.persistence.entities;

import edu.unicauca.apliweb.persistence.entities.Dispositivo;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-03-09T18:10:14")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> apellidos;
    public static volatile SingularAttribute<Usuario, String> tipo;
    public static volatile ListAttribute<Usuario, Dispositivo> dispositivoList;
    public static volatile SingularAttribute<Usuario, String> cedula;
    public static volatile SingularAttribute<Usuario, String> usuario;
    public static volatile SingularAttribute<Usuario, Integer> id;
    public static volatile SingularAttribute<Usuario, String> nombres;
    public static volatile SingularAttribute<Usuario, String> contraseña;
    public static volatile SingularAttribute<Usuario, Boolean> activo;

}