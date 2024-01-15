package servicios.implementacion;

import modelos.TipoGasto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import repositorios.implementacion.TipoGastoDao;
import servicios.interfaces.I_servicioTipoGasto;

public class ServicioTipoGasto implements I_servicioTipoGasto {

    private TipoGastoDao tipoGastoDao;
    private JdbcTemplate JdbcTemplate;
    private RowMapper<TipoGasto> MapeadorTipoGasto;

    public ServicioTipoGasto() {
    }

    public ServicioTipoGasto(TipoGastoDao tipoGastoDao) {
        this.tipoGastoDao = tipoGastoDao;
    }

    public ServicioTipoGasto(TipoGastoDao tipoGastoDao, JdbcTemplate JdbcTemplate, RowMapper<TipoGasto> MapeadorTipoGasto) {
        this.tipoGastoDao = tipoGastoDao;
        this.JdbcTemplate = JdbcTemplate;
        this.MapeadorTipoGasto = MapeadorTipoGasto;
    }

    @Override
    public String getTipoGasto(int id) {
        return tipoGastoDao.getTipoGasto(id);
    }

}
