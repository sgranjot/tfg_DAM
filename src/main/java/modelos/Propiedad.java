package modelos;

public class Propiedad {

    private int idPropiedad;
    private int idUsuario;
    private int idDireccion;
    private int idTipoPropiedad;
    private boolean libre;

    public Propiedad() {
    }

    public Propiedad(int idPropiedad, int idUsuario, int idDireccion, int idTipoPropiedad, boolean libre) {
        this.idPropiedad = idPropiedad;
        this.idUsuario = idUsuario;
        this.idDireccion = idDireccion;
        this.idTipoPropiedad = idTipoPropiedad;
        this.libre = libre;
    }

    public int getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(int idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public int getIdTipoPropiedad() {
        return idTipoPropiedad;
    }

    public void setIdTipoPropiedad(int idTipoPropiedad) {
        this.idTipoPropiedad = idTipoPropiedad;
    }

    public boolean isLibre() {
        return libre;
    }

    public void setLibre(boolean libre) {
        this.libre = libre;
    }

}
