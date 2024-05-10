
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.*;
import Vista.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ControladorEstudiante implements ActionListener{
    
    Estudiante mod;
    dbEstudiante db;
    dlg_Estudiantes vista;
    dlg_Menu vista2;
    boolean hab;
    boolean act = false;
    
    public ControladorEstudiante(Estudiante mod, dbEstudiante db, dlg_Estudiantes vista, dlg_Menu vista2) {
        this.mod = mod;
        this.db = db;
        this.vista = vista;
        this.vista2 = vista2;
        
        vista.btnAgregaNuevoestu.addActionListener(this);
        vista.btnBuscarestu.addActionListener(this);
        vista.btnCancelarEstu.addActionListener(this);
        vista.btnDeshabilitarEstu.addActionListener(this);
        vista.btnLImpiarestu.addActionListener(this);
        vista.btnNuevoestu.addActionListener(this);
        vista.btnVolverestu.addActionListener(this);
        
        vista.cmbCarreraEstu.addActionListener(this);
    }
    
    void iniciarVista() throws Exception{
        vista.setTitle(":: Estudiante ::");
        vista.setSize(476, 519);
        vista.setVisible(true);     
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnNuevoestu){
            hab = true;
            vista.txtMatriculaestu.setEnabled(hab);
            vista.txtNombreEstu.setEnabled(hab);
            vista.txtcorreoestu.setEnabled(hab);
            vista.txtTelefonoestu.setEnabled(hab);
            vista.txtAdeudoEstu.setEnabled(hab);
            
            vista.btnLImpiarestu.setEnabled(hab);
            vista.btnAgregaNuevoestu.setEnabled(hab);
            vista.btnCancelarEstu.setEnabled(hab);
            vista.btnDeshabilitarEstu.setEnabled(hab);
            
            vista.cmbCarreraEstu.setEnabled(hab);
            
        }
        else if(e.getSource()==vista.btnAgregaNuevoestu){
            if(valiEmpty()!=true){
                JOptionPane.showMessageDialog(vista,"No deje ningun espacio vacio");
            }
            else{
                if(act !=true){
                    try {
                        mod = (Estudiante) db.buscar(vista.txtMatriculaestu.getText());
                        if (!mod.getMatricula().equals("")) {
                            JOptionPane.showMessageDialog(vista, "Esa matricula ya está registrada");
                        } else {
                            mod.setMatricula(vista.txtMatriculaestu.getText());
                            mod.setNombre(vista.txtNombreEstu.getText());
                            mod.setCorreo(vista.txtcorreoestu.getText());
                            mod.setTelefono(vista.txtTelefonoestu.getText());
                            mod.setAdeudo(Float.parseFloat(vista.txtAdeudoEstu.getText()));
                            Carrera();
                            db.insertar(mod);
                            limpiar();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vista, "Ocurrio un error al insertar un estudiante: " + ex.getMessage());
                    }
                }
                else{
                    try {
                        mod = (Estudiante) db.buscar(vista.txtMatriculaestu.getText());
                        if (!mod.getMatricula().equals("")) {
                            JOptionPane.showMessageDialog(vista, "Esa matricula ya está registrada");
                        } else {
                            mod.setMatricula(vista.txtMatriculaestu.getText());
                            mod.setNombre(vista.txtNombreEstu.getText());
                            mod.setCorreo(vista.txtcorreoestu.getText());
                            mod.setTelefono(vista.txtTelefonoestu.getText());
                            mod.setAdeudo(Float.parseFloat(vista.txtAdeudoEstu.getText()));
                            Carrera();
                            db.actualizar(mod);
                            limpiar();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vista, "Ocurrio un error al actualizar un estudiante: " + ex.getMessage());
                    }
                }     
            }            
        }
        else if(e.getSource()==vista.btnBuscarestu){
            if(valiEmptyBusc()!=true){
                JOptionPane.showMessageDialog(vista,"Porfavor ingrese una matricula antes de buscar");
            }
            else{
                try {
                    mod = (Estudiante) db.buscar(vista.txtMatriculaestu.getText());
                    if(mod.getMatricula().equals("")){
                        JOptionPane.showMessageDialog(vista,"Esa matricula no es valida");
                    }
                    else{
                        vista.txtNombreEstu.setText(mod.getNombre());
                        vista.txtcorreoestu.setText(mod.getCorreo());
                        vista.txtTelefonoestu.setText(mod.getTelefono());
                        vista.txtAdeudoEstu.setText(String.valueOf(mod.getAdeudo()));
                        CarreraBusc();
                        act=true;
                    }
                    
                } catch (Exception ex) {
                    Logger.getLogger(ControladorEstudiante.class.getName()).log(Level.SEVERE, null, ex);
                }
            }    
        }
        else if(e.getSource()==vista.btnDeshabilitarEstu){
            if(valiEmptyBusc()!=true){
                JOptionPane.showMessageDialog(vista,"Porfavor ingrese una matricula antes de deshabilitar");
            }
            else{
                try {
                    mod = (Estudiante) db.buscar(vista.txtMatriculaestu.getText());
                    if (mod.getMatricula().equals("")) {
                        JOptionPane.showMessageDialog(vista, "Esa matricula no es valida");
                    }
                    else{
                        db.deshabilitar(vista.txtMatriculaestu.getText());
                    }
                } catch (Exception ex) {
                    Logger.getLogger(ControladorEstudiante.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        
        else if(e.getSource()==vista.btnLImpiarestu){
            vista.txtNombreEstu.setText("");
            vista.txtcorreoestu.setText("");
            vista.cmbCarreraEstu.setSelectedIndex(0);
            vista.txtTelefonoestu.setText("");
            vista.txtAdeudoEstu.setText("");
        }
        else if(e.getSource()==vista.btnCancelarEstu){
            hab = false;
            vista.txtNombreEstu.setEnabled(hab);
            vista.txtcorreoestu.setEnabled(hab);
            vista.cmbCarreraEstu.setEnabled(hab);
            vista.txtTelefonoestu.setEnabled(hab);
            vista.txtAdeudoEstu.setEnabled(hab);
            vista.btnLImpiarestu.setEnabled(hab);
            vista.btnAgregaNuevoestu.setEnabled(hab);
            vista.btnCancelarEstu.setEnabled(hab);
        }
        else if (e.getSource() == vista.btnVolverestu) {
            if (JOptionPane.showConfirmDialog(vista, "Seguro que desea volver al menu?", "Cerrar", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            } else {
                vista.setVisible(false);
                vista.dispose();
                vista2.setTitle(":: Menu ::");
                vista2.setSize(277, 381);
                vista2.setVisible(true);
            }
        }
    }
    
    public void limpiar(){
        vista.txtMatriculaestu.setText("");
        vista.txtNombreEstu.setText("");
        vista.txtcorreoestu.setText("");
        vista.cmbCarreraEstu.setSelectedIndex(0);
        vista.txtTelefonoestu.setText("");
        vista.txtAdeudoEstu.setText("");
    }
    
    public boolean valiEmpty(){
        if(!vista.txtMatriculaestu.getText().isEmpty()
                && !vista.txtNombreEstu.getText().isEmpty()
                && !vista.txtcorreoestu.getText().isEmpty()
                && !vista.txtTelefonoestu.getText().isEmpty()
                && !vista.txtAdeudoEstu.getText().isEmpty()
                && vista.cmbCarreraEstu.getSelectedIndex()!=0){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean valiEmptyBusc(){
        if(!vista.txtMatriculaestu.getText().isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void Carrera(){
        if(vista.cmbCarreraEstu.getSelectedIndex()==1){
            mod.setCarrera("Ingeniería");
        }
        else if(vista.cmbCarreraEstu.getSelectedIndex()==2){
            mod.setCarrera("Medicina");
        }
        else if(vista.cmbCarreraEstu.getSelectedIndex()==3){
            mod.setCarrera("Derecho");
        }
        else if(vista.cmbCarreraEstu.getSelectedIndex()==4){
            mod.setCarrera("Psicología");
        }
        else if(vista.cmbCarreraEstu.getSelectedIndex()==5){
            mod.setCarrera("Arquitectura");
        }
        else if(vista.cmbCarreraEstu.getSelectedIndex()==6){
            mod.setCarrera("Economía");
        }
    }

    public void CarreraBusc(){
        if(mod.getCarrera()== "Ingeniería"){            
            vista.cmbCarreraEstu.setSelectedIndex(1);
        }
        else if(mod.getCarrera()== "Medicina"){            
            vista.cmbCarreraEstu.setSelectedIndex(2);
        }
        else if(mod.getCarrera()== "Derecho"){            
            vista.cmbCarreraEstu.setSelectedIndex(3);
        }
        else if(mod.getCarrera()== "Psicología"){            
            vista.cmbCarreraEstu.setSelectedIndex(4);
        }
        else if(mod.getCarrera()== "Arquitectura"){            
            vista.cmbCarreraEstu.setSelectedIndex(5);
        }
        else if(mod.getCarrera()== "Economía"){            
            vista.cmbCarreraEstu.setSelectedIndex(6);
        }
    }
}
