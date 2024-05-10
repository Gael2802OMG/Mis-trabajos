
package Controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import Vista.*;
import Modelo.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class ControladorMostrar implements MouseListener{
    dlg_Mostrar vista;
    dlg_Menu vista2;
    dbMostrar mod;
    String dato;
    String auxDato;
    boolean mostrar;

    public ControladorMostrar(dlg_Mostrar vista, dlg_Menu vista2, dbMostrar mod) {
        this.vista = vista;
        this.vista2 = vista2;
        this.mod = mod;
        
        
        vista.jTableMostrar.addMouseListener(this);
    }
    
    void iniciarVista() throws Exception{
        vista.jTableMostrar.setModel(mod.listar("show tables"));
        vista.setTitle(":: Mostrar ::");
        vista.setSize(697, 620);
        vista.setVisible(true);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.jTableMostrar) {
                try {
                    int fila = vista.jTableMostrar.getSelectedRow();
                    int columna = vista.jTableMostrar.getSelectedColumn();
                    Object valor = vista.jTableMostrar.getValueAt(fila, columna);
                    dato = (valor != null) ? valor.toString() : "";
                    
                    auxDato = dato;
                    String sql = "show tables from " + dato;
                    vista.jTableMostrar.setModel(mod.listar(sql));
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "Surgió el siguiente error: " + ex.getMessage());
                }
        }
        if (e.getSource() == vista.btnMostrarVolver) {
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

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
   
    
}
