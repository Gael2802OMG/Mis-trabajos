
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vista.*;
import Modelo.*;
import java.text.DateFormat;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControladorAutor implements ActionListener{
    Autor mod;
    dbAutor db;
    dlg_Autor vista;
    dlg_Menu vista2;
    boolean hab;
    boolean act;
    
    public ControladorAutor(Autor mod, dbAutor db, dlg_Autor vista, dlg_Menu vista2) {
        this.mod = mod;
        this.db = db;
        this.vista = vista;
        this.vista2 = vista2;
        
        vista.btnAgregarau.addActionListener(this);
        vista.btnBuscarau.addActionListener(this);
        vista.btnLimpiarau.addActionListener(this);
        vista.btnNuevoau.addActionListener(this);
        vista.btnVolverAu.addActionListener(this);
        vista.btnCancelarAu.addActionListener(this);
        
    }
    
    
    void iniciarVista() throws Exception{
        vista.setTitle(":: Autor ::");
        vista.setSize(530, 430);
        vista.setVisible(true);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnNuevoau){
            hab = true;
            vista.txtNombreau.setEnabled(hab);
            vista.txtNacionalidadau.setEnabled(hab);
            vista.txtDescripcionau.setEnabled(hab);
            vista.btnBuscarau.setEnabled(hab);
            vista.btnLimpiarau.setEnabled(hab);
            vista.btnAgregarau.setEnabled(hab);
            vista.btnCancelarAu.setEnabled(hab);
            vista.btnVolverAu.setEnabled(hab);
            
            vista.jdtFechaau.setEnabled(hab);
        }
        else if(e.getSource() == vista.btnAgregarau){
            if(valiEmpty()!=true){
                JOptionPane.showMessageDialog(vista,"No deje espacios vacios");
            }
            else{
                try {
                    if(act!=true){
                       mod = (Autor)db.buscar(vista.txtNombreau.getText());
                        if(!mod.getNombre().equals("")){
                            JOptionPane.showMessageDialog(vista,"No se permiten autores con el mismo nombre");
                            limpiar();
                        }
                        else{
                            mod.setNombre(vista.txtNombreau.getText());
                            mod.setNacimiento(vista.txtNacionalidadau.getText());
                            mod.setDescripcion(vista.txtDescripcionau.getText());
                            fecha();
                            db.insertar(mod); 
                            limpiar();
                        } 
                    }
                    else{
                        mod.setNombre(vista.txtNombreau.getText());
                        mod.setNacimiento(vista.txtNacionalidadau.getText());
                        mod.setDescripcion(vista.txtDescripcionau.getText());
                        fecha();
                        db.actualizar(mod);
                        limpiar();
                    }
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista,"Ocurrio un error al agregar");
                }
            }      
        }
        else if(e.getSource() == vista.btnBuscarau){
            if(valiEmptyBusc()!=true){
                JOptionPane.showMessageDialog(vista,"Porfavor ingrese un nombre antes de buscar");
                limpiar();
            }
            else{
                try {
                    mod = (Autor) db.buscar(vista.txtNombreau.getText());
                    vista.txtNombreau.setText(mod.getNombre());
                    vista.txtNacionalidadau.setText(mod.getNacimiento());
                    vista.jdtFechaau.setDate(Date.valueOf(mod.getFechNac()));
                    vista.txtDescripcionau.setText(mod.getDescripcion());
                    act=true;
                } catch (Exception ex) {
                    Logger.getLogger(ControladorAutor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }    
        }
        else if(e.getSource() == vista.btnLimpiarau){
            limpiar();
        }
        else if(e.getSource() == vista.btnCancelarAu){
            hab = false;
            vista.txtNacionalidadau.setEnabled(hab);
            vista.txtDescripcionau.setEnabled(hab);
            vista.btnLimpiarau.setEnabled(hab);
            vista.btnAgregarau.setEnabled(hab);
            vista.btnCancelarAu.setEnabled(hab);
            
            vista.jdtFechaau.setEnabled(hab);
            
        }
        else if (e.getSource() == vista.btnVolverAu) {
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
    
    public void limpiar(){
        vista.txtNombreau.setText("");
        vista.txtNacionalidadau.setText("");
        vista.txtDescripcionau.setText("");
        vista.jdtFechaau.setDate(null);
    }
    
    public boolean valiEmpty(){
        if(!vista.txtNombreau.getText().isEmpty()
                && !vista.txtNacionalidadau.getText().isEmpty()
                && !vista.txtDescripcionau.getText().isEmpty()
                && vista.jdtFechaau.getDate()!=null){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean valiEmptyBusc(){
        if(!vista.txtNombreau.getText().isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void fecha(){
        int mes = vista.jdtFechaau.getCalendar().get(Calendar.MONTH) + 1;
        if(mes <10){
            mod.setFechNac(String.valueOf(vista.jdtFechaau.getCalendar().get(Calendar.YEAR)) + ",0"+ mes + ","
                + String.valueOf(vista.jdtFechaau.getCalendar().get(Calendar.DAY_OF_MONTH)));
        }
        else{
            mod.setFechNac(String.valueOf(vista.jdtFechaau.getCalendar().get(Calendar.YEAR)) + ","+ mes + ","
                + String.valueOf(vista.jdtFechaau.getCalendar().get(Calendar.DAY_OF_MONTH)));
        }
    }

}
