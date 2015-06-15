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
@Table(name = "informacion_basica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InformacionBasica.findAll", query = "SELECT i FROM InformacionBasica i"),
    @NamedQuery(name = "InformacionBasica.findById", query = "SELECT i FROM InformacionBasica i WHERE i.id = :id"),
    @NamedQuery(name = "InformacionBasica.findByNombres", query = "SELECT i FROM InformacionBasica i WHERE i.nombres = :nombres"),
    @NamedQuery(name = "InformacionBasica.findByApellidos", query = "SELECT i FROM InformacionBasica i WHERE i.apellidos = :apellidos"),
    @NamedQuery(name = "InformacionBasica.findByCodigo", query = "SELECT i FROM InformacionBasica i WHERE i.codigo = :codigo"),
    @NamedQuery(name = "InformacionBasica.findByCorreo", query = "SELECT i FROM InformacionBasica i WHERE i.correo = :correo"),
    @NamedQuery(name = "InformacionBasica.findByTiCc", query = "SELECT i FROM InformacionBasica i WHERE i.tiCc = :tiCc"),
    @NamedQuery(name = "InformacionBasica.findByTelefono", query = "SELECT i FROM InformacionBasica i WHERE i.telefono = :telefono")})
public class InformacionBasica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Nombres")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "Apellidos")
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "Codigo")
    private String codigo;
    @Basic(optional = false)
    @Column(name = "Correo")
    private String correo;
    @Basic(optional = false)
    @Column(name = "TI_CC")
    private int tiCc;
    @Basic(optional = false)
    @Column(name = "Telefono")
    private String telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "informacionBasicaId")
    private List<Estudiante> estudianteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "informacionBasicaId")
    private List<Docente> docenteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "informacionBasicaId")
    private List<Dinamizador> dinamizadorList;

    public InformacionBasica() {
    }

    public InformacionBasica(Integer id) {
        this.id = id;
    }

    public InformacionBasica(Integer id, String nombres, String apellidos, String codigo, String correo, int tiCc, String telefono) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.codigo = codigo;
        this.correo = correo;
        this.tiCc = tiCc;
        this.telefono = telefono;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getTiCc() {
        return tiCc;
    }

    public void setTiCc(int tiCc) {
        this.tiCc = tiCc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformacionBasica)) {
            return false;
        }
        InformacionBasica other = (InformacionBasica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Unicordoba.Registro_Control.Base_de_Datos.Entity.InformacionBasica[ id=" + id + " ]";
    }
    
}
