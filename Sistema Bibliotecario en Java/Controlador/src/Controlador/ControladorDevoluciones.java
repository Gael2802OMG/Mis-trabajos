
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.*;
import Vista.*;
import javax.swing.JOptionPane;
import java.sql.Date;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorDevoluciones implements ActionListener{
    Devoluciones mod;
    Prestamos mod2;
    dbDevoluciones db;
    dbPrestamos db2;
    dlg_Devoluciones vista;
    dlg_Menu vista2;
    boolean hab;
    boolean act = false;
    
    public ControladorDevoluciones(Devoluciones mod, Prestamos mod2, dbDevoluciones db, dbPrestamos db2, dlg_Devoluciones vista, dlg_Menu vista2) {
        this.mod = mod;
        this.mod2 = mod2;
        this.db = db;
        this.db2 = db2;
        this.vista = vista;
        this.vista2 = vista2;
        
        vista.btnNuevodevo.addActionListener(this);
        vista.btnBuscardevo.addActionListener(this);
        vista.btnCancelardevo.addActionListener(this);
        vista.btnLimpiardevo.addActionListener(this);
        vista.btnAgregardevo.addActionListener(this);
        vista.btnVolverDevo.addActionListener(this);
        vista.btnCalcular.addActionListener(this);
    }
    
    
    void iniciarVista() throws Exception{
        idPres();
        vista.setTitle(":: Autor ::");
        vista.setSize(466, 419);
        vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnNuevodevo){
            hab = true;
            vista.txtCostodevo.setEnabled(hab);
            vista.txtDiasdevo.setEnabled(hab);
            vista.txtCodigoDevolucion.setEnabled(hab);
            
            vista.cmbIdPrestamoDevo.setEnabled(hab);
            
            vista.btnBuscardevo.setEnabled(hab);
            vista.btnLimpiardevo.setEnabled(hab);
            vista.btnCancelardevo.setEnabled(hab);
            vista.btnVolverDevo.setEnabled(hab);
            vista.btnAgregardevo.setEnabled(hab);
            vista.btnCalcular.setEnabled(hab);
            
            vista.jDFEchaDevo.setEnabled(hab);
        }
        else if(e.getSource()==vista.btnCalcular){
            if(valiEmptyCal()!=true){
                JOptionPane.showMessageDialog(vista,"No deje espacios vacios");
            }
            else{
                vista.txtSubtotaldevo.setText(String.valueOf(Float.parseFloat(vista.txtCostodevo.getText())*Float.parseFloat(vista.txtDiasdevo.getText())));
                vista.txtIVAdevo.setText(String.valueOf(Float.parseFloat(vista.txtSubtotaldevo.getText()) * 0.16f));
            }
            
        }
        
        else if(e.getSource()==vista.btnAgregardevo){
            if(valiEmpty()!=true){
                JOptionPane.showMessageDialog(vista,"No deje espacios vacios");
            }
            else{
                try {
                    mod = (Devoluciones)db.buscar(Integer.parseInt(vista.cmbIdPrestamoDevo.getSelectedItem().toString()));
                    if(!mod.getCodigo().equals("")){
                        JOptionPane.showMessageDialog(vista,"Porfavor asegurese de ingresar un id de que no esté duplicado");
                    }
                    else{
                        if (act != true) {
                            try {
                                mod = (Devoluciones) db.buscar(vista.txtCodigoDevolucion.getText());
                                if (!mod.getCodigo().equals("")) {
                                    JOptionPane.showMessageDialog(vista, "Ese codigo ya existe");
                                } else {
                                    mod.setCodigo(vista.txtCodigoDevolucion.getText());
                                    mod.setId_dpres(Integer.parseInt(vista.cmbIdPrestamoDevo.getSelectedItem().toString()));
                                    mod.setCostdia(Float.parseFloat(vista.txtCostodevo.getText()));
                                    mod.setDiasretra(Integer.parseInt(vista.txtDiasdevo.getText()));
                                    mod.setSubtotal(Float.parseFloat(vista.txtSubtotaldevo.getText()));
                                    mod.setIva(Float.parseFloat(vista.txtIVAdevo.getText()));
                                    fecha();
                                    db.insertar(mod);
                                    
                                }

                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(vista, "Surgio un error al agregar: " + ex.getMessage());
                            }
                        } else {
                            try {
                                mod.setCodigo(vista.txtCodigoDevolucion.getText());
                                mod.setId_dpres(vista.cmbIdPrestamoDevo.getSelectedIndex());
                                mod.setCostdia(Float.parseFloat(vista.txtCostodevo.getText()));
                                mod.setSubtotal(Integer.parseInt(vista.txtSubtotaldevo.getText()));
                                mod.setDiasretra(Integer.parseInt(vista.txtDiasdevo.getText()));
                                mod.setIva(Float.parseFloat(vista.txtIVAdevo.getText()));
                                fecha();
                                db.actualizar(mod);
                                act=false; 
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(vista, "Surgio un error al actualizar: " + ex.getMessage());
                            }
                        }
                        
                    }
                } catch (Exception ex) {
                    Logger.getLogger(ControladorDevoluciones.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }      
        }
        else if(e.getSource()==vista.btnBuscardevo){
            if(valiEmptyBusc()!=true){
                JOptionPane.showMessageDialog(vista,"Ingrese un codigo antes de buscar");
            }
            else{
                try {
                    mod = (Devoluciones) db.buscar(vista.txtCodigoDevolucion.getText());
                    if(mod.getCodigo().equals("")){
                        JOptionPane.showMessageDialog(vista,"Este codigo no existe");
                    }
                    else{
                        vista.txtSubtotaldevo.setText(String.valueOf(mod.getSubtotal()));
                        vista.cmbIdPrestamoDevo.setSelectedItem(String.valueOf(mod.getId_dpres()));
                        vista.txtIVAdevo.setText(String.valueOf(mod.getId_dpres()));
                        vista.txtCodigoDevolucion.setText(String.valueOf(mod.getCodigo()));
                        vista.txtDiasdevo.setText(String.valueOf(mod.getDiasretra()));
                        vista.txtCostodevo.setText(String.valueOf(mod.getCostdia()));
                        vista.jDFEchaDevo.setDate(Date.valueOf(mod.getFechadev()));
                        vista.txtIVAdevo.setText(String.valueOf(mod.getIva()));
                        act=true; 
                    }
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista,"Surgio un error al buscar: "+ex.getMessage());
                }
            }
            
        }
        else if(e.getSource()==vista.btnLimpiardevo){
            limpiar();
            
        }
        else if(e.getSource()==vista.btnCancelardevo){
            hab = false;
            vista.txtCostodevo.setEnabled(hab);
            vista.txtDiasdevo.setEnabled(hab);
            
            vista.cmbIdPrestamoDevo.setEnabled(hab);
            
            vista.btnCancelardevo.setEnabled(hab);
            vista.btnVolverDevo.setEnabled(hab);
            vista.btnAgregardevo.setEnabled(hab);
            vista.btnCalcular.setEnabled(hab);
            
            vista.jDFEchaDevo.setEnabled(hab);
        }
        else if (e.getSource() == vista.btnVolverDevo) {
            if (JOptionPane.showConfirmDialog(vista, "Seguro que desea volver?", "Cerrar", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            } else {
                vista.setVisible(false);
                vista.dispose();
                vista2.setTitle(":: Menu ::");
                vista2.setSize(277, 410);
                vista2.setVisible(true);
            }
        }
    }
    
    public void idPres(){
        try {
            vista.cmbIdPrestamoDevo.setModel(db2.id());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista,"Surgío un problema al encontrar id existentes: "+ex.getMessage());
        }
    }
    
    public void limpiar(){
            vista.txtSubtotaldevo.setText("");
            vista.cmbIdPrestamoDevo.setSelectedIndex(0);
            vista.txtDiasdevo.setText("");
            vista.txtCostodevo.setText("");
            vista.txtIVAdevo.setText("");
            vista.jDFEchaDevo.setDate(null);
    }
    
    public boolean valiEmpty(){
        if(!vista.txtCodigoDevolucion.getText().isEmpty()
                && !vista.txtCostodevo.getText().isEmpty()
                && !vista.txtDiasdevo.getText().isEmpty()
                && vista.cmbIdPrestamoDevo.getSelectedIndex()!=0
                && !vista.txtIVAdevo.getText().isEmpty()
                && !vista.txtSubtotaldevo.getText().isEmpty()
                && vista.jDFEchaDevo.getDate() != null){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean valiEmptyBusc(){
        if(!vista.txtCodigoDevolucion.getText().isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean valiEmptyCal(){
        if(!vista.txtCodigoDevolucion.getText().isEmpty()
                && !vista.txtCostodevo.getText().isEmpty()
                && !vista.txtDiasdevo.getText().isEmpty()
                && vista.cmbIdPrestamoDevo.getSelectedIndex()!=0
                && vista.jDFEchaDevo.getDate() != null){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void fecha(){
        int mes = vista.jDFEchaDevo.getCalendar().get(Calendar.MONTH) + 1;
        if(mes <10){
            mod.setFechadev(String.valueOf(vista.jDFEchaDevo.getCalendar().get(Calendar.YEAR)) + ",0"+ mes + ","
                + String.valueOf(vista.jDFEchaDevo.getCalendar().get(Calendar.DAY_OF_MONTH)));
        }
        else{
            mod.setFechadev(String.valueOf(vista.jDFEchaDevo.getCalendar().get(Calendar.YEAR)) + ","+ mes + ","
                + String.valueOf(vista.jDFEchaDevo.getCalendar().get(Calendar.DAY_OF_MONTH)));
        }
    }
}
