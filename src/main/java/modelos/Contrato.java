package modelos;

import java.time.LocalDate;

public class Contrato {

    private int idContrato;
    private int idUsuario;
    private int idPropiedad;
    private int idArrendatario;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int fianza;
    private int mensualidad;

    public Contrato() {
    }

    public Contrato(int idContrato, int idUsuario, int idPropiedad, int idArrendatario, LocalDate fechaInicio, LocalDate fechaFin, int fianza, int mensualidad) {
        this.idContrato = idContrato;
        this.idUsuario = idUsuario;
        this.idPropiedad = idPropiedad;
        this.idArrendatario = idArrendatario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fianza = fianza;
        this.mensualidad = mensualidad;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(int idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public int getIdArrendatario() {
        return idArrendatario;
    }

    public void setIdArrendatario(int idArrendatario) {
        this.idArrendatario = idArrendatario;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getFianza() {
        return fianza;
    }

    public void setFianza(int fianza) {
        this.fianza = fianza;
    }

    public int getMensualidad() {
        return mensualidad;
    }

    public void setMensualidad(int mensualidad) {
        this.mensualidad = mensualidad;
    }

}
