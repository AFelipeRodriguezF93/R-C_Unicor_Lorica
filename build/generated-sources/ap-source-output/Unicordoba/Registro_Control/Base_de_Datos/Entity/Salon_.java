package Unicordoba.Registro_Control.Base_de_Datos.Entity;

import Unicordoba.Registro_Control.Base_de_Datos.Entity.Curso;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.RegistroAsistenciaDocente;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Sede;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-02T15:13:43")
@StaticMetamodel(Salon.class)
public class Salon_ { 

    public static volatile ListAttribute<Salon, RegistroAsistenciaDocente> registroAsistenciaDocenteList;
    public static volatile SingularAttribute<Salon, String> ubucacion;
    public static volatile SingularAttribute<Salon, Integer> numero;
    public static volatile SingularAttribute<Salon, Sede> sedeid;
    public static volatile SingularAttribute<Salon, Integer> capacidadPersonas;
    public static volatile SingularAttribute<Salon, Integer> id;
    public static volatile ListAttribute<Salon, Curso> cursoList;

}