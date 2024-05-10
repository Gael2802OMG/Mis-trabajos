
package Controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import Vista.*;
import Modelo.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControladorMostrar implements MouseListener{
    dlg_Mostrar vista;
    dlg_Menu vista2;
    dbMostrar mod;
    String dato;
    String auxDato;
    boolean mostrar = false;

    public ControladorMostrar(dlg_Mostrar vista, dlg_Menu vista2, dbMostrar mod) {
        this.vista = vista;
        this.vista2 = vista2;
        this.mod = mod;
        mostrar2 most = new mostrar2(vista, vista2);
        vista.jTableMostrar.addMouseListener(this);
    }

    void iniciarVista() throws Exception {
        vista.jTableMostrar.setModel(mod.listar("show tables from biblioteca"));
        vista.setTitle(":: Mostrar ::");
        vista.setSize(472, 431);
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
                if (mostrar != true) {
                    auxDato = dato;
                    String sql = "select * from biblioteca." + dato;
                    vista.jTableMostrar.setModel(mod.listar(sql));
                    mostrar = true;
                } else {
                    auxDato = dato;
                    String sql = "select * from biblioteca." + dato;
                    vista.jTableMostrar.setModel(mod.listar(sql));
                    mostrar = true;
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Surgió el siguiente error: " + ex.getMessage());
            }
        }
        if (e.getSource() == vista.btnMostrarVolver) {
            if (JOptionPane.showConfirmDialog(vista, "Seguro que desea cerrar?", "Cerrar", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            } else {
                vista.setVisible(false);
                vista.dispose();
                vista.setTitle(":: Menu ::");
                    vista.setSize(277, 410);
                    vista.setVisible(true);
            }
        }
        if (e.getSource() == vista.btnMostrarVolver) {

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public class mostrar2 implements ActionListener {

        dlg_Mostrar vista;
        dlg_Menu vista2;

        public mostrar2(dlg_Mostrar vista, dlg_Menu vista2) {
            this.vista = vista;
            this.vista2 = vista2;
            vista.btnMostrarMenuT.addActionListener(this);
            vista.btnMostrarVolver.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == vista.btnMostrarMenuT) {
                try {
                    vista.jTableMostrar.setModel(mod.listar("show tables"));
                } catch (Exception ex) {
                    Logger.getLogger(ControladorMostrar.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == vista.btnMostrarVolver) {
                vista.setVisible(false);
                vista.dispose();
                vista2.setTitle(":: Menu ::");
                vista2.setSize(277, 410);
                vista2.setVisible(true);

            }
        }

    }
    
   
    
}
