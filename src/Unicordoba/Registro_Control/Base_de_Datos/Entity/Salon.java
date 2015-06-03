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
@Table(name = "salon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salon.findAll", query = "SELECT s FROM Salon s"),
    @NamedQuery(name = "Salon.findById", query = "SELECT s FROM Salon s WHERE s.id = :id"),
    @NamedQuery(name = "Salon.findByNumero", query = "SELECT s FROM Salon s WHERE s.numero = :numero"),
    @NamedQuery(name = "Salon.findByCapacidadPersonas", query = "SELECT s FROM Salon s WHERE s.capacidadPersonas = :capacidadPersonas"),
    @NamedQuery(name = "Salon.findByUbucacion", query = "SELECT s FROM Salon s WHERE s.ubucacion = :ubucacion")})
public class Salon implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Numero")
    private int numero;
    @Basic(optional = false)
    @Column(name = "Capacidad_Personas")
    private int capacidadPersonas;
    @Basic(optional = false)
    @Column(name = "Ubucacion")
    private String ubucacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salonid")
    private List<RegistroAsistenciaDocente> registroAsistenciaDocenteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salonid")
    private List<Curso> cursoList;
    @JoinColumn(name = "Sede_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Sede sedeid;

    public Salon() {
    }

    public Salon(Integer id) {
        this.id = id;
    }

    public Salon(Integer id, int numero, int capacidadPersonas, String ubucacion) {
        this.id = id;
        this.numero = numero;
        this.capacidadPersonas = capacidadPersonas;
        this.ubucacion = ubucacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacidadPersonas() {
        return capacidadPersonas;
    }

    public void setCapacidadPersonas(int capacidadPersonas) {
        this.capacidadPersonas = capacidadPersonas;
    }

    public String getUbucacion() {
        return ubucacion;
    }

    public void setUbucacion(String ubucacion) {
        this.ubucacion = ubucacion;
    }

    @XmlTransient
    public List<RegistroAsistenciaDocente> getRegistroAsistenciaDocenteList() {
        return registroAsistenciaDocenteList;
    }

    public void setRegistroAsistenciaDocenteList(List<RegistroAsistenciaDocente> registroAsistenciaDocenteList) {
        this.registroAsistenciaDocenteList = registroAsistenciaDocenteList;
    }

    @XmlTransient
    public List<Curso> getCursoList() {
        return cursoList;
    }

    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    public Sede getSedeid() {
        return sedeid;
    }

    public void setSedeid(Sede sedeid) {
        this.sedeid = sedeid;
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
        if (!(object instanceof Salon)) {
            return false;
        }
        Salon other = (Salon) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Unicordoba.Registro_Control.Base_de_Datos.Entity.Salon[ id=" + id + " ]";
    }
    
}
