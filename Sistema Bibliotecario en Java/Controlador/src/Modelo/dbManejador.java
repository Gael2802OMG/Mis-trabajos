package Modelo;
import java.sql.ResultSetMetaData;

import java.net.URL;
import java.sql.*;

public class dbManejador {

    protected Connection conexion;
    protected PreparedStatement sqlConsulta;
    protected ResultSet Registros;
    protected ResultSetMetaData lista;
    private String usuario;
    private String database;
    private String contraseña;
    private String drive;
    private String url;

    public dbManejador() {
        this.drive = "com.mysql.cj.jdbc.Driver";
        this.database = "pruebabibli";
        this.usuario = "root";
        this.contraseña = "";
        this.url = "jdbc:mysql://localhost:3306/pruebabibli";

        isDrive();
    }

    public dbManejador(Connection conexion, PreparedStatement sqlConsulta, ResultSet Registros, String usuario, String database, String contraseña, String drive, String url) {
        this.conexion = conexion;
        this.sqlConsulta = sqlConsulta;
        this.Registros = Registros;
        this.usuario = usuario;
        this.database = database;
        this.contraseña = contraseña;
        this.drive = drive;
        this.url = url;
        isDrive();
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public PreparedStatement getSqlConsulta() {
        return sqlConsulta;
    }

    public void setSqlConsulta(PreparedStatement sqlConsulta) {
        this.sqlConsulta = sqlConsulta;
    }

    public ResultSet getRegistros() {
        return Registros;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRegistros(ResultSet Registros) {
        this.Registros = Registros;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public boolean isDrive() {
        boolean exito = false;

        try {
            Class.forName(drive);
            exito = true;

        } catch (ClassNotFoundException e) {
            exito = false;
            System.err.println("ocurrio un error" + e.getMessage());
            System.exit(-1);
        }
        return exito;
    }

    public boolean conectar() throws SQLException {
        boolean exito = false;

        try {

            this.setConexion((DriverManager.getConnection(this.url, this.usuario, this.contraseña)));
            exito = true;
        } catch (SQLDataException e) {

            exito = false;
            System.err.print("surgio un error al conectar " + e.getMessage());

        }
        return exito;
    }

    public void desconectar() {
        try {
            if (this.conexion.isClosed()) {
                this.getConexion().close();
            }
        } catch (SQLException e) {
            System.err.println("error, no fue posible cerrar la conexion"
                    + e.getMessage());

        }
    }
}
