package repositorios.interfaces;

import java.util.List;
import modelos.Usuario;

public interface I_usuarioDao {

    public int altaUsuario(Usuario u);

    public int actualizaContrasenia(Usuario u);

    public int eliminarUsuario(Usuario u);

    public int eliminarTodos();

    public List<Usuario> mostrarTodos();

    public Usuario recuperarUsuario(String nombre);

    public int darDeBajaUsuario(Usuario u);

}
