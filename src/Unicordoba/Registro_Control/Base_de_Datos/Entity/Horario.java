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
@Table(name = "horario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h"),
    @NamedQuery(name = "Horario.findById", query = "SELECT h FROM Horario h WHERE h.id = :id"),
    @NamedQuery(name = "Horario.findByHoraInicio", query = "SELECT h FROM Horario h WHERE h.horaInicio = :horaInicio"),
    @NamedQuery(name = "Horario.findByHoraSalida", query = "SELECT h FROM Horario h WHERE h.horaSalida = :horaSalida"),
    @NamedQuery(name = "Horario.findByDia", query = "SELECT h FROM Horario h WHERE h.dia = :dia")})
public class Horario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Hora_Inicio")
    private String horaInicio;
    @Basic(optional = false)
    @Column(name = "Hora_Salida")
    private String horaSalida;
    @Basic(optional = false)
    @Column(name = "Dia")
    private String dia;
    @JoinColumn(name = "Materia_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Materia materiaid;

    public Horario() {
    }

    public Horario(Integer id) {
        this.id = id;
    }

    public Horario(Integer id, String horaInicio, String horaSalida, String dia) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaSalida = horaSalida;
        this.dia = dia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Materia getMateriaid() {
        return materiaid;
    }

    public void setMateriaid(Materia materiaid) {
        this.materiaid = materiaid;
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
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Unicordoba.Registro_Control.Base_de_Datos.Entity.Horario[ id=" + id + " ]";
    }
    
}
