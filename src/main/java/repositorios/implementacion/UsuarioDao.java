package repositorios.implementacion;

import repositorios.interfaces.I_usuarioDao;
import modelos.Usuario;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import modelos.Propiedad;

public class UsuarioDao implements I_usuarioDao {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Usuario> mapeadorUsuario;
    private RowMapper<Propiedad> mapeadorPropiedad;

    public UsuarioDao() {
    }

    public UsuarioDao(JdbcTemplate jdbcTemplate, RowMapper<Usuario> mapeadorUsuario) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapeadorUsuario = mapeadorUsuario;
    }

    @Override
    public int altaUsuario(Usuario u) {
        try {
            String sql = "Insert into usuario values (null,?,?,?)";
            return jdbcTemplate.update(sql, u.getNombre(), u.getContrasenia(), u.isActivado());
        } catch (DataAccessException ex) {
            return 0;
        }

    }

    @Override
    public int actualizaContrasenia(Usuario u) {
        String sql = "UPDATE usuario SET contrasenia = ?, activado = ? where id_usuario = ?";
        return jdbcTemplate.update(sql, u.getContrasenia(), u.isActivado(), u.getIdUsuario());
    }

    @Override
    public int eliminarUsuario(Usuario u) {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        return jdbcTemplate.update(sql, u.getIdUsuario());
    }

    @Override
    public int eliminarTodos() {
        String sql = "DELETE FROM usuario";
        return jdbcTemplate.update(sql);
    }

    @Override
    public List<Usuario> mostrarTodos() {
        return jdbcTemplate.query("select * from usuario", mapeadorUsuario);
    }

    @Override
    public Usuario recuperarUsuario(String nombre) {
        List<Usuario> userList;
        userList = jdbcTemplate.query("select * from usuario where usuario = ?", mapeadorUsuario, nombre);
        Usuario user = new Usuario();
        for (Usuario u : userList) {

            user.setIdUsuario(u.getIdUsuario());
            user.setNombre(u.getNombre());
            user.setContrasenia(u.getContrasenia());
            user.setActivado(u.isActivado());
            return user;
        }
        return null;
    }

    @Override
    public int darDeBajaUsuario(Usuario u) {
        String sql = "UPDATE usuario SET contrasenia = ?, activado = false where id_usuario = ?";
        return jdbcTemplate.update(sql, u.getContrasenia(), u.getIdUsuario());
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RowMapper<Usuario> getMapeadorUsuario() {
        return mapeadorUsuario;
    }

    public void setMapeadorUsuario(RowMapper<Usuario> mapeadorUsuario) {
        this.mapeadorUsuario = mapeadorUsuario;
    }

}
