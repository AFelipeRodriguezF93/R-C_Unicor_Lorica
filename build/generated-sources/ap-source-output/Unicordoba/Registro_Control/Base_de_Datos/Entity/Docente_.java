package Unicordoba.Registro_Control.Base_de_Datos.Entity;

import Unicordoba.Registro_Control.Base_de_Datos.Entity.Facultad;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.InformacionBasica;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.InformacionDeSeguridad;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Materia;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.RegistroAsistenciaDocente;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-02T15:13:43")
@StaticMetamodel(Docente.class)
public class Docente_ { 

    public static volatile SingularAttribute<Docente, InformacionDeSeguridad> informacionDeSeguridadidI;
    public static volatile ListAttribute<Docente, RegistroAsistenciaDocente> registroAsistenciaDocenteList;
    public static volatile ListAttribute<Docente, Materia> materiaList;
    public static volatile SingularAttribute<Docente, Facultad> facultadid;
    public static volatile SingularAttribute<Docente, Integer> idDocente;
    public static volatile SingularAttribute<Docente, InformacionBasica> informacionBasicaId;

}