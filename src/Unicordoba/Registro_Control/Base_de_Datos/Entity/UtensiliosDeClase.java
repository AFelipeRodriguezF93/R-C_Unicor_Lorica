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
@Table(name = "utensilios_de_clase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UtensiliosDeClase.findAll", query = "SELECT u FROM UtensiliosDeClase u"),
    @NamedQuery(name = "UtensiliosDeClase.findById", query = "SELECT u FROM UtensiliosDeClase u WHERE u.id = :id"),
    @NamedQuery(name = "UtensiliosDeClase.findByCodigo", query = "SELECT u FROM UtensiliosDeClase u WHERE u.codigo = :codigo"),
    @NamedQuery(name = "UtensiliosDeClase.findByNombre", query = "SELECT u FROM UtensiliosDeClase u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "UtensiliosDeClase.findByListaAccesorios", query = "SELECT u FROM UtensiliosDeClase u WHERE u.listaAccesorios = :listaAccesorios")})
public class UtensiliosDeClase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Codigo")
    private int codigo;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "Lista_Accesorios")
    private String listaAccesorios;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utensiliosDeClaseid")
    private List<RegistroAsistenciaDocente> registroAsistenciaDocenteList;

    public UtensiliosDeClase() {
    }

    public UtensiliosDeClase(Integer id) {
        this.id = id;
    }

    public UtensiliosDeClase(Integer id, int codigo, String nombre, String listaAccesorios) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.listaAccesorios = listaAccesorios;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getListaAccesorios() {
        return listaAccesorios;
    }

    public void setListaAccesorios(String listaAccesorios) {
        this.listaAccesorios = listaAccesorios;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UtensiliosDeClase)) {
            return false;
        }
        UtensiliosDeClase other = (UtensiliosDeClase) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Unicordoba.Registro_Control.Base_de_Datos.Entity.UtensiliosDeClase[ id=" + id + " ]";
    }
    
}
