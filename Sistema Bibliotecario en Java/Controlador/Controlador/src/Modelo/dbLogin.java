/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.SQLException;
import javax.swing.JOptionPane;

public class dbLogin extends dbManejador implements dbPersistenciaLogin {

    @Override
    public Object buscar(String Matricula, String Contraseña) throws Exception {
        Trabajador trabajador = new Trabajador();

        String consulta = "SELECT * FROM biblioteca.personal WHERE matricula = ? and contraseña = ? and Estatus = 0";
        if (this.conectar()) {
            try {
                this.sqlConsulta = conexion.prepareStatement(consulta);
                this.sqlConsulta.setString(1, Matricula);
                this.sqlConsulta.setString(2, Contraseña);
                this.Registros = this.sqlConsulta.executeQuery();

                if (this.Registros.next()) {
                    trabajador = new Trabajador();
                    trabajador.setMatricula(this.Registros.getString("Matricula"));
                    trabajador.setContraseña(this.Registros.getString("Contraseña"));

                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Surgió un error al verificar: " + e.getMessage());
            } finally {
                this.desconectar();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No fue posible conectarse");
        }

        return trabajador;
    }

}
