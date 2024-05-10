package Modelo;

public class Prestamos {
    private int idPrest;
    private String fechaInicio;
    private String fechaFin;
    private String codigo;
    private int idEst;
    private int idPer;
    private int idLib;

    // Constructor
    public Prestamos(int idPrest, String fechaInicio, String fechaFin, String codigo, int idEst, int idPer, int idLib) {
        this.idPrest = idPrest;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.codigo = codigo;
        this.idEst = idEst;
        this.idPer = idPer;
        this.idLib = idLib;
    }
    public Prestamos(){
        this.idPrest = 0;
        this.fechaInicio = "";
        this.fechaFin = "";
        this.codigo = "";
        this.idEst = 0;
        this.idPer = 0;
    }
    // Getters and setters
    public int getIdPrest() {
        return idPrest;
    }

    public void setIdPrest(int idPrest) {
        this.idPrest = idPrest;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public int getIdEst() {
        return idEst;
    }

    public void setIdEst(int idEst) {
        this.idEst = idEst;
    }

    public int getIdPer() {
        return idPer;
    }

    public void setIdPer(int idPer) {
        this.idPer = idPer;
    }

    public int getIdLib() {
        return idLib;
    }

    public void setIdLib(int idLib) {
        this.idLib = idLib;
    }
    
    
}

