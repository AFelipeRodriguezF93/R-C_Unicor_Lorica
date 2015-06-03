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
@Table(name = "sede")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sede.findAll", query = "SELECT s FROM Sede s"),
    @NamedQuery(name = "Sede.findById", query = "SELECT s FROM Sede s WHERE s.id = :id"),
    @NamedQuery(name = "Sede.findByNombre", query = "SELECT s FROM Sede s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Sede.findByUbicacion", query = "SELECT s FROM Sede s WHERE s.ubicacion = :ubicacion"),
    @NamedQuery(name = "Sede.findByTelefono", query = "SELECT s FROM Sede s WHERE s.telefono = :telefono")})
public class Sede implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "Ubicacion")
    private String ubicacion;
    @Basic(optional = false)
    @Column(name = "Telefono")
    private int telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sedeid")
    private List<Estudiante> estudianteList;
    @JoinColumn(name = "Universidad_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Universidad universidadid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sedeid")
    private List<Dinamizador> dinamizadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sedeid")
    private List<Salon> salonList;

    public Sede() {
    }

    public Sede(Integer id) {
        this.id = id;
    }

    public Sede(Integer id, String nombre, String ubicacion, int telefono) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public List<Estudiante> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudianteList(List<Estudiante> estudianteList) {
        this.estudianteList = estudianteList;
    }

    public Universidad getUniversidadid() {
        return universidadid;
    }

    public void setUniversidadid(Universidad universidadid) {
        this.universidadid = universidadid;
    }

    @XmlTransient
    public List<Dinamizador> getDinamizadorList() {
        return dinamizadorList;
    }

    public void setDinamizadorList(List<Dinamizador> dinamizadorList) {
        this.dinamizadorList = dinamizadorList;
    }

    @XmlTransient
    public List<Salon> getSalonList() {
        return salonList;
    }

    public void setSalonList(List<Salon> salonList) {
        this.salonList = salonList;
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
        if (!(object instanceof Sede)) {
            return false;
        }
        Sede other = (Sede) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Unicordoba.Registro_Control.Base_de_Datos.Entity.Sede[ id=" + id + " ]";
    }
    
}
