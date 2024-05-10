/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jovani
 */
public class dbTrabajador extends dbManejador implements dbPersistencia {

    @Override
    public void insertar(Object objecto) throws Exception {
        Trabajador personal = new Trabajador();

        personal = (Trabajador) objecto;

        String consulta = "INSERT INTO biblioteca.personal(matricula, nombre, contraseña, cargo, correo, tel_est, direccion, Estatus) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";

        if (this.conectar()) {
            try {
                this.sqlConsulta = conexion.prepareStatement(consulta);
                this.sqlConsulta.setString(1, personal.getMatricula());
                this.sqlConsulta.setString(2, personal.getNombre());
                this.sqlConsulta.setString(3, personal.getContraseña());
                this.sqlConsulta.setString(4, personal.getCargo());
                this.sqlConsulta.setString(5, personal.getCorreo());
                this.sqlConsulta.setString(6, personal.getTel_est());
                this.sqlConsulta.setString(7, personal.getDireccion());
                this.sqlConsulta.setInt(8, personal.getEstatus());
                this.sqlConsulta.executeUpdate();

                this.desconectar();
                JOptionPane.showMessageDialog(null, "Registro insertado correctamente");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Surgió un error al insertar: " + e.getMessage());
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
    public void actualizar(Object objecto) throws Exception {
        Trabajador trabajador = (Trabajador) objecto;
        String consulta = "UPDATE biblioteca.personal SET nombre = ?, contraseña = ?, cargo = ?, correo = ?, tel_est = ?, direccion = ?, Estatus = ? WHERE Matricula = ?";
        if (this.conectar()) {
            try {
                this.sqlConsulta = conexion.prepareStatement(consulta);
                this.sqlConsulta.setString(1, trabajador.getNombre());
                this.sqlConsulta.setString(2, trabajador.getContraseña());
                this.sqlConsulta.setString(3, trabajador.getCargo());
                this.sqlConsulta.setString(4, trabajador.getCorreo());
                this.sqlConsulta.setString(5, trabajador.getTel_est());
                this.sqlConsulta.setString(6, trabajador.getDireccion());
                this.sqlConsulta.setInt(7, trabajador.getEstatus());
                this.sqlConsulta.setString(8, trabajador.getMatricula());

                this.sqlConsulta.executeUpdate();
                JOptionPane.showMessageDialog(null, "Trabajador actualizado correctamente");
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
                String sql = "select * from biblioteca.personal order by id_per";
                DefaultComboBoxModel<String> idModel = new DefaultComboBoxModel();
                this.sqlConsulta = conexion.prepareStatement(sql);
                this.Registros = this.sqlConsulta.executeQuery();
                this.lista = this.Registros.getMetaData();

                String id = "Id Personal";
                idModel.addElement(id);
                while (this.Registros.next()) {
                    id = this.Registros.getString("id_per");
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
    public Object buscar(String Matricula) throws Exception {
        Trabajador trabajador = new Trabajador();

        String consulta = "SELECT * FROM biblioteca.personal WHERE Matricula = ? and Estatus = 0";
        if (this.conectar()) {
            try {
                this.sqlConsulta = conexion.prepareStatement(consulta);
                this.sqlConsulta.setString(1, Matricula);
                this.Registros = this.sqlConsulta.executeQuery();

                if (this.Registros.next()) {
                    trabajador = new Trabajador();
                    trabajador.setMatricula(this.Registros.getString("Matricula"));
                    trabajador.setNombre(this.Registros.getString("nombre"));
                    trabajador.setContraseña(this.Registros.getString("contraseña"));
                    trabajador.setCargo(this.Registros.getString("cargo"));
                    trabajador.setCorreo(this.Registros.getString("correo"));
                    trabajador.setTel_est(this.Registros.getString("tel_est"));
                    trabajador.setDireccion(this.Registros.getString("direccion"));
                    trabajador.setEstatus(this.Registros.getInt("Estatus"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Surgió un error al buscar: " + e.getMessage());
            } finally {
                this.desconectar();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No fue posible conectarse");
        }

        return trabajador;
    }

    @Override
    public void deshabilitar(String matricula) throws Exception {
        String consulta = "UPDATE biblioteca.personal SET Estatus = 1 WHERE Matricula = ?";

        if (this.conectar()) {
            try {
                this.sqlConsulta = conexion.prepareStatement(consulta);
                this.sqlConsulta.setString(1, matricula);
                this.sqlConsulta.executeUpdate();
                JOptionPane.showMessageDialog(null, "Trabajador deshabilitado correctamente");
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
