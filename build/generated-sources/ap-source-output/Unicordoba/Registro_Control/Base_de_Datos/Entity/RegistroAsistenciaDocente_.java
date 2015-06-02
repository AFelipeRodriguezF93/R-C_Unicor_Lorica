package Unicordoba.Registro_Control.Base_de_Datos.Entity;

import Unicordoba.Registro_Control.Base_de_Datos.Entity.Curso;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Docente;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Salon;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.UtensiliosDeClase;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-02T15:13:43")
@StaticMetamodel(RegistroAsistenciaDocente.class)
public class RegistroAsistenciaDocente_ { 

    public static volatile SingularAttribute<RegistroAsistenciaDocente, Salon> salonid;
    public static volatile SingularAttribute<RegistroAsistenciaDocente, Date> fecha;
    public static volatile SingularAttribute<RegistroAsistenciaDocente, String> tipoAsistencia;
    public static volatile SingularAttribute<RegistroAsistenciaDocente, UtensiliosDeClase> utensiliosDeClaseid;
    public static volatile SingularAttribute<RegistroAsistenciaDocente, Docente> docenteidDocente;
    public static volatile SingularAttribute<RegistroAsistenciaDocente, Integer> id;
    public static volatile SingularAttribute<RegistroAsistenciaDocente, Curso> cursoid;

}