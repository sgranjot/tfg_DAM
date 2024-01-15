package configuration.jdbc_Template_javaBased.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import modelos.Propiedad;

public class MapeadorPropiedad implements RowMapper<Propiedad> {

    @Override
    public Propiedad mapRow(ResultSet rs, int i) throws SQLException {

        Propiedad p = new Propiedad();

        p.setIdPropiedad(rs.getInt("id_propiedad"));
        p.setIdUsuario(rs.getInt("id_usuario"));
        p.setIdDireccion(rs.getInt("id_direccion"));
        p.setIdTipoPropiedad(rs.getInt("id_tipo_propiedad"));
        p.setLibre(rs.getBoolean("libre"));

        return p;
    }

}
