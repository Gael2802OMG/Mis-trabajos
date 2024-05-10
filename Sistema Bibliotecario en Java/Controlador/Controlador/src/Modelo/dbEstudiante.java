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
public class dbEstudiante extends dbManejador implements dbPersistencia {

    Estudiante est;
    dbDevoluciones dbDev;

    public void insertar(Object objecto) throws Exception {
        Estudiante estudiante = new Estudiante();
        estudiante = (Estudiante) objecto;

            String consulta = "INSERT INTO biblioteca.estudiante(matricula, nombre, correo, carrera, tel_est, adeudos,Estatus) VALUES (?, ?, ?, ?, ?, ?, ?);";
            if (this.conectar()) {
                try {
                    this.sqlConsulta = conexion.prepareStatement(consulta);
                    this.sqlConsulta.setString(1, estudiante.getMatricula());
                    this.sqlConsulta.setString(2, estudiante.getNombre());
                    this.sqlConsulta.setString(3, estudiante.getCorreo());
                    this.sqlConsulta.setString(4, estudiante.getCarrera());
                    this.sqlConsulta.setString(5, estudiante.getTelefono());
                    this.sqlConsulta.setFloat(6, estudiante.getAdeudo());
                    this.sqlConsulta.setInt(7, estudiante.getEstatus());

                    this.sqlConsulta.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Estudiante insertado correctamente");
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
    public void actualizar(Object objecto) throws Exception {
        if (objecto instanceof Devoluciones) {
            Devoluciones devolucion = (Devoluciones) objecto;

            float updatedAdeudo = dbDev.getNuevoAdeudo();

            String consulta = "UPDATE biblioteca.devoluciones SET adeudo = ? WHERE matricula = ?";
            if (this.conectar()) {
                try {
                    this.sqlConsulta = conexion.prepareStatement(consulta);
                    this.sqlConsulta.setFloat(1, updatedAdeudo);
                    this.sqlConsulta.setString(2, this.est.getMatricula());

                    this.sqlConsulta.executeUpdate();
                    this.est.setAdeudo(updatedAdeudo);  // Actualiza el valor del atributo en memoria
                    JOptionPane.showMessageDialog(null, "Adeudo actualizado correctamente");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Surgió un error al actualizar: " + e.getMessage());
                } finally {
                    this.desconectar();
                }
            } else {
                JOptionPane.showMessageDialog(null, "No fue posible conectarse");
            }
        } else {
            throw new IllegalArgumentException("Tipo de objeto no válido para actualizar");
        }
    }

    @Override
    public DefaultComboBoxModel id() throws Exception {
        if (this.conectar()) {
            try {
                String sql = "select * from biblioteca.estudiante order by id_est";
                DefaultComboBoxModel<String> idModel = new DefaultComboBoxModel();
                this.sqlConsulta = conexion.prepareStatement(sql);
                this.Registros = this.sqlConsulta.executeQuery();
                this.lista  = this.Registros.getMetaData();
                
                String id = "Id Estudiante";
                idModel.addElement(id);
                while (this.Registros.next()) {
                    id = this.Registros.getString("id_est");
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
public Object buscar(String matricula) throws Exception {
    Estudiante estudianteEncontrado = new Estudiante();

    String consulta = "SELECT * FROM biblioteca.estudiante WHERE matricula = ? AND Estatus = 0";
    if (this.conectar()) {
        try {
            this.sqlConsulta = conexion.prepareStatement(consulta);
            this.sqlConsulta.setString(1, matricula);
            this.Registros  = this.sqlConsulta.executeQuery();

            if (this.Registros .next()) {
                estudianteEncontrado = new Estudiante();
                estudianteEncontrado.setMatricula(this.Registros .getString("matricula"));
                estudianteEncontrado.setNombre(this.Registros .getString("nombre"));
                estudianteEncontrado.setCorreo(this.Registros .getString("correo"));
                estudianteEncontrado.setCarrera(this.Registros .getString("carrera"));
                estudianteEncontrado.setTelefono(this.Registros .getString("tel_est"));
                estudianteEncontrado.setAdeudo(this.Registros .getFloat("adeudos"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Surgió un error al buscar: " + e.getMessage());
        } finally {
            this.desconectar();
        }
    } else {
        JOptionPane.showMessageDialog(null, "No fue posible conectarse");
    }

    return estudianteEncontrado;
}

    @Override
    public void deshabilitar(String Matricula) throws Exception {
        String consulta = "UPDATE biblioteca.estudiante SET Estatus = 1 WHERE matricula = ?";

        if (this.conectar()) {
            try {
                this.sqlConsulta = conexion.prepareStatement(consulta);
                this.sqlConsulta.setString(1, Matricula);
                this.sqlConsulta.executeUpdate();
                JOptionPane.showMessageDialog(null, "Estudiante deshabilitado correctamente");
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
