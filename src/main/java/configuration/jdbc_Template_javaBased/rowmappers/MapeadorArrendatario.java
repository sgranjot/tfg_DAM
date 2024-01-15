package configuration.jdbc_Template_javaBased.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import modelos.Arrendatario;

public class MapeadorArrendatario implements RowMapper<Arrendatario> {

    @Override
    public Arrendatario mapRow(ResultSet rs, int i) throws SQLException {

        Arrendatario a = new Arrendatario();

        a.setIdArrendatario(rs.getInt("id_arrendatario"));
        a.setIdUsuario(rs.getInt("id_usuario"));
        a.setNombre(rs.getString("nombre"));
        a.setApellidos(rs.getString("apellidos"));
        a.setDni(rs.getString("dni"));
        a.setTelefono(rs.getString("telefono"));
        a.setEmail(rs.getString("email"));

        return a;
    }

}
