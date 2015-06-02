package Unicordoba.Registro_Control.Base_de_Datos.Entity;

import Unicordoba.Registro_Control.Base_de_Datos.Entity.InformacionBasica;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.InformacionDeSeguridad;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Materia;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Sede;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-02T15:13:43")
@StaticMetamodel(Estudiante.class)
public class Estudiante_ { 

    public static volatile SingularAttribute<Estudiante, InformacionDeSeguridad> informacionDeSeguridadidI;
    public static volatile SingularAttribute<Estudiante, Sede> sedeid;
    public static volatile ListAttribute<Estudiante, Materia> materiaList;
    public static volatile SingularAttribute<Estudiante, Integer> idEstudiante;
    public static volatile SingularAttribute<Estudiante, InformacionBasica> informacionBasicaId;

}