package modelos;

public class TipoGasto {

    private int idTipoGasto;
    private String tipoGasto;

    public TipoGasto() {
    }

    public TipoGasto(int idTipoGasto, String tipoGasto) {
        this.idTipoGasto = idTipoGasto;
        this.tipoGasto = tipoGasto;
    }

    public int getIdTipoGasto() {
        return idTipoGasto;
    }

    public void setIdTipoGasto(int idTipoGasto) {
        this.idTipoGasto = idTipoGasto;
    }

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

}
