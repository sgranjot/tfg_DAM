package modelos;

import java.time.LocalDate;

public class Gasto {

    private int idGasto;
    private int idUsuario;
    private int idTipoGasto;
    private int idPropiedad;
    private double importe;
    private LocalDate fecha;
    private String descripcion;

    public Gasto() {
    }

    public Gasto(int idGasto, int idUsuario, int idTipoGasto, int idPropiedad, double importe, LocalDate fecha, String descripcion) {
        this.idGasto = idGasto;
        this.idUsuario = idUsuario;
        this.idTipoGasto = idTipoGasto;
        this.idPropiedad = idPropiedad;
        this.importe = importe;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public int getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(int idGasto) {
        this.idGasto = idGasto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdTipoGasto() {
        return idTipoGasto;
    }

    public void setIdTipoGasto(int idTipoGasto) {
        this.idTipoGasto = idTipoGasto;
    }

    public int getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(int idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
