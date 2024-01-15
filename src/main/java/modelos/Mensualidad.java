package modelos;

import java.sql.Date;

public class Mensualidad {

    private int idMensualidad;
    private int idUsuario;
    private int idContrato;
    private int cantidad;
    private Date fecha;
    private boolean estado;  //true=pagado

    public Mensualidad() {
    }

    public Mensualidad(int idMensualidad, int idUsuario, int idContrato, int cantidad, Date fecha, boolean estado) {
        this.idMensualidad = idMensualidad;
        this.idUsuario = idUsuario;
        this.idContrato = idContrato;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getIdMensualidad() {
        return idMensualidad;
    }

    public void setIdMensualidad(int idMensualidad) {
        this.idMensualidad = idMensualidad;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
