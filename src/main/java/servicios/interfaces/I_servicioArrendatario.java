package servicios.interfaces;

import java.util.List;
import modelos.Arrendatario;
import modelos.Usuario;

public interface I_servicioArrendatario {

    public int altaArrendatario(Arrendatario a);

    public Arrendatario getArrendatario(int id);

    public List<Arrendatario> getAllArrendatarios();

    public int actualizaArrendatario(Arrendatario a);

    public int eliminaArrendatario(Arrendatario a);

    public int eliminarTodos();

    public int getId(int idUsuario, String nombre, String apellidos, String dni, String telefono, String email);

    public List<Arrendatario> getArrendatariosDeUsuario(Usuario u);
}
