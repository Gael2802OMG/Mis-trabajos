
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.*;
import Vista.*;
import javax.swing.JOptionPane;

public class ControladorRegistros implements ActionListener{
    Trabajador mod;
    dbTrabajador db;
    dlg_registro vista;
    dlg_login vistaL;
    boolean act = false;

    ControladorRegistros(Trabajador mod, dbTrabajador db, dlg_registro vista, dlg_login vistaL) {
        this.mod = mod;
        this.db = db;
        this.vista = vista;
        this.vistaL = vistaL;
        
        vista.btnRegistrarseRegis.addActionListener(this);
        vista.btnBuscarRegis.addActionListener(this);
        vista.btnDeshabilitarRegis.addActionListener(this);
        vista.btnLimpiar.addActionListener(this);
        vista.btnVolverAu.addActionListener(this);
    }
    
    void iniciarVista() throws Exception{
        vista.setTitle(":: Autor ::");
        vista.setSize(415, 459);
        vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnRegistrarseRegis){
            if(valiEmpty()!=true){
                JOptionPane.showMessageDialog(vista,"No deje ningun espacio vacio");
            }
            else{
                if(act != true){
                    try {
                        mod = (Trabajador)db.buscar(vista.txtMatricularegis.getText());
                        if(!mod.getMatricula().equals("")){
                            JOptionPane.showMessageDialog(vista,"Esa matricula ya está registrada");
                        }
                        else{
                            mod.setMatricula(vista.txtMatricularegis.getText());
                            mod.setNombre(vista.txtNomRegis.getText());
                            mod.setContraseña(vista.txtContrRegis.getText());
                            mod.setCorreo(vista.txtElectro.getText());
                            mod.setTel_est(vista.txtTeleRegi.getText());
                            mod.setDireccion(vista.txtDirecRegi.getText());
                            cargo();
                            db.insertar(mod);
                            limpiar(); 
                        }
                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vista, "Surgio un error al insertar: " + ex.getMessage());
                    }
                }
                else{
                    try {
                        mod.setMatricula(vista.txtMatricularegis.getText());
                        mod.setNombre(vista.txtNomRegis.getText());
                        mod.setContraseña(vista.txtContrRegis.getText());
                        mod.setCorreo(vista.txtElectro.getText());
                        mod.setTel_est(vista.txtTeleRegi.getText());
                        mod.setDireccion(vista.txtDirecRegi.getText());
                        cargo();
                        db.actualizar(mod);
                        limpiar();
                        act=false; 
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vista, "Surgio un error al actualizar: " + ex.getMessage());
                    }
                }
                
            }
            
            
        }
        
        else if(e.getSource()==vista.btnBuscarRegis){
            
            if(valiEmptyBus()!=true){
                JOptionPane.showMessageDialog(vista,"Para buscar ingrese una matricula existente por favor");
                limpiar();
            }
            else{
                try {
                    mod = (Trabajador) db.buscar(vista.txtMatricularegis.getText());
                    if(mod.getMatricula().equals("")){
                        JOptionPane.showMessageDialog(vista,"No existe esa matricula, verifiquela por favor");
                        limpiar();
                    }
                    else{       
                        vista.txtMatricularegis.setText(mod.getMatricula());
                        vista.txtNomRegis.setText(mod.getNombre());
                        vista.txtContrRegis.setText(mod.getContraseña());
                        vista.txtElectro.setText(mod.getCorreo());
                        vista.txtTeleRegi.setText(mod.getTel_est());
                        vista.txtDirecRegi.setText(mod.getDireccion());
                        cargoBus();
                        act = true;
                        
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista,"Ocurrio un error al buscar: "+ex.getMessage());
                    limpiar();
                } 
            }     
        }
        
        else if(e.getSource()==vista.btnDeshabilitarRegis){
            if(valiEmptyBus()!=true){
                JOptionPane.showMessageDialog(vista,"Para deshabilitar ingrese una matricula existente por favor");
            }
            else{
                try {
                    mod = (Trabajador) db.buscar(vista.txtMatricularegis.getText());
                    if(mod.getMatricula().equals("")){
                        JOptionPane.showMessageDialog(vista,"No existe esa matricula, verifiquela por favor");
                    }
                    else{
                        db.deshabilitar(vista.txtMatricularegis.getText());
                    }     
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista,"Ocurrio un error al Deshabilitar: "+ex.getMessage());
                }
            }
            
            
        }
       
        else if(e.getSource()==vista.btnLimpiar){
            limpiar();
        }
        
        else if (e.getSource() == vista.btnVolverAu) {
            if (JOptionPane.showConfirmDialog(vista, "Seguro que desea volver?", "Volver", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            } else {
                vista.setVisible(false);
                vista.dispose();
                vistaL.setTitle(":: Login ::");
                vistaL.setSize(400, 330);
                vistaL.setVisible(true);
                
            }
        }
    }
    
    public void cargo(){
        if(vista.cmbCargo.getSelectedIndex() == 1){
            mod.setCargo("Bibliotecaria");
        }
        else if(vista.cmbCargo.getSelectedIndex() == 2){
            mod.setCargo("Asistente");
        }
    }
    public void cargoBus(){
        if(mod.getCargo().equals("Bibliotecaria")){
            vista.cmbCargo.setSelectedIndex(1);
        }
        else if(mod.getCargo().equals("Asistente")){
            vista.cmbCargo.setSelectedIndex(2);
        }
    }
    
    public void limpiar(){
        vista.txtMatricularegis.setText("");
        vista.txtNomRegis.setText("");
        vista.txtContrRegis.setText("");
        vista.cmbCargo.setSelectedIndex(0);
        vista.txtElectro.setText("");
        vista.txtTeleRegi.setText("");
        vista.txtDirecRegi.setText("");
    }
    
    public boolean valiEmpty(){
        if(!vista.txtMatricularegis.getText().isEmpty()
                && !vista.txtNomRegis.getText().isEmpty()
                && !vista.txtContrRegis.getText().isEmpty()
                && !vista.txtElectro.getText().isEmpty()
                && !vista.txtTeleRegi.getText().isEmpty()
                && !vista.txtDirecRegi.getText().isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean valiEmptyBus(){
        if(!vista.txtMatricularegis.getText().isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
    
}
