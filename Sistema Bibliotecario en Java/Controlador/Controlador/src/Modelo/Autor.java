/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author jovani
 */
public class Autor {

   private String id_aut = "aut-000";
   private String nombre = "";
   private String fechNac = "";
   private String nacimiento = "";
   private String descripcion = "";
   


    public String getFechNac() {
        return fechNac;
    }

    public void setFechNac(String fechNac) {
        this.fechNac = fechNac;
    }

    public Autor() {
    }

    public Autor(String id_aut, String nombre, String fechNac, String nacimiento, String descripcion) {
        this.id_aut = id_aut;
        this.nombre = nombre;
        this.fechNac = fechNac;
        this.nacimiento = nacimiento;
        this.descripcion = descripcion;
    }

    public String getId_aut() {
        return id_aut;
    }

    public void setId_aut(String id_aut) {
        this.id_aut = id_aut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

 

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
