/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class dbDevoluciones extends dbManejador implements dbPersistencia {
    Devoluciones dev;
    Autor autor;

    @Override
    public void insertar(Object objecto) throws Exception {
        Devoluciones devolucion = new Devoluciones(); // Assuming the object is of type Devolucion
        devolucion = (Devoluciones)objecto;

        String consulta = "INSERT INTO biblioteca.devoluciones(codigo, id_pres, fechadev, costodia, subtotal, diasderetra, iva) VALUES (?, ?, ?, ?, ?, ?, ?);";
        if (this.conectar()) {
            try {
                this.sqlConsulta = conexion.prepareStatement(consulta);
                this.sqlConsulta.setString(1, devolucion.getCodigo());
                this.sqlConsulta.setInt(2, devolucion.getId_dpres());
                this.sqlConsulta.setString(3, devolucion.getFechadev());
                this.sqlConsulta.setFloat(4, devolucion.getCostdia());
                this.sqlConsulta.setFloat(5, devolucion.getSubtotal());
                this.sqlConsulta.setInt(6, devolucion.getDiasretra());
                this.sqlConsulta.setFloat(7, devolucion.getIva());
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
    public void actualizar(Object objecto) throws Exception {
        Devoluciones dev = new Devoluciones();
        dev = (Devoluciones) objecto;

        String consulta = "update biblioteca.devoluciones set id_pres = ?, fechadev = ?, costodia= ?, fech_p= ?, subtotal=?, diasderetra= ?, iva= ?, where codigo = ?";

        if (this.conectar()) {
            try {
                this.sqlConsulta = conexion.prepareStatement(consulta);
                this.sqlConsulta.setInt(1, dev.getId_dpres());
                this.sqlConsulta.setString(2, dev.getFechadev());
                this.sqlConsulta.setFloat(3, dev.getCostdia());
                this.sqlConsulta.setFloat(4, dev.getSubtotal());
                this.sqlConsulta.setInt(5, dev.getDiasretra());
                this.sqlConsulta.setFloat(6, dev.getIva());
                this.sqlConsulta.setString(7, dev.getCodigo());
                this.sqlConsulta.executeUpdate();

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
                DefaultComboBoxModel id = new DefaultComboBoxModel();
                this.sqlConsulta = conexion.prepareStatement(sql);
                this.Registros = this.sqlConsulta.executeQuery();
                this.lista  = this.Registros.getMetaData();

                //Agregar Columnas
                /*for (int column = 1; column <= this.lista.getColumnCount(); column++) {
                    id.(this.lista.getColumnLabel(column));
                }*/
                //Agregar Tablas
                while (this.Registros.next()) {
                    Object[] row = new Object[this.lista.getColumnCount()];
                    for (int column = 1; column <= this.lista.getColumnCount(); column++) {
                        row[column - 1] = this.Registros.getString(column);
                        System.out.println(row[column-1]);
                    }
                    /*id.addRow(row);*/
                }
                return id;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Surgio un error al listar" + e.getMessage());
            }
        }
        return null;
    }

    @Override
    public Object buscar(String codigo) throws Exception {
        Devoluciones dev = new Devoluciones();

        String consulta = "SELECT * FROM biblioteca.devoluciones WHERE codigo = ?";
        if (this.conectar()) {
            try {
                this.sqlConsulta = conexion.prepareStatement(consulta);
                this.sqlConsulta.setString(1, codigo);
                this.Registros = this.sqlConsulta.executeQuery();

                if (this.Registros.next()) {
                    dev.setCodigo(this.Registros.getString("codigo"));
                    dev.setId_dpres(this.Registros.getInt("id_pres"));
                    dev.setFechadev(this.Registros.getString("fechadev"));
                    dev.setCostdia(this.Registros.getFloat("costodia"));
                    dev.setSubtotal(this.Registros.getFloat("subtotal"));
                    dev.setDiasretra(this.Registros.getInt("diasderetra"));
                    dev.setIva(this.Registros.getFloat("iva"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Surgió un error al buscar: " + e.getMessage());
            } 
            this.desconectar();
            return dev;
            
        } else {
            JOptionPane.showMessageDialog(null, "No fue posible conectarse");
        }

        return null;
        
    }

    @Override
    public void deshabilitar(String codigo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Devolucion(Object objecto) throws Exception {
        Devoluciones devolucion = new Devoluciones(); // Assuming the object is of type Devolucion

        String consulta = "UPDATE devoluciones SET diasretra = ?, costdia = ?, subtotal = ? WHERE id_dev = ?";
        if (this.conectar()) {
            try {
                this.sqlConsulta = conexion.prepareStatement(consulta);

                // Update days of delay and cost
                int newDiasRetra = 0; // Calculate the new value for diasretra
                double newCostDia = 0; // Calculate the new value for costdia

                this.sqlConsulta.setInt(1, newDiasRetra);
                this.sqlConsulta.setDouble(2, newCostDia);

                double newSubtotal = newCostDia * newDiasRetra;
                this.sqlConsulta.setDouble(3, newSubtotal);

                // Set id_dev for updating specific row
                this.sqlConsulta.setString(4, devolucion.getId_dev());

                this.sqlConsulta.executeUpdate();
                JOptionPane.showMessageDialog(null, "Devolución modificada correctamente");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Surgió un error al modificar la devolución: " + e.getMessage());
            } finally {
                this.desconectar();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No fue posible conectarse");
        }
    }

    @Override
    public void Detallesprest(Object objecto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public float getNuevoAdeudo() throws SQLException {
        float nuevoAdeudo = 0;

        String consulta = "SELECT adeudo FROM devoluciones WHERE id_est = ?";
        if (this.conectar()) {
            try {
                this.sqlConsulta = conexion.prepareStatement(consulta);
                this.sqlConsulta.setString(1, autor.getId_aut());

                this.Registros = this.sqlConsulta.executeQuery();
                if (this.Registros.next()) {
                    float adeudoActual = this.Registros.getFloat("adeudo");
                    float costoDevolucion = calcularCostoDevolucion();
                    nuevoAdeudo = adeudoActual - costoDevolucion;
                }
            } catch (SQLException e) {
                throw e;
            } finally {
                this.desconectar();
            }
        } else {
            throw new SQLException("No fue posible conectarse");
        }

        return nuevoAdeudo;
    }

    public float calcularCostoDevolucion() {
        // Supongamos que el costo se calcula en función de días de retraso y un costo por día
        int diasRetraso = obtenerDiasRetraso(); // Implementa este método para obtener los días de retraso
        float costoPorDia = obtenerCostoPorDia(); // Implementa este método para obtener el costo por día

        float costoDevolucion = diasRetraso * costoPorDia;

        return costoDevolucion;
    }

    private int obtenerDiasRetraso() {
       
       int diasretra=dev.getDiasretra();
        return diasretra;
    }

    private float obtenerCostoPorDia() {
        float ctpd= dev.getCostdia();
        return ctpd;
    }

    @Override
    public Object buscar(int codigo) throws Exception {
        Devoluciones dev = new Devoluciones();

        String consulta = "SELECT * FROM biblioteca.devoluciones WHERE id_pres = ?";
        if (this.conectar()) {
            try {
                this.sqlConsulta = conexion.prepareStatement(consulta);
                this.sqlConsulta.setInt(1, codigo);
                this.Registros = this.sqlConsulta.executeQuery();

                if (this.Registros.next()) {
                    dev.setCodigo(this.Registros.getString("codigo"));
                    dev.setId_dpres(this.Registros.getInt("id_pres"));
                    dev.setFechadev(this.Registros.getString("fechadev"));
                    dev.setCostdia(this.Registros.getFloat("costodia"));
                    dev.setSubtotal(this.Registros.getFloat("subtotal"));
                    dev.setDiasretra(this.Registros.getInt("diasderetra"));
                    dev.setIva(this.Registros.getFloat("iva"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Surgió un error al buscar: " + e.getMessage());
            } 
            this.desconectar();
            return dev;
            
        } else {
            JOptionPane.showMessageDialog(null, "No fue posible conectarse");
        }

        return null;
    }

}
