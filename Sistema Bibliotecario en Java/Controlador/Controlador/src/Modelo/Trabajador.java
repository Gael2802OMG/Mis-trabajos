/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jovani
 */
public class Trabajador {
    private String id_per;
    private String Matricula;
    private String nombre;
    private String contraseña;
    private String cargo;
    private String correo;
    private String tel_est;
    private String direccion;
    private int Estatus;

    public Trabajador(String id_per, String Matricula, String nombre, String contraseña, String cargo, String correo, String tel_est, String direccion, int Estatus) {
        this.id_per = id_per;
        this.Matricula = Matricula;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.cargo = cargo;
        this.correo = correo;
        this.tel_est = tel_est;
        this.direccion = direccion;
    }

    public Trabajador() {
        this.id_per = "";
        this.Matricula = "";
        this.nombre = "";
        this.contraseña = "";
        this.cargo = "";
        this.correo = "";
        this.tel_est ="";
        this.direccion = "";
        this.Estatus = 0;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
    public String getId_per() {
        return id_per;
    }

    public void setId_per(String id_per) {
        this.id_per = id_per;
    }

    public String getMatricula() {
        return Matricula;
    }

    public void setMatricula(String Matricula) {
        this.Matricula = Matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTel_est() {
        return tel_est;
    }

    public void setTel_est(String tel_est) {
        this.tel_est = tel_est;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEstatus() {
        return Estatus;
    }

    public void setEstatus(int Estatus) {
        this.Estatus = Estatus;
    }

    
}
