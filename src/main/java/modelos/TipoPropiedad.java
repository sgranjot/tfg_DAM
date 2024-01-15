package modelos;

public class TipoPropiedad {

    private int idTipoPropiedad;
    private String tipoPropiedad;

    public TipoPropiedad() {
    }

    public TipoPropiedad(int idTipoPropiedad, String tipoPropiedad) {
        this.idTipoPropiedad = idTipoPropiedad;
        this.tipoPropiedad = tipoPropiedad;
    }

    public int getIdTipoPropiedad() {
        return idTipoPropiedad;
    }

    public void setIdTipoPropiedad(int idTipoPropiedad) {
        this.idTipoPropiedad = idTipoPropiedad;
    }

    public String getTipoPropiedad() {
        return tipoPropiedad;
    }

    public void setTipoPropiedad(String tipoPropiedad) {
        this.tipoPropiedad = tipoPropiedad;
    }

}
