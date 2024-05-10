package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.*;
import Vista.*;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControladorLibros implements ActionListener {

    Libro mod;
    dbLibro db;
    dbAutor db2;
    dlg_Libros vista;
    dlg_Menu vista2;
    boolean hab;
    boolean act;

    public ControladorLibros(Libro mod, dbLibro db, dbAutor db2, dlg_Libros vista, dlg_Menu vista2) {
        this.mod = mod;
        this.db = db;
        this.db2 = db2;
        this.vista = vista;
        this.vista2 = vista2;

        vista.btnAgregarLi1.addActionListener(this);
        vista.btnBuscarli.addActionListener(this);
        vista.btnCancelarLi.addActionListener(this);
        vista.btnLimpiarli.addActionListener(this);
        vista.btnNuevoli.addActionListener(this);
        vista.btnVolverALi.addActionListener(this);
    }

    void iniciarVista() throws Exception {
        vista.setTitle(":: Autor ::");
        vista.setSize(400, 570);
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnNuevoli) {
            hab = true;
            vista.cmbCategoria.setEnabled(hab);

            vista.txtNumero.setEnabled(hab);
            vista.cmbIdAutor.setEnabled(hab);
            vista.txtExist.setEnabled(hab);
            vista.txtPrecio.setEnabled(hab);
            vista.txtEdit.setEnabled(hab);

            vista.btnBuscarli.setEnabled(hab);
            vista.btnLimpiarli.setEnabled(hab);
            vista.btnAgregarLi1.setEnabled(hab);
            vista.btnCancelarLi.setEnabled(hab);
            vista.btnVolverALi.setEnabled(hab);

            vista.jdFechaPu.setEnabled(hab);
        } else if (e.getSource() == vista.btnAgregarLi1) {
            if (valiEmpty()) {
                if (act != true) {
                    try {
                        if (valiEmpty()) {
                            mod.setCodigo(vista.txtCodigo.getText());
                            mod.setNum_pag(Integer.parseInt(vista.txtNumero.getText()));
                            mod.setAutor(vista.cmbIdAutor.getSelectedIndex());
                            mod.setEditorial(vista.txtEdit.getText());
                            mod.setNum_ejem(Integer.parseInt(vista.txtExist.getText()));
                            fecha();
                            Categoria();
                            db.insertar(mod);
                        } else {
                            JOptionPane.showMessageDialog(vista, "Por favor ingrese todos los datos necesarios.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(ControladorLibros.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        if (valiEmpty()) {
                            mod.setCodigo(vista.txtCodigo.getText());
                            mod.setNum_pag(Integer.parseInt(vista.txtNumero.getText()));
                            mod.setAutor(vista.cmbIdAutor.getSelectedIndex());
                            mod.setEditorial(vista.txtEdit.getText());
                            mod.setNum_ejem(Integer.parseInt(vista.txtExist.getText()));
                            fecha();
                            Categoria();
                            db.actualizar(mod);
                        } else {
                            JOptionPane.showMessageDialog(vista, "Por favor ingrese todos los datos necesarios.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(ControladorLibros.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(vista, "No deje espacios vacios");
            }
        } else if (e.getSource() == vista.btnBuscarli) {
            try {
                String codigoBuscado = vista.txtCodigo.getText();

                if (codigoBuscado.isEmpty()) {
                    // Mostrar un mensaje de error o hacer algo apropiado cuando no hay datos de entrada
                    // Por ejemplo:
                    JOptionPane.showMessageDialog(vista, "Por favor ingrese un código para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                mod = (Libro) db.buscar(codigoBuscado);

                if (mod != null) {
                    vista.txtCodigo.setText(mod.getCodigo());
                    vista.txtEdit.setText(mod.getEditorial());
                    vista.txtExist.setText(String.valueOf(mod.getNum_ejem()));
                    vista.txtNumero.setText(String.valueOf(mod.getNum_pag()));
                    vista.txtPrecio.setText(String.valueOf(mod.getPreciolibro()));
                    vista.cmbIdAutor.setSelectedIndex(mod.getAutor());
                } else {
                    // Manejar el caso en que no se encuentra el libro
                    // Por ejemplo:
                    JOptionPane.showMessageDialog(vista, "El libro no fue encontrado.", "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                Logger.getLogger(ControladorLibros.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource()
                == vista.btnLimpiarli) {
            vista.cmbCategoria.setSelectedIndex(0);
            vista.txtNumero.setText("");
            vista.cmbIdAutor.setSelectedIndex(0);
            vista.txtExist.setText("");
            vista.txtPrecio.setText("");
        } else if (e.getSource()
                == vista.btnCancelarLi) {
            hab = true;
            vista.cmbCategoria.setEnabled(hab);
            vista.txtNumero.setEnabled(hab);
            vista.cmbIdAutor.setEnabled(hab);
            vista.txtExist.setEnabled(hab);
            vista.txtPrecio.setEnabled(hab);
            vista.btnLimpiarli.setEnabled(hab);
            vista.btnAgregarLi1.setEnabled(hab);
            vista.btnCancelarLi.setEnabled(hab);

            vista.jdFechaPu.setEnabled(hab);
        } else if (e.getSource()
                == vista.btnVolverALi) {
            if (JOptionPane.showConfirmDialog(vista, "Seguro que desea cerrar?", "Cerrar", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            } else {
                vista.setVisible(false);
                vista.dispose();
                vista2.setTitle(":: Menu ::");
                vista2.setSize(277, 381);
                vista2.setVisible(true);
            }
        } else if (e.getSource() == vista.btnLimpiarli) {
            // Limpia los campos de entrada
            vista.txtCodigo.setText("");
            vista.txtEdit.setText("");
            vista.txtExist.setText("");
            vista.txtNumero.setText("");
            vista.txtPrecio.setText("");
            vista.cmbIdAutor.setSelectedIndex(0);
            vista.cmbCategoria.setSelectedIndex(0);

        } else if (e.getSource() == vista.btnCancelarLi) {
            hab = false;
            vista.txtCodigo.setEnabled(hab);
            vista.txtEdit.setEnabled(hab);
            vista.txtExist.setEnabled(hab);
            vista.txtNumero.setEnabled(hab);
            vista.txtPrecio.setEnabled(hab);
            vista.cmbIdAutor.setEnabled(hab);
            vista.btnLimpiarli.setEnabled(hab);
            vista.cmbCategoria.setEnabled(hab);
            vista.btnAgregarLi1.setEnabled(hab);

        } else if (e.getSource() == vista.btnVolverALi) {
        }
        if (JOptionPane.showConfirmDialog(vista, "Seguro que desea volver al menu?", "Cerrar", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
        } else {
            vista.setVisible(false);
            vista.dispose();
            vista2.setTitle(":: Menu ::");
            vista2.setSize(277, 381);
            vista2.setVisible(true);
        }

    }

    public void IdAut() {
        try {
            vista.cmbIdAutor.setModel(db2.id());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Surgío un problema al encontrar id existentes: " + ex.getMessage());
        }
    }

    private boolean valiEmpty() {
        return vista.cmbIdAutor.getSelectedIndex() != 0
                && !vista.txtCodigo.getText().isEmpty()
                && !vista.txtEdit.getText().isEmpty()
                && !vista.txtExist.getText().isEmpty()
                && !vista.txtNumero.getText().isEmpty()
                && !vista.txtPrecio.getText().isEmpty();
    }

    public void fecha() {
        int mes = vista.jdFechaPu.getCalendar().get(Calendar.MONTH) + 1;
        if (mes < 10) {
            mod.setFech_p(String.valueOf(vista.jdFechaPu.getCalendar().get(Calendar.YEAR)) + ",0" + mes + ","
                    + String.valueOf(vista.jdFechaPu.getCalendar().get(Calendar.DAY_OF_MONTH)));
        } else {
            mod.setFech_p(String.valueOf(vista.jdFechaPu.getCalendar().get(Calendar.YEAR)) + "," + mes + ","
                    + String.valueOf(vista.jdFechaPu.getCalendar().get(Calendar.DAY_OF_MONTH)));
        }
    }

    public void Categoria() {
        switch (vista.cmbCategoria.getSelectedIndex()) {
            case 1:
                mod.setCateg("Informativos");
                break;
            case 2:
                mod.setCateg("literarios");
                break;
            case 3:
                mod.setCateg("Historicos");
                break;
            case 4:
                mod.setCateg("biograficos");
                break;
            case 5:
                mod.setCateg("enciclopedia");
                break;
            case 6:
                mod.setCateg("artisticos");
                break;
            case 7:
                mod.setCateg("cientificos");
                break;
            case 8:
                mod.setCateg("Ficción");
                break;
            case 9:
                mod.setCateg("Fantasía");
                break;
            case 10:
                mod.setCateg("Realismo mágico");
                break;
            case 11:
                mod.setCateg("Distopia");
                break;
            case 12:
                mod.setCateg("Romance");
                break;
            case 13:
                mod.setCateg("Suspenso");
                break;
            default:
                break;
        }
    }

    public void CategoriaBusc() {
        switch (mod.getCateg()) {
            case "Informativos":
                vista.cmbCategoria.setSelectedIndex(1);
                break;
            case "literarios":
                vista.cmbCategoria.setSelectedIndex(2);
                break;
            case "Historicos":
                vista.cmbCategoria.setSelectedIndex(4);
                break;
            case "biograficos":
                vista.cmbCategoria.setSelectedIndex(5);
                break;
            case "enciclopedia":
                vista.cmbCategoria.setSelectedIndex(6);
                break;
            case "artisticos":
                vista.cmbCategoria.setSelectedIndex(7);
                break;
            case "cientificos":
                vista.cmbCategoria.setSelectedIndex(8);
                break;
            case "Ficción":
                vista.cmbCategoria.setSelectedIndex(9);
                break;
            case "Fantasía":
                vista.cmbCategoria.setSelectedIndex(10);
                break;
            case "Realismo mágico":
                vista.cmbCategoria.setSelectedIndex(11);
                break;
            case "Distopia":
                vista.cmbCategoria.setSelectedIndex(12);
                break;
            case "Romance":
                vista.cmbCategoria.setSelectedIndex(14);
                break;
            case "Suspenso":
                vista.cmbCategoria.setSelectedIndex(15);
                break;
            default:
                break;
        }
    }

}
