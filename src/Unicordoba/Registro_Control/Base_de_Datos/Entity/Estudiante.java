/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unicordoba.Registro_Control.Base_de_Datos.Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author AndresFelipe
 */
@Entity
@Table(name = "estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e"),
    @NamedQuery(name = "Estudiante.findByIdEstudiante", query = "SELECT e FROM Estudiante e WHERE e.idEstudiante = :idEstudiante")})
public class Estudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEstudiante")
    private Integer idEstudiante;
    @JoinTable(name = "estudiante_has_materia", joinColumns = {
        @JoinColumn(name = "Estudiante_idEstudiante", referencedColumnName = "idEstudiante")}, inverseJoinColumns = {
        @JoinColumn(name = "Materia_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Materia> materiaList;
    @JoinColumn(name = "Sede_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Sede sedeid;
    @JoinColumn(name = "Informacion_De_Seguridad_idI", referencedColumnName = "idI")
    @ManyToOne(optional = false)
    private InformacionDeSeguridad informacionDeSeguridadidI;
    @JoinColumn(name = "Informacion_Basica_Id", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private InformacionBasica informacionBasicaId;

    public Estudiante() {
    }

    public Estudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    @XmlTransient
    public List<Materia> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    public Sede getSedeid() {
        return sedeid;
    }

    public void setSedeid(Sede sedeid) {
        this.sedeid = sedeid;
    }

    public InformacionDeSeguridad getInformacionDeSeguridadidI() {
        return informacionDeSeguridadidI;
    }

    public void setInformacionDeSeguridadidI(InformacionDeSeguridad informacionDeSeguridadidI) {
        this.informacionDeSeguridadidI = informacionDeSeguridadidI;
    }

    public InformacionBasica getInformacionBasicaId() {
        return informacionBasicaId;
    }

    public void setInformacionBasicaId(InformacionBasica informacionBasicaId) {
        this.informacionBasicaId = informacionBasicaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstudiante != null ? idEstudiante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.idEstudiante == null && other.idEstudiante != null) || (this.idEstudiante != null && !this.idEstudiante.equals(other.idEstudiante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Unicordoba.Registro_Control.Base_de_Datos.Entity.Estudiante[ idEstudiante=" + idEstudiante + " ]";
    }
    
}
