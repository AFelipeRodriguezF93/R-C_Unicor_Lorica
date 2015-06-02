package Unicordoba.Registro_Control.Base_de_Datos.Entity;

import Unicordoba.Registro_Control.Base_de_Datos.Entity.Materia;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Programa;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.RegistroAsistenciaDocente;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Salon;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-02T15:13:43")
@StaticMetamodel(Curso.class)
public class Curso_ { 

    public static volatile SingularAttribute<Curso, Salon> salonid;
    public static volatile ListAttribute<Curso, RegistroAsistenciaDocente> registroAsistenciaDocenteList;
    public static volatile ListAttribute<Curso, Materia> materiaList;
    public static volatile SingularAttribute<Curso, Programa> programaid;
    public static volatile SingularAttribute<Curso, Integer> id;

}