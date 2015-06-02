package Unicordoba.Registro_Control.Base_de_Datos.Entity;

import Unicordoba.Registro_Control.Base_de_Datos.Entity.Dinamizador;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Estudiante;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Salon;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Universidad;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-02T15:13:43")
@StaticMetamodel(Sede.class)
public class Sede_ { 

    public static volatile ListAttribute<Sede, Dinamizador> dinamizadorList;
    public static volatile SingularAttribute<Sede, String> ubicacion;
    public static volatile SingularAttribute<Sede, Universidad> universidadid;
    public static volatile ListAttribute<Sede, Salon> salonList;
    public static volatile ListAttribute<Sede, Estudiante> estudianteList;
    public static volatile SingularAttribute<Sede, Integer> id;
    public static volatile SingularAttribute<Sede, Integer> telefono;
    public static volatile SingularAttribute<Sede, String> nombre;

}