package Modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class dbPrestamos extends dbManejador implements dbPersistencia {

    @Override
    public void insertar(Object objecto) throws Exception {
        if (objecto instanceof Prestamos) {
            Prestamos prestamo = (Prestamos) objecto;

            String consulta = "INSERT INTO biblioteca.prestamos(codigo, fechini, fechfin, id_lib, id_est, id_per) VALUES (?, ?, ?, ?, ?, ?)";
            if (this.conectar()) {
                try {
                    this.sqlConsulta = conexion.prepareStatement(consulta);
                    this.sqlConsulta.setString(1, prestamo.getCodigo());
                    this.sqlConsulta.setString(2, prestamo.getFechaInicio());
                    this.sqlConsulta.setString(3, prestamo.getFechaFin());
                    this.sqlConsulta.setInt(4, prestamo.getIdLib());
                    this.sqlConsulta.setInt(5, prestamo.getIdEst());
                    this.sqlConsulta.setInt(6, prestamo.getIdPer());
                    this.sqlConsulta.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Préstamo registrado correctamente");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Surgió un error al insertar: " + e.getMessage());
                } finally {
                    this.desconectar();
                }
            } else {
                JOptionPane.showMessageDialog(null, "No fue posible conectarse");
            }
        } else {
            throw new IllegalArgumentException("Invalid object type for insertar");
        }
    }

    @Override
    public void Prestamo(Object objecto) throws Exception {
        if (objecto instanceof Prestamos) {
            Prestamos prestamo = (Prestamos) objecto;

            // Obtener la id del libro prestado
            int idPrestado = prestamo.getIdPrest();

            // Restar uno a la cantidad de libros por id
            restarCantidadLibros(idPrestado);

            // Asignar la id del libro prestado a detalleprest
            asignarDetallePrest(idPrestado);

            JOptionPane.showMessageDialog(null, "Préstamo realizado con éxito");
        } else {
            throw new IllegalArgumentException("Invalid object type for Prestamo");
        }
    }

    private void restarCantidadLibros(int idLibro) throws SQLException {
        String consulta = "UPDATE libro SET num_ejem = num_ejem - 1 WHERE id_lib = ?";

        if (this.conectar()) {
            try {
                PreparedStatement statement = conexion.prepareStatement(consulta);
                statement.setInt(1, idLibro);

                statement.executeUpdate();
            } catch (SQLException e) {
                throw e;
            } finally {
                this.desconectar();
            }
        } else {
            throw new SQLException("No fue posible conectarse");
        }
    }
 @Override
    public void actualizar(Object objecto) throws Exception {
        if (objecto instanceof Prestamos) {
            Prestamos prestamo = (Prestamos) objecto;

            String consulta = "UPDATE biblioteca.prestamos SET fechini = ?, fechfin = ?, id_lib = ?, id_est = ?, id_per = ? WHERE codigo = ?";
            if (this.conectar()) {
                try {
                    this.sqlConsulta = conexion.prepareStatement(consulta);
                    this.sqlConsulta.setString(1, prestamo.getFechaInicio());
                    this.sqlConsulta.setString(2, prestamo.getFechaFin());
                    this.sqlConsulta.setInt(3, prestamo.getIdLib());
                    this.sqlConsulta.setInt(4, prestamo.getIdEst());
                    this.sqlConsulta.setInt(5, prestamo.getIdPer());
                    this.sqlConsulta.setString(6, prestamo.getCodigo());

                    this.sqlConsulta.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Préstamo actualizado correctamente");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Surgió un error al actualizar: " + e.getMessage());
                } finally {
                    this.desconectar();
                }
            } else {
                JOptionPane.showMessageDialog(null, "No fue posible conectarse");
            }
        } else {
            throw new IllegalArgumentException("Invalid object type for actualizar");
        }
    }

    @Override
    public DefaultComboBoxModel id() throws Exception {
        if (this.conectar()) {
            try {
                String sql = "select * from biblioteca.prestamos order by id_prest";
                DefaultComboBoxModel<String> idModel = new DefaultComboBoxModel();
                this.sqlConsulta = conexion.prepareStatement(sql);
                this.Registros = this.sqlConsulta.executeQuery();
                this.lista  = this.Registros.getMetaData();
                
                String id = "Id Prestamos";
                idModel.addElement(id);
                while (this.Registros.next()) {
                    id = this.Registros.getString("id_prest");
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
    public Object buscar(String codigo) throws Exception {
        if (this.conectar()) {
            try {
                String sql = "SELECT * FROM biblioteca.prestamos WHERE codigo = ?";
                this.sqlConsulta = conexion.prepareStatement(sql);
                this.sqlConsulta.setString(1, codigo);
                this.Registros = this.sqlConsulta.executeQuery();

                if (this.Registros.next()) {
                    Prestamos prestamo = new Prestamos();
                    prestamo.setCodigo(this.Registros.getString("codigo"));
                    prestamo.setFechaInicio(this.Registros.getString("fechini"));
                    prestamo.setFechaFin(this.Registros.getString("fechfin"));
                    prestamo.setIdLib(this.Registros.getInt("id_lib"));
                    prestamo.setIdEst(this.Registros.getInt("id_est"));
                    prestamo.setIdPer(this.Registros.getInt("id_per"));
                    return prestamo;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Surgió un error al buscar: " + e.getMessage());
            } finally {
                this.desconectar();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No fue posible conectarse");
        }
        return null;
    }

    @Override
    public void deshabilitar(String codigo) throws Exception {
        String consulta = "DELETE FROM biblioteca.prestamos WHERE codigo = ?";

        if (this.conectar()) {
            try {
                this.sqlConsulta = conexion.prepareStatement(consulta);
                this.sqlConsulta.setString(1, codigo);
                this.sqlConsulta.executeUpdate();
                JOptionPane.showMessageDialog(null, "Préstamo eliminado correctamente");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Surgió un error al eliminar: " + e.getMessage());
            } finally {
                this.desconectar();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No fue posible conectarse");
        }
    }
    
    @Override
    public void Devolucion(Object objecto) throws Exception {
        // Implementa la lógica de devolución
    }

    @Override
    public void Detallesprest(Object objecto) throws Exception {
        // Implementa la lógica de detalles de préstamo
    }

    private void asignarDetallePrest(int idPrestado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object buscar(int codigo) throws Exception {
       if (this.conectar()) {
            try {
                String sql = "SELECT * FROM biblioteca.prestamos WHERE codigo = ?";
                this.sqlConsulta = conexion.prepareStatement(sql);
                this.sqlConsulta.setInt(1, codigo);
                this.Registros = this.sqlConsulta.executeQuery();

                if (this.Registros.next()) {
                    Prestamos prestamo = new Prestamos();
                    prestamo.setCodigo(this.Registros.getString("codigo"));
                    prestamo.setFechaInicio(this.Registros.getString("fechini"));
                    prestamo.setFechaFin(this.Registros.getString("fechfin"));
                    prestamo.setIdEst(this.Registros.getInt("id_est"));
                    prestamo.setIdPer(this.Registros.getInt("id_per"));
                    return prestamo;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Surgió un error al buscar: " + e.getMessage());
            } finally {
                this.desconectar();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No fue posible conectarse");
        }
        return null;
    }
}
