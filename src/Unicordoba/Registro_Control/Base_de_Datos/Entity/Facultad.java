/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unicordoba.Registro_Control.Base_de_Datos.Entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author AndresFelipe
 */
@Entity
@Table(name = "facultad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facultad.findAll", query = "SELECT f FROM Facultad f"),
    @NamedQuery(name = "Facultad.findById", query = "SELECT f FROM Facultad f WHERE f.id = :id"),
    @NamedQuery(name = "Facultad.findByNombre", query = "SELECT f FROM Facultad f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "Facultad.findByDecano", query = "SELECT f FROM Facultad f WHERE f.decano = :decano"),
    @NamedQuery(name = "Facultad.findByUbicacion", query = "SELECT f FROM Facultad f WHERE f.ubicacion = :ubicacion"),
    @NamedQuery(name = "Facultad.findByCodigo", query = "SELECT f FROM Facultad f WHERE f.codigo = :codigo")})
public class Facultad implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
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
    @Column(name = "Decano")
    private String decano;
    @Basic(optional = false)
    @Column(name = "Ubicacion")
    private String ubicacion;
    @Column(name = "Codigo")
    private Integer codigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultadid")
    private List<Docente> docenteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultadid")
    private List<Programa> programaList;
    @JoinColumn(name = "Universidad_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Universidad universidadid;

    public Facultad() {
    }

    public Facultad(Integer id) {
        this.id = id;
    }

    public Facultad(Integer id, String nombre, String decano, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.decano = decano;
        this.ubicacion = ubicacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        String oldNombre = this.nombre;
        this.nombre = nombre;
        changeSupport.firePropertyChange("nombre", oldNombre, nombre);
    }

    public String getDecano() {
        return decano;
    }

    public void setDecano(String decano) {
        String oldDecano = this.decano;
        this.decano = decano;
        changeSupport.firePropertyChange("decano", oldDecano, decano);
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        String oldUbicacion = this.ubicacion;
        this.ubicacion = ubicacion;
        changeSupport.firePropertyChange("ubicacion", oldUbicacion, ubicacion);
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        Integer oldCodigo = this.codigo;
        this.codigo = codigo;
        changeSupport.firePropertyChange("codigo", oldCodigo, codigo);
    }

    @XmlTransient
    public List<Docente> getDocenteList() {
        return docenteList;
    }

    public void setDocenteList(List<Docente> docenteList) {
        this.docenteList = docenteList;
    }

    @XmlTransient
    public List<Programa> getProgramaList() {
        return programaList;
    }

    public void setProgramaList(List<Programa> programaList) {
        this.programaList = programaList;
    }

    public Universidad getUniversidadid() {
        return universidadid;
    }

    public void setUniversidadid(Universidad universidadid) {
        Universidad oldUniversidadid = this.universidadid;
        this.universidadid = universidadid;
        changeSupport.firePropertyChange("universidadid", oldUniversidadid, universidadid);
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
        if (!(object instanceof Facultad)) {
            return false;
        }
        Facultad other = (Facultad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Unicordoba.Registro_Control.Base_de_Datos.Entity.Facultad[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
