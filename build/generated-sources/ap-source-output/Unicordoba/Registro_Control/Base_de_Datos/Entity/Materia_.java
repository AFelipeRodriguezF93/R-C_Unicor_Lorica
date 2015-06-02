package Unicordoba.Registro_Control.Base_de_Datos.Entity;

import Unicordoba.Registro_Control.Base_de_Datos.Entity.Curso;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Docente;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Estudiante;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Horario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-02T15:13:43")
@StaticMetamodel(Materia.class)
public class Materia_ { 

    public static volatile SingularAttribute<Materia, String> contenido;
    public static volatile ListAttribute<Materia, Estudiante> estudianteList;
    public static volatile SingularAttribute<Materia, Integer> id;
    public static volatile SingularAttribute<Materia, Integer> creditos;
    public static volatile SingularAttribute<Materia, Integer> semestre;
    public static volatile SingularAttribute<Materia, String> nombre;
    public static volatile ListAttribute<Materia, Docente> docenteList;
    public static volatile ListAttribute<Materia, Curso> cursoList;
    public static volatile ListAttribute<Materia, Horario> horarioList;

}