/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author jovani
 */
public class Devoluciones {

    private String id_dev;
    private int id_dpres;
    private String fechadev;
    private String codigo;
    private float costdia;
    private int diasretra;
    private float subtotal;
    private float iva;

    public Devoluciones() {
        this.id_dev = "";
        this.id_dpres = 0;
        this.fechadev = "";
        this.codigo = "";
        this.costdia = 0f;
        this.diasretra = 0;
        this.subtotal=0f;
        this.iva = 0.16f;
    }

    

    public Devoluciones(String id_dev, int id_dpres, String fechadev, String codigo, float costdia, int diasretra,float subtotal, float iva) {
        this.id_dev = id_dev;
        this.id_dpres = id_dpres;
        this.fechadev = fechadev;
        this.codigo = codigo;
        this.costdia = costdia;
        this.diasretra = diasretra;
        this.subtotal=subtotal;
        this.iva = iva;
    }

    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getId_dev() {
        return id_dev;
    }

    public void setId_dev(String id_dev) {
        this.id_dev = id_dev;
    }

    public int getId_dpres() {
        return id_dpres;
    }

    public void setId_dpres(int id_dpres) {
        this.id_dpres = id_dpres;
    }

    public String getFechadev() {
        return fechadev;
    }

    public void setFechadev(String fechadev) {
        this.fechadev = fechadev;
    }

    public float getCostdia() {
        return costdia;
    }

    public void setCostdia(float costdia) {
        this.costdia = costdia;
    }

    public int getDiasretra() {
        return diasretra;
    }

    public void setDiasretra(int diasretra) {
        this.diasretra = diasretra;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }
}
