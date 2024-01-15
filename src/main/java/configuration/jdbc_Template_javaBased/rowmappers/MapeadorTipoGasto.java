package configuration.jdbc_Template_javaBased.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.TipoGasto;
import org.springframework.jdbc.core.RowMapper;

public class MapeadorTipoGasto implements RowMapper<TipoGasto> {

    @Override
    public TipoGasto mapRow(ResultSet rs, int rowNum) throws SQLException {

        TipoGasto tg = new TipoGasto();

        tg.setIdTipoGasto(rs.getInt("id_tipo_gasto"));
        tg.setTipoGasto(rs.getString("tipo_gasto"));

        return tg;
    }

}
