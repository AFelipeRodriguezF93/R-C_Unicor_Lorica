package Unicordoba.Registro_Control.Base_de_Datos.Entity;

import Unicordoba.Registro_Control.Base_de_Datos.Entity.Dinamizador;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Docente;
import Unicordoba.Registro_Control.Base_de_Datos.Entity.Estudiante;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-02T15:13:43")
@StaticMetamodel(InformacionBasica.class)
public class InformacionBasica_ { 

    public static volatile SingularAttribute<InformacionBasica, String> apellidos;
    public static volatile ListAttribute<InformacionBasica, Dinamizador> dinamizadorList;
    public static volatile SingularAttribute<InformacionBasica, String> codigo;
    public static volatile SingularAttribute<InformacionBasica, Integer> tiCc;
    public static volatile SingularAttribute<InformacionBasica, String> correo;
    public static volatile ListAttribute<InformacionBasica, Estudiante> estudianteList;
    public static volatile SingularAttribute<InformacionBasica, Integer> id;
    public static volatile SingularAttribute<InformacionBasica, Integer> telefono;
    public static volatile ListAttribute<InformacionBasica, Docente> docenteList;
    public static volatile SingularAttribute<InformacionBasica, String> nombres;

}