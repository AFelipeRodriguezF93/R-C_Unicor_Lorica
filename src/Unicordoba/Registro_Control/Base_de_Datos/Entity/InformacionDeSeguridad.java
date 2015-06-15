/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unicordoba.Registro_Control.Base_de_Datos.Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author AndresFelipe
 */
@Entity
@Table(name = "informacion_de_seguridad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InformacionDeSeguridad.findAll", query = "SELECT i FROM InformacionDeSeguridad i"),
    @NamedQuery(name = "InformacionDeSeguridad.findByIdI", query = "SELECT i FROM InformacionDeSeguridad i WHERE i.idI = :idI"),
    @NamedQuery(name = "InformacionDeSeguridad.findByCodigoHuella", query = "SELECT i FROM InformacionDeSeguridad i WHERE i.codigoHuella = :codigoHuella"),
    @NamedQuery(name = "InformacionDeSeguridad.findByClave", query = "SELECT i FROM InformacionDeSeguridad i WHERE i.clave = :clave"),
    @NamedQuery(name = "InformacionDeSeguridad.findByEstado", query = "SELECT i FROM InformacionDeSeguridad i WHERE i.estado = :estado")})
public class InformacionDeSeguridad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idI")
    private Integer idI;
    @Basic(optional = false)
    @Column(name = "Codigo_Huella")
    private String codigoHuella;
    @Basic(optional = false)
    @Column(name = "Clave")
    private String clave;
    @Basic(optional = false)
    @Column(name = "Estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "informacionDeSeguridadidI")
    private List<Estudiante> estudianteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "informacionDeSeguridadidI")
    private List<Docente> docenteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "informacionDeSeguridadidI")
    private List<Dinamizador> dinamizadorList;

    public InformacionDeSeguridad() {
    }

    public InformacionDeSeguridad(Integer idI) {
        this.idI = idI;
    }

    public InformacionDeSeguridad(Integer idI, String codigoHuella, String clave, String estado) {
        this.idI = idI;
        this.codigoHuella = codigoHuella;
        this.clave = clave;
        this.estado = estado;
    }

    public Integer getIdI() {
        return idI;
    }

    public void setIdI(Integer idI) {
        this.idI = idI;
    }

    public String getCodigoHuella() {
        return codigoHuella;
    }

    public void setCodigoHuella(String codigoHuella) {
        this.codigoHuella = codigoHuella;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Estudiante> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudianteList(List<Estudiante> estudianteList) {
        this.estudianteList = estudianteList;
    }

    @XmlTransient
    public List<Docente> getDocenteList() {
        return docenteList;
    }

    public void setDocenteList(List<Docente> docenteList) {
        this.docenteList = docenteList;
    }

    @XmlTransient
    public List<Dinamizador> getDinamizadorList() {
        return dinamizadorList;
    }

    public void setDinamizadorList(List<Dinamizador> dinamizadorList) {
        this.dinamizadorList = dinamizadorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idI != null ? idI.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformacionDeSeguridad)) {
            return false;
        }
        InformacionDeSeguridad other = (InformacionDeSeguridad) object;
        if ((this.idI == null && other.idI != null) || (this.idI != null && !this.idI.equals(other.idI))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Unicordoba.Registro_Control.Base_de_Datos.Entity.InformacionDeSeguridad[ idI=" + idI + " ]";
    }
    
}
