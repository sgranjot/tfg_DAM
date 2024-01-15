package configuration.jdbc_Template_javaBased.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import modelos.Gasto;

public class MapeadorGasto implements RowMapper<Gasto> {

    @Override
    public Gasto mapRow(ResultSet rs, int i) throws SQLException {

        Gasto g = new Gasto();

        g.setIdGasto(rs.getInt("id_gasto"));
        g.setIdUsuario((rs.getInt("id_usuario")));
        g.setIdTipoGasto(rs.getInt("id_tipo_gasto"));
        g.setIdPropiedad(rs.getInt("id_propiedad"));
        g.setImporte(rs.getDouble("importe"));
        g.setFecha(rs.getDate("fecha").toLocalDate());
        g.setDescripcion(rs.getString("descripcion"));

        return g;
    }

}
