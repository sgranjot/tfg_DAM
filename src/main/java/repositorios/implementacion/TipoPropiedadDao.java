package repositorios.implementacion;

import java.util.List;
import repositorios.interfaces.I_tipoPropiedadoDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import modelos.TipoPropiedad;

public class TipoPropiedadDao implements I_tipoPropiedadoDao {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<TipoPropiedad> mapeadorTipoPropiedad;

    public TipoPropiedadDao() {
    }

    public TipoPropiedadDao(JdbcTemplate jdbcTemplate, RowMapper<TipoPropiedad> mapeadorTipoPropiedad) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapeadorTipoPropiedad = mapeadorTipoPropiedad;
    }

    @Override
    public String getTipoPropiedad(int id) {
        List<TipoPropiedad> list;
        list = jdbcTemplate.query("SELECT * FROM tipo_propiedad WHERE id_tipo_propiedad = ?", mapeadorTipoPropiedad, id);
        return list.get(0).getTipoPropiedad();
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RowMapper<TipoPropiedad> getMapeadorTipoPropiedad() {
        return mapeadorTipoPropiedad;
    }

    public void setMapeadorTipoPropiedad(RowMapper<TipoPropiedad> mapeadorTipoPropiedad) {
        this.mapeadorTipoPropiedad = mapeadorTipoPropiedad;
    }

}
