package configuration.jdbc_Template_javaBased.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import modelos.Contrato;

public class MapeadorContrato implements RowMapper<Contrato> {

    @Override
    public Contrato mapRow(ResultSet rs, int i) throws SQLException {

        Contrato c = new Contrato();

        c.setIdContrato(rs.getInt("id_contrato"));
        c.setIdUsuario(rs.getInt("id_usuario"));
        c.setIdPropiedad(rs.getInt("id_propiedad"));
        c.setIdArrendatario(rs.getInt("id_arrendatario"));
        c.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
        c.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
        c.setFianza(rs.getInt("fianza"));
        c.setMensualidad(rs.getInt("mensualidad"));

        return c;
    }

}
