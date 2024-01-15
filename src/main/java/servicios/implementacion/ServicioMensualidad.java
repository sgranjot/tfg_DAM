package servicios.implementacion;

import java.sql.Date;
import java.util.List;
import repositorios.implementacion.MensualidadDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import modelos.Mensualidad;
import modelos.Propiedad;
import modelos.Usuario;
import servicios.interfaces.I_servicioMensualidad;

public class ServicioMensualidad implements I_servicioMensualidad {

    private MensualidadDao mensualidadDao;
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Mensualidad> mapeadorMensualidad;

    public ServicioMensualidad() {
    }

    public ServicioMensualidad(MensualidadDao mensualidadDao) {
        this.mensualidadDao = mensualidadDao;
    }

    public ServicioMensualidad(MensualidadDao mensualidadDao, JdbcTemplate jdbcTemplate, RowMapper<Mensualidad> mapeadorMensualidad) {
        this.mensualidadDao = mensualidadDao;
        this.jdbcTemplate = jdbcTemplate;
        this.mapeadorMensualidad = mapeadorMensualidad;
    }

    @Override
    public int altaMensualidad(Mensualidad m) {
        return mensualidadDao.altaMensualidad(m);
    }

    @Override
    public Mensualidad getMensualidad(int id) {
        return mensualidadDao.getMensualidad(id);
    }

    @Override
    public List<Mensualidad> getAllMensualidades() {
        return mensualidadDao.getAllMensualidades();
    }

    @Override
    public int actualizaMensualidad(Mensualidad m) {
        return mensualidadDao.actualizaMensualidad(m);
    }

    @Override
    public int eliminaMensualidad(Mensualidad m) {
        return mensualidadDao.eliminaMensualidad(m);
    }

    @Override
    public int eliminarTodas() {
        return mensualidadDao.eliminarTodas();
    }

    @Override
    public int getId(int idUsuario, int idContrato, int cantidad, Date fecha, boolean estado) {
        return mensualidadDao.getId(idUsuario, idContrato, cantidad, fecha, estado);
    }

    @Override
    public List<Mensualidad> getMensualidadesDeUsuario(Usuario u) {
        return mensualidadDao.getMensualidadesDeUsuario(u);
    }

    @Override
    public int getMensualidadesDeUsuarioPendientes(Usuario u) {
        return mensualidadDao.getMensualidadesDeUsuarioPendientes(u);
    }

    @Override
    public int getMensualidadesCobradasDePropiedad(Propiedad p) {
        return mensualidadDao.getMensualidadesCobradasDePropiedad(p);
    }

}
