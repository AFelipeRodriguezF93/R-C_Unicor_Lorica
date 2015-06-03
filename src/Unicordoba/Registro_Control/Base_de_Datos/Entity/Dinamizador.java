/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unicordoba.Registro_Control.Base_de_Datos.Entity;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AndresFelipe
 */
@Entity
@Table(name = "dinamizador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dinamizador.findAll", query = "SELECT d FROM Dinamizador d"),
    @NamedQuery(name = "Dinamizador.findByIdDinamizador", query = "SELECT d FROM Dinamizador d WHERE d.idDinamizador = :idDinamizador")})
public class Dinamizador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDinamizador")
    private Integer idDinamizador;
    @JoinColumn(name = "Sede_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Sede sedeid;
    @JoinColumn(name = "Informacion_Basica_Id", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private InformacionBasica informacionBasicaId;
    @JoinColumn(name = "Informacion_De_Seguridad_idI", referencedColumnName = "idI")
    @ManyToOne(optional = false)
    private InformacionDeSeguridad informacionDeSeguridadidI;

    public Dinamizador() {
    }

    public Dinamizador(Integer idDinamizador) {
        this.idDinamizador = idDinamizador;
    }

    public Integer getIdDinamizador() {
        return idDinamizador;
    }

    public void setIdDinamizador(Integer idDinamizador) {
        this.idDinamizador = idDinamizador;
    }

    public Sede getSedeid() {
        return sedeid;
    }

    public void setSedeid(Sede sedeid) {
        this.sedeid = sedeid;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDinamizador != null ? idDinamizador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dinamizador)) {
            return false;
        }
        Dinamizador other = (Dinamizador) object;
        if ((this.idDinamizador == null && other.idDinamizador != null) || (this.idDinamizador != null && !this.idDinamizador.equals(other.idDinamizador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Unicordoba.Registro_Control.Base_de_Datos.Entity.Dinamizador[ idDinamizador=" + idDinamizador + " ]";
    }
    
}
