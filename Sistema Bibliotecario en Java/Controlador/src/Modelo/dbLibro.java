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
public class dbLibro extends dbManejador implements dbPersistencia{

    @Override
public void insertar(Object objecto) throws Exception {
    Libro libro = (Libro) objecto; // Suponiendo que Libro es el tipo correcto

    String consulta = "INSERT INTO biblioteca.libro(codigo, categ, id_autor, editorial, fech_p, num_pag, num_ejem, nombre, preciolibro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    if (this.conectar()) {
        try {
            this.sqlConsulta = conexion.prepareStatement(consulta);
            this.sqlConsulta.setString(1, libro.getCodigo());
            this.sqlConsulta.setString(2, libro.getCateg());
            this.sqlConsulta.setInt(3, libro.getAutor());
            this.sqlConsulta.setString(4, libro.getEditorial());
            this.sqlConsulta.setString(5, libro.getFech_p());
            this.sqlConsulta.setInt(6, libro.getNum_pag());
            this.sqlConsulta.setInt(7, libro.getNum_ejem());
            this.sqlConsulta.setString(8, libro.getNombre());
            this.sqlConsulta.setFloat(9, libro.getPreciolibro());

            this.sqlConsulta.executeUpdate();
            JOptionPane.showMessageDialog(null, "Libro insertado cov   rrectamente");
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
    Libro lib = new Libro();
    lib = (Libro) objecto;
    
    String consulta = "update biblioteca.libro set categ = ?, id_autor = ?, editorial= ?, fech_p= ?, num_pag=?, num_ejem= ?, nombre= ?, preciolibro= ? where codigo = ?";
    
    if (this.conectar()) {
        try {
            this.sqlConsulta = conexion.prepareStatement(consulta);
            this.sqlConsulta.setString(1, lib.getCateg());
            this.sqlConsulta.setInt(2, lib.getAutor());
            this.sqlConsulta.setString(3, lib.getEditorial());
            this.sqlConsulta.setString(4, lib.getFech_p());
            this.sqlConsulta.setInt(5, lib.getNum_pag());
            this.sqlConsulta.setInt(6, lib.getNum_ejem());
            this.sqlConsulta.setString(7, lib.getNombre());
            this.sqlConsulta.setFloat(8, lib.getPreciolibro());
            this.sqlConsulta.setString(9, lib.getCodigo());
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
                String sql = "select * from biblioteca.libro order by id_lib";
                DefaultComboBoxModel<String> idModel = new DefaultComboBoxModel();
                this.sqlConsulta = conexion.prepareStatement(sql);
                this.Registros = this.sqlConsulta.executeQuery();
                this.lista  = this.Registros.getMetaData();
                
                String id = "Id Libros";
                idModel.addElement(id);
                while (this.Registros.next()) {
                    id = this.Registros.getString("id_lib");
                    idModel.addElement(id);
                }
                return idModel;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Surgio un error al obtener id de libros" + e.getMessage());
            }
        }
        return null;
    }

    @Override
    public Object buscar(String codigo) throws Exception {
        Libro lib = new Libro();

        String consulta = "SELECT * FROM biblioteca.libro WHERE codigo = ?";
        if (this.conectar()) {
            try {
                this.sqlConsulta = conexion.prepareStatement(consulta);
                this.sqlConsulta.setString(1, codigo);
                this.Registros = this.sqlConsulta.executeQuery();

                if (this.Registros.next()) {
                    lib = new Libro();
                    lib.setCodigo(this.Registros.getString("codigo"));
                    lib.setCateg(this.Registros.getString("categ"));
                    lib.setAutor(this.Registros.getInt("id_autor"));
                    lib.setEditorial(this.Registros.getString("editorial"));
                    lib.setFech_p(this.Registros.getString("fech_p"));
                    lib.setNum_pag(this.Registros.getInt("num_pag"));
                    lib.setNum_ejem(this.Registros.getInt("num_ejem"));
                    lib.setNombre(this.Registros.getString("nombre"));
                    lib.setPreciolibro(this.Registros.getFloat("preciolibro"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Surgió un error al buscar: " + e.getMessage());
            } finally {
                this.desconectar();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No fue posible conectarse");
        }

        return lib;
    }

    @Override
    public void deshabilitar(String codigo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
