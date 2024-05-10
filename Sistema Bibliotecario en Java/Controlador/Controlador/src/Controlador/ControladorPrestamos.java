
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import Modelo.*;
import Vista.*;
import com.mysql.cj.PerConnectionLRUFactory;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControladorPrestamos implements ActionListener{
    Prestamos mod;
    dbPrestamos db;
    dbEstudiante db2;
    dbTrabajador db3;
    dbLibro db4;
    dlg_Prestamos vista;
    dlg_Menu vista2;
    boolean hab;
    boolean act = false;
    
    public ControladorPrestamos(Prestamos mod, dbPrestamos db, dbEstudiante db2, dbTrabajador db3, dbLibro db4, dlg_Prestamos vista, dlg_Menu vista2) {
        this.mod = mod;
        this.db = db;
        this.db2 = db2;
        this.db3 = db3;
        this.db4 = db4;
        this.vista = vista;
        this.vista2 = vista2;
        
        vista.btnBuscarPre.addActionListener(this);
        vista.btnCancelarPre.addActionListener(this);
        vista.btnInserPre.addActionListener(this);
        vista.btnLimpre.addActionListener(this);
        vista.btnNuevoPre.addActionListener(this);
        vista.btnVolverALi.addActionListener(this);
    }
    

    void iniciarVista() throws Exception{
        cmbIdPres();
        vista.setTitle(":: Prestamos ::");
        vista.setSize(446, 436);
        vista.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnNuevoPre){
            hab = true;
            vista.txtCodigoPres.setEnabled(hab);
            vista.cmbIdEst.setEnabled(hab);
            vista.cmbIdPers.setEnabled(hab);
            vista.cmbLibrospres.setEnabled(hab);
            vista.btnBuscarPre.setEnabled(hab);
            vista.btnLimpre.setEnabled(hab);
            vista.btnInserPre.setEnabled(hab);
            vista.btnCancelarPre.setEnabled(hab);
            vista.btnVolverALi.setEnabled(hab);
            
            vista.jdFechaini.setEnabled(hab);
            vista.jdFechaFi.setEnabled(hab);
            
        }
        else if(e.getSource()==vista.btnInserPre){
            if(valiEmpty()!=true){
                JOptionPane.showMessageDialog(vista,"No deje espacios vacios");
            }
            else{
                if(act!=true){
                    try {
                        mod = (Prestamos)db.buscar(vista.txtCodigoPres.getText());
                        if(!mod.getCodigo().equals("")){
                            JOptionPane.showMessageDialog(vista,"Ese codigo ya existe");
                        }
                        else{
                            mod.setCodigo(vista.txtCodigoPres.getText());
                            mod.setIdEst(vista.cmbIdEst.getSelectedIndex());
                            mod.setIdPer(vista.cmbIdEst.getSelectedIndex());
                            mod.setIdLib(vista.cmbIdEst.getSelectedIndex());
                            fechaini();
                            fechafin();
                            db.insertar(mod); 
                        }        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vista,"Ocurrio un error al insertar"+ex.getMessage());
                    }
                }
                else{
                    try {
                        mod.setCodigo(vista.txtCodigoPres.getText());
                        mod.setIdEst(vista.cmbIdEst.getSelectedIndex());
                        mod.setIdPer(vista.cmbIdEst.getSelectedIndex());
                        mod.setIdLib(vista.cmbIdEst.getSelectedIndex());
                        fechaini();
                        fechafin();
                        db.actualizar(mod);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vista,"Ocurrio un error al actualizar"+ex.getMessage());
                    }
                }
                 
            }  
        }
        else if(e.getSource()==vista.btnBuscarPre){
            if(valiEmptyBusc()!=true){
                JOptionPane.showMessageDialog(vista,"Porfavor ingrese un codigo antes de buscar");
            }
            else{
               try {
                    mod = (Prestamos) db.buscar(vista.txtCodigoPres.getText());
                    vista.txtCodigoPres.setText(mod.getCodigo());
                    vista.cmbIdEst.setSelectedIndex(mod.getIdEst());
                    vista.cmbIdPers.setSelectedIndex(mod.getIdPer());
                    vista.cmbLibrospres.setSelectedIndex(mod.getIdLib());
                    vista.jdFechaini.setDate(Date.valueOf(mod.getFechaInicio()));
                    vista.jdFechaFi.setDate(Date.valueOf(mod.getFechaFin()));
                    act = true;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista,"Ocurrio un error al buscar"+ex.getMessage());
                } 
            }
            
        }
        else if(e.getSource()==vista.btnLimpre){
            vista.txtCodigoPres.setText("");
            vista.cmbIdEst.setSelectedIndex(0);
            vista.cmbIdPers.setSelectedIndex(0);
            vista.cmbLibrospres.setSelectedIndex(0);
        }
        else if(e.getSource()==vista.btnCancelarPre){
            hab = false;
            
            vista.btnLimpre.setEnabled(hab);
            vista.btnInserPre.setEnabled(hab);
            vista.btnCancelarPre.setEnabled(hab);
            
            vista.cmbIdEst.setEnabled(hab);
            vista.cmbIdPers.setEnabled(hab);
            vista.cmbLibrospres.setEnabled(hab);
            
            vista.jdFechaini.setEnabled(hab);
            vista.jdFechaFi.setEnabled(hab);
        }
        else if (e.getSource() == vista.btnVolverALi) {
            if (JOptionPane.showConfirmDialog(vista, "Seguro que desea cerrar?", "Cerrar", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            } else {
                vista.setVisible(false);
                vista.dispose();
                vista2.setTitle(":: Menu ::");
                vista2.setSize(277, 381);
                vista2.setVisible(true);
            }
        }
    }
    
    public void cmbIdPres(){
        try {
            vista.cmbIdEst.setModel(db2.id());
            vista.cmbIdPers.setModel(db3.id());
            vista.cmbLibrospres.setModel(db4.id());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista,"Surgío un problema al encontrar id existentes: "+ex.getMessage());
        }
    }

    public boolean valiEmpty(){
        if(!vista.txtCodigoPres.getText().isEmpty()
                && vista.cmbIdEst.getSelectedIndex()!=0
                && vista.cmbIdPers.getSelectedIndex()!=0
                && vista.cmbLibrospres.getSelectedIndex()!=0){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean valiEmptyBusc(){
        if(!vista.txtCodigoPres.getText().isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void fechaini(){
        int mes = vista.jdFechaini.getCalendar().get(Calendar.MONTH) + 1;
        if(mes <10){
            mod.setFechaInicio(String.valueOf(vista.jdFechaini.getCalendar().get(Calendar.YEAR)) + ",0"+ mes + ","
                + String.valueOf(vista.jdFechaini.getCalendar().get(Calendar.DAY_OF_MONTH)));
        }
        else{
            mod.setFechaInicio(String.valueOf(vista.jdFechaini.getCalendar().get(Calendar.YEAR)) + ","+ mes + ","
                + String.valueOf(vista.jdFechaini.getCalendar().get(Calendar.DAY_OF_MONTH)));
        }
    }
    public void fechafin(){
        int mes = vista.jdFechaFi.getCalendar().get(Calendar.MONTH) + 1;
        if(mes <10){
            mod.setFechaFin(String.valueOf(vista.jdFechaFi.getCalendar().get(Calendar.YEAR)) + ",0"+ mes + ","
                + String.valueOf(vista.jdFechaFi.getCalendar().get(Calendar.DAY_OF_MONTH)));
        }
        else{
            mod.setFechaFin(String.valueOf(vista.jdFechaFi.getCalendar().get(Calendar.YEAR)) + ","+ mes + ","
                + String.valueOf(vista.jdFechaFi.getCalendar().get(Calendar.DAY_OF_MONTH)));
        }
    }
    
}
