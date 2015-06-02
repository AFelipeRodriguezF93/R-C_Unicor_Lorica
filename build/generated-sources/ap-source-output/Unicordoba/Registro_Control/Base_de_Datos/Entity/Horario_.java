package Unicordoba.Registro_Control.Base_de_Datos.Entity;

import Unicordoba.Registro_Control.Base_de_Datos.Entity.Materia;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-02T15:13:43")
@StaticMetamodel(Horario.class)
public class Horario_ { 

    public static volatile SingularAttribute<Horario, Materia> materiaid;
    public static volatile SingularAttribute<Horario, Integer> id;
    public static volatile SingularAttribute<Horario, String> dia;
    public static volatile SingularAttribute<Horario, String> horaInicio;
    public static volatile SingularAttribute<Horario, String> horaSalida;

}