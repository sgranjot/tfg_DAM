package configuration.jdbc_Template_javaBased.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import modelos.Direccion;

public class MapeadorDireccion implements RowMapper<Direccion> {

    @Override
    public Direccion mapRow(ResultSet rs, int i) throws SQLException {

        Direccion d = new Direccion();

        d.setIdDireccion(rs.getInt("id_direccion"));
        d.setCalle(rs.getString("calle"));
        d.setNumero(rs.getString("numero"));
        d.setPiso(rs.getString("piso"));
        d.setPuerta(rs.getString("puerta"));
        d.setCodigoPostal(rs.getString("codigo_postal"));
        d.setPoblacion(rs.getString("poblacion"));
        d.setProvincia(rs.getString("provincia"));
        d.setPais(rs.getString("pais"));
        d.setNumeroPlaza(rs.getString("numero_plaza"));

        return d;
    }

}
