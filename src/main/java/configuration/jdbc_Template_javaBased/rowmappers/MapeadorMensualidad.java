package configuration.jdbc_Template_javaBased.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import modelos.Mensualidad;

public class MapeadorMensualidad implements RowMapper<Mensualidad> {

    @Override
    public Mensualidad mapRow(ResultSet rs, int i) throws SQLException {

        Mensualidad m = new Mensualidad();

        m.setIdMensualidad(rs.getInt("id_mensualidad"));
        m.setIdUsuario(rs.getInt("id_usuario"));
        m.setIdContrato(rs.getInt("id_contrato"));
        m.setCantidad(rs.getInt("cantidad"));
        m.setFecha(rs.getDate("fecha"));
        m.setEstado(rs.getBoolean("estado"));

        return m;
    }

}
