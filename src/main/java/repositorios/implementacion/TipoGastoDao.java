package repositorios.implementacion;

import java.util.List;
import modelos.TipoGasto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import repositorios.interfaces.I_tipoGastoDao;

public class TipoGastoDao implements I_tipoGastoDao {

    private JdbcTemplate JdbcTemplate;
    private RowMapper<TipoGasto> mapeadorTipoGasto;

    public TipoGastoDao() {
    }

    public TipoGastoDao(JdbcTemplate JdbcTemplate, RowMapper<TipoGasto> mapeadorTipoGasto) {
        this.JdbcTemplate = JdbcTemplate;
        this.mapeadorTipoGasto = mapeadorTipoGasto;
    }

    @Override
    public String getTipoGasto(int id) {
        List<TipoGasto> list;
        list = JdbcTemplate.query("SELECT * FROM tipo_gasto WHERE id_tipo_gasto = ?", mapeadorTipoGasto, id);
        return list.get(0).getTipoGasto();
    }

    public JdbcTemplate getJdbcTemplate() {
        return JdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate JdbcTemplate) {
        this.JdbcTemplate = JdbcTemplate;
    }

    public RowMapper<TipoGasto> getMapeadorTipoGasto() {
        return mapeadorTipoGasto;
    }

    public void setMapeadorTipoGasto(RowMapper<TipoGasto> mapeadorTipoGasto) {
        this.mapeadorTipoGasto = mapeadorTipoGasto;
    }

}
