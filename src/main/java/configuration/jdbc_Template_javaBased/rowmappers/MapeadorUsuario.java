package configuration.jdbc_Template_javaBased.rowmappers;

import modelos.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class MapeadorUsuario implements RowMapper<Usuario> {

    @Override
    public Usuario mapRow(ResultSet rs, int i) throws SQLException {

        Usuario u = new Usuario();

        u.setIdUsuario((rs.getInt("id_usuario")));
        u.setNombre(rs.getString("usuario"));
        u.setContrasenia(rs.getString("contrasenia"));
        u.setActivado(rs.getBoolean("activado"));

        return u;
    }

}
