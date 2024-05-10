
package Modelo;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class dbMostrar extends dbManejador implements dbPersistenciaMostrar {

    @Override
    public DefaultTableModel listar(String sql) throws Exception {
        if (this.conectar()) {
            try {
                DefaultTableModel Tabla = new DefaultTableModel();
                this.sqlConsulta = conexion.prepareStatement(sql);
                this.Registros = this.sqlConsulta.executeQuery();
                this.lista = this.Registros.getMetaData();

                //Agregar Columnas
                for (int column = 1; column <= this.lista.getColumnCount(); column++) {
                    Tabla.addColumn(this.lista.getColumnLabel(column));
                }
                //Agregar Tablas
                while (this.Registros.next()) {
                    Object[] row = new Object[this.lista.getColumnCount()];
                    for (int column = 1; column <= this.lista.getColumnCount(); column++) {
                        row[column - 1] = this.Registros.getString(column);
                    }
                    Tabla.addRow(row);
                }
                return Tabla;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Surgio un error al listar" + e.getMessage());
            }
        }
        return null;
    }
    
}
