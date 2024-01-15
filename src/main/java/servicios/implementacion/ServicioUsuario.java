package servicios.implementacion;

import repositorios.implementacion.UsuarioDao;
import java.util.List;
import modelos.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import servicios.interfaces.I_servicioUsuario;

public class ServicioUsuario implements I_servicioUsuario {

    private UsuarioDao usuarioDao;
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Usuario> mapeadorUsuario;

    public ServicioUsuario() {
    }

    public ServicioUsuario(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public ServicioUsuario(UsuarioDao usuarioDao, JdbcTemplate jdbcTemplate, RowMapper<Usuario> mapeadorUsuario) {
        this.usuarioDao = usuarioDao;
        this.jdbcTemplate = jdbcTemplate;
        this.mapeadorUsuario = mapeadorUsuario;
    }

    @Override
    public int altaUsuario(Usuario u) {
        return usuarioDao.altaUsuario(u);
    }

    @Override
    public int actualizaContrasenia(Usuario u) {
        return usuarioDao.actualizaContrasenia(u);
    }

    @Override
    public int eliminarUsuario(Usuario u) {
        return usuarioDao.eliminarUsuario(u);
    }

    @Override
    public int eliminarTodos() {
        return usuarioDao.eliminarTodos();
    }

    @Override
    public List<Usuario> mostrarTodos() {
        return usuarioDao.mostrarTodos();
    }

    @Override
    public Usuario recuperarUsuario(String nombre) {
        return usuarioDao.recuperarUsuario(nombre);
    }

    @Override
    public int darDeBajaUsuario(Usuario u) {
        return usuarioDao.darDeBajaUsuario(u);
    }

}
