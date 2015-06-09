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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "docente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Docente.findAll", query = "SELECT d FROM Docente d"),
    @NamedQuery(name = "Docente.findByIdDocente", query = "SELECT d FROM Docente d WHERE d.idDocente = :idDocente")})
public class Docente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDocente")
    private Integer idDocente;
    @JoinTable(name = "docente_has_materia", joinColumns = {
        @JoinColumn(name = "Docente_idDocente", referencedColumnName = "idDocente")}, inverseJoinColumns = {
        @JoinColumn(name = "Materia_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Materia> materiaList;
    @JoinColumn(name = "Facultad_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Facultad facultadid;
    @JoinColumn(name = "Informacion_Basica_Id", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private InformacionBasica informacionBasicaId;
    @JoinColumn(name = "Informacion_De_Seguridad_idI", referencedColumnName = "idI")
    @ManyToOne(optional = false)
    private InformacionDeSeguridad informacionDeSeguridadidI;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "docenteidDocente")
    private List<RegistroAsistenciaDocente> registroAsistenciaDocenteList;

    public Docente() {
    }

    public Docente(Integer idDocente) {
        this.idDocente = idDocente;
    }

    public Integer getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Integer idDocente) {
        this.idDocente = idDocente;
    }

    @XmlTransient
    public List<Materia> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    public Facultad getFacultadid() {
        return facultadid;
    }

    public void setFacultadid(Facultad facultadid) {
        this.facultadid = facultadid;
    }

    public InformacionBasica getInformacionBasicaId() {
        return informacionBasicaId;
    }

    public void setInformacionBasicaId(InformacionBasica informacionBasicaId) {
        this.informacionBasicaId = informacionBasicaId;
    }

    public InformacionDeSeguridad getInformacionDeSeguridadidI() {
        return informacionDeSeguridadidI;
    }

    public void setInformacionDeSeguridadidI(InformacionDeSeguridad informacionDeSeguridadidI) {
        this.informacionDeSeguridadidI = informacionDeSeguridadidI;
    }

    @XmlTransient
    public List<RegistroAsistenciaDocente> getRegistroAsistenciaDocenteList() {
        return registroAsistenciaDocenteList;
    }

    public void setRegistroAsistenciaDocenteList(List<RegistroAsistenciaDocente> registroAsistenciaDocenteList) {
        this.registroAsistenciaDocenteList = registroAsistenciaDocenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocente != null ? idDocente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docente)) {
            return false;
        }
        Docente other = (Docente) object;
        if ((this.idDocente == null && other.idDocente != null) || (this.idDocente != null && !this.idDocente.equals(other.idDocente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Unicordoba.Registro_Control.Base_de_Datos.Entity.Docente[ idDocente=" + idDocente + " ]";
    }
    
}
