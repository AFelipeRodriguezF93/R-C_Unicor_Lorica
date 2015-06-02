/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unicordoba.Registro_Control.Interfaz_Secundaria.Materia;

import Unicordoba.Registro_Control.Interfaz_Secundaria.Basica.IPanelEdicion;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author AndresFelipe
 */
public class PMateria extends javax.swing.JPanel implements IPanelEdicion {

    /**
     * Creates new form PMateria
     */
    public PMateria() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        LNombreMateria = new javax.swing.JLabel();
        LCodigo = new javax.swing.JLabel();
        LSemestre = new javax.swing.JLabel();
        LContenido = new javax.swing.JLabel();
        LCantidadCreditos = new javax.swing.JLabel();
        LProgramaAcedimico = new javax.swing.JLabel();
        TFieldNombreMateria = new javax.swing.JTextField();
        TFieldCodigo = new javax.swing.JTextField();
        ScrollPanelContenido = new javax.swing.JScrollPane();
        TAreaContenido = new javax.swing.JTextArea();
        CBSemestre = new javax.swing.JComboBox();
        CBCantidadCreditos = new javax.swing.JComboBox();
        CBListaProgramas = new javax.swing.JComboBox();

        setLayout(new java.awt.GridBagLayout());

        LNombreMateria.setText("Nombre de la Materia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 10, 0, 0);
        add(LNombreMateria, gridBagConstraints);

        LCodigo.setText("Codigo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 10, 0, 0);
        add(LCodigo, gridBagConstraints);

        LSemestre.setText("Semestre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 10, 0, 0);
        add(LSemestre, gridBagConstraints);

        LContenido.setText("Contenido:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 10, 0, 0);
        add(LContenido, gridBagConstraints);

        LCantidadCreditos.setText("Cantidad de Credito:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 133, 0, 0);
        add(LCantidadCreditos, gridBagConstraints);

        LProgramaAcedimico.setText("Programa Academico:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 10, 0, 0);
        add(LProgramaAcedimico, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(22, 18, 0, 10);
        add(TFieldNombreMateria, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 0, 10);
        add(TFieldCodigo, gridBagConstraints);

        TAreaContenido.setColumns(20);
        TAreaContenido.setRows(5);
        ScrollPanelContenido.setViewportView(TAreaContenido);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 357;
        gridBagConstraints.ipady = 73;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 21, 10);
        add(ScrollPanelContenido, gridBagConstraints);

        CBSemestre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Primer Semestre", "Segundo Semestre", "Tercer Semestre", "Cuarto Semestre", "Quinto Semestre", "Sexto Semestre", "Septimo Semestre", "Octavo Semestre", "Noveno Semestre", "Decimo Semestre" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 146, 0, 10);
        add(CBSemestre, gridBagConstraints);

        CBCantidadCreditos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 10, 0, 10);
        add(CBCantidadCreditos, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 228;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 0, 10);
        add(CBListaProgramas, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CBCantidadCreditos;
    private javax.swing.JComboBox CBListaProgramas;
    private javax.swing.JComboBox CBSemestre;
    private javax.swing.JLabel LCantidadCreditos;
    private javax.swing.JLabel LCodigo;
    private javax.swing.JLabel LContenido;
    private javax.swing.JLabel LNombreMateria;
    private javax.swing.JLabel LProgramaAcedimico;
    private javax.swing.JLabel LSemestre;
    private javax.swing.JScrollPane ScrollPanelContenido;
    private javax.swing.JTextArea TAreaContenido;
    private javax.swing.JTextField TFieldCodigo;
    private javax.swing.JTextField TFieldNombreMateria;
    // End of variables declaration//GEN-END:variables

    @Override
    public void Guardar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ActivarEdicion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Nuevo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Seleccionar(Vector vectorSeleccion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object[]> getListaParaTabla() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getNombreDeColumnas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
