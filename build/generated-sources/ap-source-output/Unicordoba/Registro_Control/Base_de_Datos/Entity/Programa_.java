package Unicordoba.Registro_Control.Base_de_Datos.Entity;

import Unicordoba.Registro_Control.Base_de_Datos.Entity.Curso;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Facultad;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-02T15:13:43")
@StaticMetamodel(Programa.class)
public class Programa_ { 

    public static volatile SingularAttribute<Programa, Integer> codigo;
    public static volatile SingularAttribute<Programa, Facultad> facultadid;
    public static volatile SingularAttribute<Programa, Integer> id;
    public static volatile SingularAttribute<Programa, String> nombre;
    public static volatile SingularAttribute<Programa, String> jefePrograma;
    public static volatile ListAttribute<Programa, Curso> cursoList;

}