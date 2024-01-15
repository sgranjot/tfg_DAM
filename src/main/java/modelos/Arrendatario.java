package modelos;

public class Arrendatario {

    private int idArrendatario;
    private int idUsuario;
    private String nombre;
    private String apellidos;
    private String dni;
    private String telefono;
    private String email;

    public Arrendatario() {
    }

    public Arrendatario(int idUsuario, String nombre, String apellidos, String dni, String telefono, String email) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
    }
    
    

    public Arrendatario(int idArrendatario, int idUsuario, String nombre, String apellidos, String dni, String telefono, String email) {
        this.idArrendatario = idArrendatario;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
    }

    public int getIdArrendatario() {
        return idArrendatario;
    }

    public void setIdArrendatario(int idArrendatario) {
        this.idArrendatario = idArrendatario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
