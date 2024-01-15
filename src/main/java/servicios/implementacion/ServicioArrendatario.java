package servicios.implementacion;

import java.util.List;
import repositorios.implementacion.ArrendatarioDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import modelos.Arrendatario;
import modelos.Usuario;
import servicios.interfaces.I_servicioArrendatario;

public class ServicioArrendatario implements I_servicioArrendatario {

    private ArrendatarioDao arrendatarioDao;
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Arrendatario> mapeadorArrendatario;

    public ServicioArrendatario() {
    }

    public ServicioArrendatario(ArrendatarioDao arrendatarioDao) {
        this.arrendatarioDao = arrendatarioDao;
    }

    public ServicioArrendatario(ArrendatarioDao arrendatarioDao, JdbcTemplate jdbcTemplate, RowMapper<Arrendatario> mapeadorArrendatario) {
        this.arrendatarioDao = arrendatarioDao;
        this.jdbcTemplate = jdbcTemplate;
        this.mapeadorArrendatario = mapeadorArrendatario;
    }

    @Override
    public int altaArrendatario(Arrendatario a) {
        return arrendatarioDao.altaArrendatario(a);
    }

    @Override
    public Arrendatario getArrendatario(int id) {
        return arrendatarioDao.getArrendatario(id);
    }

    @Override
    public List<Arrendatario> getAllArrendatarios() {
        return arrendatarioDao.getAllArrendatarios();
    }

    @Override
    public int getId(int idUsuario, String nombre, String apellidos, String dni, String telefono, String email) {
        return arrendatarioDao.getId(idUsuario, nombre, apellidos, dni, telefono, email);
    }

    @Override
    public int actualizaArrendatario(Arrendatario a) {
        return arrendatarioDao.actualizaArrendatario(a);
    }

    @Override
    public int eliminaArrendatario(Arrendatario a) {
        return arrendatarioDao.eliminaArrendatario(a);
    }

    @Override
    public int eliminarTodos() {
        return arrendatarioDao.eliminarTodos();
    }

    @Override
    public List<Arrendatario> getArrendatariosDeUsuario(Usuario u) {
        return arrendatarioDao.getArrendatariosDeUsuario(u);
    }

}
