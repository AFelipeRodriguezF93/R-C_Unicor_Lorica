package Unicordoba.Registro_Control.Base_de_Datos.Entity;

import Unicordoba.Registro_Control.Base_de_Datos.Entity.Dinamizador;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Docente;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Estudiante;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-02T15:13:43")
@StaticMetamodel(InformacionDeSeguridad.class)
public class InformacionDeSeguridad_ { 

    public static volatile ListAttribute<InformacionDeSeguridad, Dinamizador> dinamizadorList;
    public static volatile SingularAttribute<InformacionDeSeguridad, String> clave;
    public static volatile SingularAttribute<InformacionDeSeguridad, String> estado;
    public static volatile ListAttribute<InformacionDeSeguridad, Estudiante> estudianteList;
    public static volatile SingularAttribute<InformacionDeSeguridad, String> codigoHuella;
    public static volatile ListAttribute<InformacionDeSeguridad, Docente> docenteList;
    public static volatile SingularAttribute<InformacionDeSeguridad, Integer> idI;

}