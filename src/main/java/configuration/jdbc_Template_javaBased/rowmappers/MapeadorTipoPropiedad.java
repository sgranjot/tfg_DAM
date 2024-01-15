package configuration.jdbc_Template_javaBased.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import modelos.TipoPropiedad;

public class MapeadorTipoPropiedad implements RowMapper<TipoPropiedad> {

    @Override
    public TipoPropiedad mapRow(ResultSet rs, int i) throws SQLException {

        TipoPropiedad tp = new TipoPropiedad();

        tp.setIdTipoPropiedad(rs.getInt("id_tipo_propiedad"));
        tp.setTipoPropiedad(rs.getString("tipo_propiedad"));

        return tp;
    }

}
