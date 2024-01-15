package servicios.implementacion;

import java.util.List;
import repositorios.implementacion.DireccionDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import modelos.Direccion;
import servicios.interfaces.I_servicioDireccion;

public class ServicioDireccion implements I_servicioDireccion {

    private DireccionDao direccionDao;
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Direccion> mapeadorDireccion;

    public ServicioDireccion() {
    }

    public ServicioDireccion(DireccionDao direccionDao) {
        this.direccionDao = direccionDao;
    }

    public ServicioDireccion(DireccionDao direccionDao, JdbcTemplate jdbcTemplate, RowMapper<Direccion> mapeadorDireccion) {
        this.direccionDao = direccionDao;
        this.jdbcTemplate = jdbcTemplate;
        this.mapeadorDireccion = mapeadorDireccion;
    }

    @Override
    public int altaDireccion(Direccion d) {
        return direccionDao.altaDireccion(d);
    }

    @Override
    public Direccion getDireccion(int id) {
        return direccionDao.getDireccion(id);
    }

    @Override
    public List<Direccion> getAllDirecciones() {
        return direccionDao.getAllDirecciones();
    }

    @Override
    public int actualizaDireccion(Direccion d) {
        return direccionDao.actualizaDireccion(d);
    }

    @Override
    public int eliminaDireccion(Direccion d) {
        return direccionDao.eliminaDireccion(d);
    }

    @Override
    public int eliminarTodos() {
        return direccionDao.eliminarTodos();
    }

    @Override
    public int getId(String calle, String numero, String piso, String puerta, String codigoPostal, String poblacion, String provincia, String pais, String numeroPlaza) {
        return direccionDao.getId(calle, numero, piso, puerta, codigoPostal, poblacion, provincia, pais, numeroPlaza);
    }

}
