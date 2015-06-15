/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unicordoba.Registro_Control.Interfaz_Secundaria.BasicaUno;

import java.util.List;
import java.util.Vector;

/**
 *
 * @author AndresFelipe
 */
public interface IPanelEdicion {    
    public void Guardar(Estado_Ventana estado_Ventana);
    public void Eliminar();
    public void ActivarEdicion();
    public void Nuevo();
    public void Seleccionar(Vector vectorSeleccion);
    public List<Object[]> getListaParaTabla();
    public String[] getNombreDeColumnas();     
}
