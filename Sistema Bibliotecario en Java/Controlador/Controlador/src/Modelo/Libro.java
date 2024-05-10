package Modelo;
import java.sql.Date;

public class Libro {
    private int id_lib;
    private String categ;
    private int id_aut;
    private String editorial;
    private String fech_p;
    private int num_pag;
    private int num_ejem;
    private String nombre;
    private String codigo;
    private float preciolibro;

    // Constructor
    public Libro(int id_lib,String codigo, String categ, int id_aut, String editorial, String fech_p, int num_pag, int num_ejem, String nombre, float preciolibro) {
        this.id_lib = id_lib;
        this.codigo = codigo;
        this.categ = categ;
        this.id_aut = id_aut;
        this.editorial = editorial;
        this.fech_p = fech_p;
        this.num_pag = num_pag;
        this.num_ejem = num_ejem;
        this.nombre = nombre;
        this.preciolibro = preciolibro;
    }

    public Libro() {
        this.id_lib = 0;
        this.codigo = "";
        this.categ = "";
        this.id_aut = 0;
        this.editorial = "";
        this.fech_p = "";
        this.num_pag = 0;
        this.num_ejem = 0;
        this.nombre = "";
        this.preciolibro = 0f;
    }
    
    

    // Getters and Setters
    public int getId_lib() {
        return id_lib;
    }

    public void setId_lib(int id_lib) {
        this.id_lib = id_lib;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }

    public int getAutor() {
        return id_aut;
    }

    public void setAutor(int autor) {
        this.id_aut = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getFech_p() {
        return fech_p;
    }

    public void setFech_p(String fech_p) {
        this.fech_p = fech_p;
    }



    public int getNum_pag() {
        return num_pag;
    }

    public void setNum_pag(int num_pag) {
        this.num_pag = num_pag;
    }

    public int getNum_ejem() {
        return num_ejem;
    }

    public void setNum_ejem(int num_ejem) {
        this.num_ejem = num_ejem;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPreciolibro() {
        return preciolibro;
    }

    public void setPreciolibro(float preciolibro) {
        this.preciolibro = preciolibro;
    }
}

