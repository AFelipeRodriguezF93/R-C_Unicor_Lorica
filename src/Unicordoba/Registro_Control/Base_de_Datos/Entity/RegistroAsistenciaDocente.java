/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unicordoba.Registro_Control.Base_de_Datos.Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AndresFelipe
 */
@Entity
@Table(name = "registro_asistencia_docente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroAsistenciaDocente.findAll", query = "SELECT r FROM RegistroAsistenciaDocente r"),
    @NamedQuery(name = "RegistroAsistenciaDocente.findById", query = "SELECT r FROM RegistroAsistenciaDocente r WHERE r.id = :id"),
    @NamedQuery(name = "RegistroAsistenciaDocente.findByFecha", query = "SELECT r FROM RegistroAsistenciaDocente r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "RegistroAsistenciaDocente.findByTipoAsistencia", query = "SELECT r FROM RegistroAsistenciaDocente r WHERE r.tipoAsistencia = :tipoAsistencia")})
public class RegistroAsistenciaDocente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "Tipo_Asistencia")
    private String tipoAsistencia;
    @JoinColumn(name = "Docente_idDocente", referencedColumnName = "idDocente")
    @ManyToOne(optional = false)
    private Docente docenteidDocente;
    @JoinColumn(name = "Salon_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Salon salonid;
    @JoinColumn(name = "Curso_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Curso cursoid;
    @JoinColumn(name = "Utensilios_De_Clase_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UtensiliosDeClase utensiliosDeClaseid;

    public RegistroAsistenciaDocente() {
    }

    public RegistroAsistenciaDocente(Integer id) {
        this.id = id;
    }

    public RegistroAsistenciaDocente(Integer id, Date fecha, String tipoAsistencia) {
        this.id = id;
        this.fecha = fecha;
        this.tipoAsistencia = tipoAsistencia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoAsistencia() {
        return tipoAsistencia;
    }

    public void setTipoAsistencia(String tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }

    public Docente getDocenteidDocente() {
        return docenteidDocente;
    }

    public void setDocenteidDocente(Docente docenteidDocente) {
        this.docenteidDocente = docenteidDocente;
    }

    public Salon getSalonid() {
        return salonid;
    }

    public void setSalonid(Salon salonid) {
        this.salonid = salonid;
    }

    public Curso getCursoid() {
        return cursoid;
    }

    public void setCursoid(Curso cursoid) {
        this.cursoid = cursoid;
    }

    public UtensiliosDeClase getUtensiliosDeClaseid() {
        return utensiliosDeClaseid;
    }

    public void setUtensiliosDeClaseid(UtensiliosDeClase utensiliosDeClaseid) {
        this.utensiliosDeClaseid = utensiliosDeClaseid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroAsistenciaDocente)) {
            return false;
        }
        RegistroAsistenciaDocente other = (RegistroAsistenciaDocente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Unicordoba.Registro_Control.Base_de_Datos.Entity.RegistroAsistenciaDocente[ id=" + id + " ]";
    }
    
}
