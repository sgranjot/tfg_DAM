package repositorios.implementacion;

import java.util.List;
import repositorios.interfaces.I_arrendatarioDao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import modelos.Arrendatario;
import modelos.Usuario;

public class ArrendatarioDao implements I_arrendatarioDao {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Arrendatario> mapeadorArrendatario;

    public ArrendatarioDao() {
    }

    public ArrendatarioDao(JdbcTemplate jdbcTemplate, RowMapper<Arrendatario> mapeadorArrendatario) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapeadorArrendatario = mapeadorArrendatario;
    }

    @Override
    public int altaArrendatario(Arrendatario a) {
        try {
            String sql = "Insert into arrendatario values (null,?,?,?,?,?,?)";
            return jdbcTemplate.update(sql, a.getIdUsuario(), a.getNombre(), a.getApellidos(), a.getDni(),
                    a.getTelefono(), a.getEmail());
        } catch (DataAccessException ex) {
            return 0;
        }
    }

    @Override
    public Arrendatario getArrendatario(int id) {
        List<Arrendatario> arrendList;
        arrendList = jdbcTemplate.query("select * from arrendatario where id_arrendatario = ?", mapeadorArrendatario, id);
        Arrendatario arrendatario = new Arrendatario();
        for (Arrendatario a : arrendList) {

            arrendatario.setIdArrendatario(a.getIdArrendatario());
            arrendatario.setIdUsuario(a.getIdUsuario());
            arrendatario.setNombre(a.getNombre());
            arrendatario.setApellidos(a.getApellidos());
            arrendatario.setDni(a.getDni());
            arrendatario.setTelefono(a.getTelefono());
            arrendatario.setEmail(a.getEmail());
            return arrendatario;
        }
        return null;
    }

    @Override
    public List<Arrendatario> getAllArrendatarios() {
        return jdbcTemplate.query("select * from arrendatario", mapeadorArrendatario);
    }

    @Override
    public int getId(int idUsuario, String nombre, String apellidos, String dni, String telefono, String email) {
        for (Arrendatario a : getAllArrendatarios()) {
            if (a.getIdUsuario() == idUsuario && a.getNombre().equals(nombre) && a.getApellidos().equals(apellidos) && a.getDni().equals(dni)
                    && a.getTelefono().equals(telefono) && a.getEmail().equals(email)) {
                return a.getIdArrendatario();
            }
        }
        return 0;
    }

    @Override
    public int actualizaArrendatario(Arrendatario a) {
        String sql = "UPDATE arrendatario SET id_usuario = ?, nombre = ?, apellidos = ?, dni = ?,"
                + "telefono = ?, email = ? where id_arrendatario = ?";
        return jdbcTemplate.update(sql, a.getIdUsuario(), a.getNombre(), a.getApellidos(), a.getDni(),
                a.getTelefono(), a.getEmail(), a.getIdArrendatario());
    }

    @Override
    public int eliminaArrendatario(Arrendatario a) {
        String sql = "DELETE FROM arrendatario WHERE id_arrendatario = ?";
        return jdbcTemplate.update(sql, a.getIdArrendatario());
    }

    @Override
    public int eliminarTodos() {
        String sql = "DELETE FROM arrendatario";
        return jdbcTemplate.update(sql);
    }

    @Override
    public List<Arrendatario> getArrendatariosDeUsuario(Usuario u) {
        int id = u.getIdUsuario();
        List<Arrendatario> listArrendatarios;
        listArrendatarios = jdbcTemplate.query("SELECT * FROM arrendatario WHERE id_usuario = ?", mapeadorArrendatario, id);
        return listArrendatarios;
    }

}
