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
@Table(name = "curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findById", query = "SELECT c FROM Curso c WHERE c.id = :id")})
public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @ManyToMany(mappedBy = "cursoList")
    private List<Materia> materiaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cursoid")
    private List<RegistroAsistenciaDocente> registroAsistenciaDocenteList;
    @JoinColumn(name = "Programa_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Programa programaid;
    @JoinColumn(name = "Salon_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Salon salonid;

    public Curso() {
    }

    public Curso(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public List<Materia> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    @XmlTransient
    public List<RegistroAsistenciaDocente> getRegistroAsistenciaDocenteList() {
        return registroAsistenciaDocenteList;
    }

    public void setRegistroAsistenciaDocenteList(List<RegistroAsistenciaDocente> registroAsistenciaDocenteList) {
        this.registroAsistenciaDocenteList = registroAsistenciaDocenteList;
    }

    public Programa getProgramaid() {
        return programaid;
    }

    public void setProgramaid(Programa programaid) {
        this.programaid = programaid;
    }

    public Salon getSalonid() {
        return salonid;
    }

    public void setSalonid(Salon salonid) {
        this.salonid = salonid;
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
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Unicordoba.Registro_Control.Base_de_Datos.Entity.Curso[ id=" + id + " ]";
    }
    
}
