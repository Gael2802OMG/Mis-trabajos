/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jovani
 */
public class Estudiante {
    private String Matricula;
    private String nombre;
    private String correo;
    private String carrera;
    private String telefono;
    private int Estatus;
    private float adeudo=0;

    public Estudiante(String Matricula, String nombre, String correo, String carrera, String telefono, int Estatus) {
        this.Matricula = Matricula;
        this.nombre = nombre;
        this.correo = correo;
        this.carrera = carrera;
        this.telefono = telefono;
        this.Estatus = Estatus;
    }
    
    public Estudiante() {
        this.Matricula ="";
        this.nombre = "";
        this.correo = "";
        this.carrera = "";
        this.telefono = "";
        this.Estatus = 0;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEstatus() {
        return Estatus;
    }

    public void setEstatus(int Estatus) {
        this.Estatus = Estatus;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public float getAdeudo() {
        return adeudo;
    }

    public void setAdeudo(float adeudo) {
        this.adeudo = adeudo;
    }
    
}
