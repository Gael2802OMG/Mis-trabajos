/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class dbAutor extends dbManejador implements dbPersistencia {

   

    @Override
    public void insertar(Object objecto) throws Exception {

        Autor autor = new Autor();
        autor = (Autor) objecto;
        String consulta = "INSERT INTO biblioteca.autor(nombre, fech_nac, Nac, descrip) VALUES (?, ?, ?, ?);";
        if (this.conectar()) {
            try {
                this.sqlConsulta = conexion.prepareStatement(consulta);
                this.sqlConsulta.setString(1, autor.getNombre());
                this.sqlConsulta.setString(2, autor.getFechNac());
                this.sqlConsulta.setString(3, autor.getNacimiento());
                this.sqlConsulta.setString(4, autor.getDescripcion());
                this.sqlConsulta.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se insertó correctamente");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Surgió un error al insertar: " + e.getMessage());
            } finally {
                this.desconectar();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No fue posible conectarse");
        }

    }

    @Override
    public void Prestamo(Object objecto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(Object objeto) throws Exception {
        Autor autor = (Autor) objeto;

        String consulta = "UPDATE biblioteca.autor SET fech_nac = ?, Nac = ?, descrip = ? WHERE nombre = ?";
        if (this.conectar()) {
            try {
                this.sqlConsulta = conexion.prepareStatement(consulta);
                this.sqlConsulta.setString(1, autor.getFechNac());
                this.sqlConsulta.setString(2, autor.getNacimiento());
                this.sqlConsulta.setString(3, autor.getDescripcion());
                this.sqlConsulta.setString(4, autor.getNombre());
                this.sqlConsulta.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se actualizó correctamente");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Surgió un error al actualizar: " + e.getMessage());
            } finally {
                this.desconectar();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No fue posible conectarse");
        }
    }

    @Override
    public DefaultComboBoxModel id() throws Exception {
        if (this.conectar()) {
            try {
                String sql = "select * from biblioteca.autor order by id_aut";
                DefaultComboBoxModel<String> idModel = new DefaultComboBoxModel();
                this.sqlConsulta = conexion.prepareStatement(sql);
                this.Registros = this.sqlConsulta.executeQuery();
                this.lista  = this.Registros.getMetaData();
                
                String id = "Id Autor";
                idModel.addElement(id);
                while (this.Registros.next()) {
                    id = this.Registros.getString("id_aut");
                    idModel.addElement(id);
                }
                return idModel;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Surgio un error al obtener id de estudiantes" + e.getMessage());
            }
        }
        return null;
    }

    @Override
    public Object buscar(String nombre) throws Exception {
        Autor autor = new Autor();

        String consulta = "SELECT * FROM biblioteca.autor WHERE nombre = ?";
        if (this.conectar()) {
            try {
                this.sqlConsulta = conexion.prepareStatement(consulta);
                this.sqlConsulta.setString(1, nombre);
                this.Registros = this.sqlConsulta.executeQuery();
                if (this.Registros.next()) {
                    autor.setNombre(this.Registros.getString("nombre"));
                    autor.setFechNac(this.Registros.getString("fech_nac"));
                    autor.setNacimiento(this.Registros.getString("Nac"));
                    autor.setDescripcion(this.Registros.getString("descrip"));
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Surgió un error al buscar: " + e.getMessage());
            } finally {
                this.desconectar();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No fue posible conectarse");
        }

        return autor;
    }

    @Override
    public void deshabilitar(String id) throws Exception {
        String consulta = "UPDATE pruebabibli.autor SET status = 0 WHERE id_aut = ?";

        if (this.conectar()) {
            try {
                this.sqlConsulta = conexion.prepareStatement(consulta);
                this.sqlConsulta.setString(1, id);
                this.sqlConsulta.executeUpdate();
                JOptionPane.showMessageDialog(null, "Registro deshabilitado correctamente");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Surgió un error al deshabilitar: " + e.getMessage());
            } finally {
                this.desconectar();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No fue posible conectarse");
        }
    }

    @Override
    public void Devolucion(Object objecto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Detallesprest(Object objecto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object buscar(int codigo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
