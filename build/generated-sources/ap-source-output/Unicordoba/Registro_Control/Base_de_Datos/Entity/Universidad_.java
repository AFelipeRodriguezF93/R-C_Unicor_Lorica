package Unicordoba.Registro_Control.Base_de_Datos.Entity;

import Unicordoba.Registro_Control.Base_de_Datos.Entity.Facultad;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Sede;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-02T15:13:43")
@StaticMetamodel(Universidad.class)
public class Universidad_ { 

    public static volatile ListAttribute<Universidad, Sede> sedeList;
    public static volatile SingularAttribute<Universidad, String> nit;
    public static volatile SingularAttribute<Universidad, Integer> id;
    public static volatile ListAttribute<Universidad, Facultad> facultadList;
    public static volatile SingularAttribute<Universidad, String> nombre;

}