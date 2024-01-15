package servicios.implementacion;

import java.util.List;
import repositorios.implementacion.PropiedadDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import modelos.Propiedad;
import modelos.Usuario;
import servicios.interfaces.I_servicioPropiedad;

public class ServicioPropiedad implements I_servicioPropiedad {

    private PropiedadDao propiedadDao;
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Propiedad> mapeadorPropiedad;

    public ServicioPropiedad() {
    }

    public ServicioPropiedad(PropiedadDao propiedadDao) {
        this.propiedadDao = propiedadDao;
    }

    public ServicioPropiedad(PropiedadDao propiedadDao, JdbcTemplate jdbcTemplate, RowMapper<Propiedad> mapeadorPropiedad) {
        this.propiedadDao = propiedadDao;
        this.jdbcTemplate = jdbcTemplate;
        this.mapeadorPropiedad = mapeadorPropiedad;
    }

    @Override
    public int altaPropiedad(Propiedad p) {
        return propiedadDao.altaPropiedad(p);
    }

    @Override
    public Propiedad getPropiedad(int id) {
        return propiedadDao.getPropiedad(id);
    }

    @Override
    public List<Propiedad> getAllPropiedades() {
        return propiedadDao.getAllPropiedades();
    }

    @Override
    public int actualizaPropiedad(Propiedad p) {
        return propiedadDao.actualizaPropiedad(p);
    }

    @Override
    public int eliminaPropiedad(Propiedad p) {
        return propiedadDao.eliminaPropiedad(p);
    }

    @Override
    public int eliminarTodas() {
        return propiedadDao.eliminarTodas();
    }

    @Override
    public int getId(int idUsuario, int idDireccion, int idTipoPropiedad, boolean libre) {
        return propiedadDao.getId(idUsuario, idDireccion, idTipoPropiedad, libre);
    }

    @Override
    public List<Propiedad> getPropiedadesDeUsuario(Usuario u) {
        return propiedadDao.getPropiedadesDeUsuario(u);
    }

}
