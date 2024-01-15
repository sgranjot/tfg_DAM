package modelos;

public class Direccion {

    private int idDireccion;
    private String calle;
    private String numero;
    private String piso;
    private String puerta;
    private String codigoPostal;
    private String poblacion;
    private String provincia;
    private String pais;
    private String numeroPlaza;

    public Direccion() {
    }

    public Direccion(String calle, String numero, String piso, String puerta, String codigoPostal, String poblacion, String provincia, String pais, String numeroPlaza) {
        this.calle = calle;
        this.numero = numero;
        this.piso = piso;
        this.puerta = puerta;
        this.codigoPostal = codigoPostal;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.pais = pais;
        this.numeroPlaza = numeroPlaza;
    }
    
    

    public Direccion(int idDireccion, String calle, String numero, String piso, String puerta, String codigoPostal, String poblacion, String provincia, String pais, String numeroPlaza) {
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.numero = numero;
        this.piso = piso;
        this.puerta = puerta;
        this.codigoPostal = codigoPostal;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.pais = pais;
        this.numeroPlaza = numeroPlaza;
    }

    public Direccion(int idDireccion, String calle, String numero, String piso, String puerta, String codigoPostal, String poblacion, String provincia, String pais) {
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.numero = numero;
        this.piso = piso;
        this.puerta = puerta;
        this.codigoPostal = codigoPostal;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.pais = pais;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getPuerta() {
        return puerta;
    }

    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNumeroPlaza() {
        return numeroPlaza;
    }

    public void setNumeroPlaza(String numeroPlaza) {
        this.numeroPlaza = numeroPlaza;
    }

}
