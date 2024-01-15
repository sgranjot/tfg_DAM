package servicios.implementacion;

import configuration.jdbc_Template_javaBased.rowmappers.MapeadorTipoPropiedad;
import repositorios.implementacion.TipoPropiedadDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import modelos.TipoPropiedad;
import servicios.interfaces.I_servicioTipoPropiedad;

public class ServicioTipoPropiedad implements I_servicioTipoPropiedad {

    private TipoPropiedadDao tipoPropiedadDao;
    private JdbcTemplate jdbcTemplate;
    private RowMapper<TipoPropiedad> mapeadorTipoPropiedad;

    public ServicioTipoPropiedad() {
    }

    public ServicioTipoPropiedad(TipoPropiedadDao tipoPropiedadDao) {
        this.tipoPropiedadDao = tipoPropiedadDao;
    }

    public ServicioTipoPropiedad(TipoPropiedadDao tipoPropiedadDao, JdbcTemplate jdbcTemplate, MapeadorTipoPropiedad mapeadorTipoPropiedad) {
        this.tipoPropiedadDao = tipoPropiedadDao;
        this.jdbcTemplate = jdbcTemplate;
        this.mapeadorTipoPropiedad = mapeadorTipoPropiedad;
    }

    @Override
    public String getTipoPropiedad(int id) {
        return tipoPropiedadDao.getTipoPropiedad(id);
    }

}
