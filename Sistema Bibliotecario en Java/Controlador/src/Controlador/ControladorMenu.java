
package Controlador;


import Vista.*;
import Modelo.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class ControladorMenu implements ActionListener{
    dlg_Menu vista;
    dlg_login vista2;
    dlg_registro vista3;
    Trabajador mod;
    dbTrabajador db;
    dbLogin db2;
    ControladorAutor aut;
    ControladorDevoluciones dev;
    ControladorEstudiante est;
    ControladorLibros lib;
    ControladorPrestamos pres; 
    ControladorRegistros reg;
    ControladorMostrar mos;
    


    private ControladorMenu(dlg_Menu vista, dlg_login vista2, Trabajador mod, dbTrabajador db,dbLogin db2, ControladorRegistros reg, ControladorEstudiante est, ControladorAutor aut, ControladorPrestamos pres, ControladorDevoluciones dev, ControladorMostrar mos, ControladorLibros lib) {
        this.vista = vista;
        this.vista2 = vista2;
        this.mod = mod;
        this.db = db;
        this.db2 = db2;
        this.reg = reg;
        this.est = est;
        this.aut = aut;
        this.pres = pres;
        this.dev = dev;
        this.mos = mos;
        this.lib = lib;
        
        vista2.btnRegistini.addActionListener(this);
        vista2.btnIngresaIni.addActionListener(this);
        vista2.btnSalir.addActionListener(this);
        vista.btnMenuAut.addActionListener(this);
        vista.btnMenuDev.addActionListener(this);
        vista.btnMenuEst.addActionListener(this);
        vista.btnMenuLib.addActionListener(this);
        vista.btnMenuPres.addActionListener(this);
        vista.btnMenuCerrar.addActionListener(this);
        vista.btnMostrar.addActionListener(this);
    }
    
    void iniciarVista() throws Exception{
        vista2.setTitle(":: Login ::");
        vista2.setSize(400, 330);
        vista2.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== vista2.btnIngresaIni){
            try {
                mod = (Trabajador) db2.buscar(vista2.txtIngresaUsu.getText(),vista2.txtIngresaContra.getText());
                if(mod.getMatricula().equals("") && mod.getContraseña().equals("")){
                    JOptionPane.showMessageDialog(vista,"Su Matricula o Contraseña son incorrectos o no existen, verifique sus datos porfavor"); 
                }
                else{
                    vista2.setVisible(false);
                    vista2.dispose();
                    vista.setTitle(":: Menu ::");
                    vista.setSize(277, 410);
                    vista.setVisible(true);
                }
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista,"Surgio un error al ingresar: "+ ex.getMessage());
            }
            
        }
        
        else if(e.getSource()== vista2.btnRegistini){
            try {
                
                vista2.setVisible(false);
                vista2.dispose();
                reg.iniciarVista();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista,"Surgio un error al entrar a Registros: "+ ex.getMessage());
            }
        }
        else if(e.getSource()== vista.btnMenuEst){
            try {
                vista.setVisible(false);
                vista.dispose();
                est.iniciarVista();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista,"Surgio un error al entrar a Autores: "+ ex.getMessage());
            }
        }
        else if(e.getSource()== vista.btnMenuAut){
            try {
                vista.setVisible(false);
                vista.dispose();
                aut.iniciarVista();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista,"Surgio un error al entrar a Autores: "+ ex.getMessage());
            }
        }
        else if(e.getSource()== vista.btnMenuPres){
            try {
                vista.setVisible(false);
                vista.dispose();
                pres.iniciarVista();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista,"Surgio un error al entrar a Prestamos: "+ ex.getMessage());
            }
        }
        else if(e.getSource()== vista.btnMenuLib){
            try {
                vista.setVisible(false);
                vista.dispose();
                lib.iniciarVista();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista,"Surgio un error al entrar a Libros: "+ ex.getMessage());
            }
        }
        else if(e.getSource()== vista.btnMenuDev){
            try {
                vista.setVisible(false);
                vista.dispose();
                dev.iniciarVista();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista,"Surgio un error al entrar a Devoluciones: "+ ex.getMessage());
            }
        }
        else if(e.getSource()== vista.btnMostrar){
            try {
                vista.setVisible(false);
                vista.dispose();
                mos.iniciarVista();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista,"Surgio un error al entrar a Devoluciones: "+ ex.getMessage());
            }
        }
        else if (e.getSource() == vista.btnMenuCerrar) {
            if (JOptionPane.showConfirmDialog(vista, "Seguro que desea cerrar?", "Cerrar", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            } else {
                vista.setVisible(false);
                vista.dispose();
                System.exit(0);
            }
        }
        else if (e.getSource() == vista2.btnSalir) {
            if (JOptionPane.showConfirmDialog(vista2, "Seguro que desea cerrar?", "Cerrar", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            } else {
                vista2.setVisible(false);
                vista2.dispose();
                System.exit(0);
            }
        }
    }
    
    public static void main(String[] args) {
        Trabajador mod = new Trabajador();
        Estudiante mod2 = new Estudiante();
        Autor mod3 = new Autor();
        Prestamos mod4 = new Prestamos();
        Devoluciones mod5 = new Devoluciones();
        Libro mod6 = new Libro();
        dbTrabajador db = new dbTrabajador();
        dbLogin db2 = new dbLogin();
        dbEstudiante db3 = new dbEstudiante();
        dbAutor db4 = new dbAutor();
        dbPrestamos db5 = new dbPrestamos();
        dbDevoluciones db6 = new dbDevoluciones();
        dbMostrar db7 = new dbMostrar();
        dbLibro db8 = new dbLibro();
        dlg_Menu vista = new dlg_Menu();
        dlg_login vista2 = new dlg_login();
        dlg_registro vista3 = new dlg_registro();
        dlg_Estudiantes vista4 = new dlg_Estudiantes();
        dlg_Autor vista5 = new dlg_Autor();
        dlg_Prestamos vista6 = new dlg_Prestamos();
        dlg_Devoluciones vista7 = new dlg_Devoluciones();
        dlg_Mostrar vista8 = new dlg_Mostrar();
        dlg_Libros vista9 = new dlg_Libros();
        ControladorRegistros reg = new ControladorRegistros(mod, db, vista3, vista2);
        ControladorEstudiante est = new ControladorEstudiante(mod2, db3, vista4, vista);
        ControladorAutor aut = new ControladorAutor(mod3, db4, vista5, vista);
        ControladorPrestamos pre = new ControladorPrestamos(mod4, db5, db3, db, db8,  vista6, vista);
        ControladorDevoluciones dev = new ControladorDevoluciones(mod5, mod4, db6, db5, vista7, vista);
        ControladorMostrar mos = new ControladorMostrar(vista8, vista, db7);
        ControladorLibros lib = new ControladorLibros(mod6, db8, db4, vista9, vista);
        ControladorMenu menu = new ControladorMenu(vista, vista2, mod, db, db2, reg, est, aut, pre, dev, mos, lib);
        
        try {
            menu.iniciarVista();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista,"Surgio un error al Iniciar vista "+ ex.getMessage());
        }
    }
    
}
