package servicios.implementacion;

import java.time.LocalDate;
import java.util.List;
import repositorios.implementacion.GastoDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import modelos.Gasto;
import modelos.Propiedad;
import modelos.Usuario;
import servicios.interfaces.I_servicioGasto;

public class ServicioGasto implements I_servicioGasto {

    private GastoDao gastoDao;
    private JdbcTemplate jdbdcTemplate;
    private RowMapper<Gasto> mapeadorGasto;

    public ServicioGasto() {
    }

    public ServicioGasto(GastoDao gastoDao) {
        this.gastoDao = gastoDao;
    }

    public ServicioGasto(GastoDao gastoDao, JdbcTemplate jdbdcTemplate, RowMapper<Gasto> mapeadorGasto) {
        this.gastoDao = gastoDao;
        this.jdbdcTemplate = jdbdcTemplate;
        this.mapeadorGasto = mapeadorGasto;
    }

    @Override
    public int altaGasto(Gasto g) {
        return gastoDao.altaGasto(g);
    }

    @Override
    public Gasto getGasto(int id) {
        return gastoDao.getGasto(id);
    }

    @Override
    public List<Gasto> getAllGastos() {
        return gastoDao.getAllGastos();
    }

    @Override
    public int actualizaGasto(Gasto g) {
        return gastoDao.actualizaGasto(g);
    }

    @Override
    public int eliminaGasto(Gasto g) {
        return gastoDao.eliminaGasto(g);
    }

    @Override
    public int eliminarTodos() {
        return gastoDao.eliminarTodos();
    }

    @Override
    public int getId(int idUsuario, int idTipoGasto, int idPropiedad, double importe, LocalDate fecha, String descripcion) {
        return gastoDao.getId(idUsuario, idTipoGasto, idPropiedad, importe, fecha, descripcion);
    }

    @Override
    public List<Gasto> getGastosDeUsuario(Usuario u) {
        return gastoDao.getGastosDeUsuario(u);
    }

    @Override
    public List<Gasto> getGastosDePropiedad(Propiedad p) {
        return gastoDao.getGastosDePropiedad(p);
    }

}
