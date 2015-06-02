package Unicordoba.Registro_Control.Base_de_Datos.Entity;

import Unicordoba.Registro_Control.Base_de_Datos.Entity.Docente;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Programa;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Universidad;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-02T15:13:43")
@StaticMetamodel(Facultad.class)
public class Facultad_ { 

    public static volatile SingularAttribute<Facultad, String> decano;
    public static volatile SingularAttribute<Facultad, String> ubicacion;
    public static volatile ListAttribute<Facultad, Programa> programaList;
    public static volatile SingularAttribute<Facultad, Universidad> universidadid;
    public static volatile SingularAttribute<Facultad, Integer> id;
    public static volatile SingularAttribute<Facultad, String> nombre;
    public static volatile ListAttribute<Facultad, Docente> docenteList;

}