package repositorios.implementacion;

import java.util.List;
import repositorios.interfaces.I_propiedadDao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import modelos.Propiedad;
import modelos.Usuario;

public class PropiedadDao implements I_propiedadDao {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Propiedad> mapeadorPropiedad;

    public PropiedadDao() {
    }

    public PropiedadDao(JdbcTemplate jdbcTemplate, RowMapper<Propiedad> mapeadorPropiedad) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapeadorPropiedad = mapeadorPropiedad;
    }

    @Override
    public int altaPropiedad(Propiedad p) {
        try {
            String sql = "Insert into propiedad values (null,?,?,?,?)";
            return jdbcTemplate.update(sql, p.getIdUsuario(), p.getIdDireccion(), p.getIdTipoPropiedad(), p.isLibre());
        } catch (DataAccessException ex) {
            return 0;
        }
    }

    @Override
    public Propiedad getPropiedad(int id) {
        List<Propiedad> propList;
        propList = jdbcTemplate.query("select * from propiedad where id_propiedad = ?", mapeadorPropiedad, id);
        Propiedad propiedad = new Propiedad();
        for (Propiedad p : propList) {

            propiedad.setIdPropiedad(p.getIdPropiedad());
            propiedad.setIdUsuario(p.getIdUsuario());
            propiedad.setIdDireccion(p.getIdDireccion());
            propiedad.setIdTipoPropiedad(p.getIdTipoPropiedad());
            propiedad.setLibre(p.isLibre());
            return propiedad;
        }
        return null;
    }

    @Override
    public List<Propiedad> getAllPropiedades() {
        return jdbcTemplate.query("select * from propiedad", mapeadorPropiedad);
    }

    @Override
    public int getId(int idUsuario, int idDireccion, int idTipoPropiedad, boolean libre) {
        for (Propiedad p : getAllPropiedades()) {
            if (p.getIdUsuario() == idUsuario && p.getIdDireccion() == idDireccion
                    && p.getIdTipoPropiedad() == idTipoPropiedad && p.isLibre() == libre) {
                return p.getIdPropiedad();
            }
        }
        return 0;
    }

    @Override
    public int actualizaPropiedad(Propiedad p) {
        String sql = "UPDATE propiedad SET id_usuario = ?, id_direccion = ?, id_tipo_propiedad = ?, libre = ? "
                + "WHERE id_propiedad = ?";
        return jdbcTemplate.update(sql, p.getIdUsuario(), p.getIdDireccion(), p.getIdTipoPropiedad(), p.isLibre(), p.getIdPropiedad());
    }

    @Override
    public int eliminaPropiedad(Propiedad p) {
        String sql = "DELETE FROM propiedad WHERE id_propiedad = ?";
        return jdbcTemplate.update(sql, p.getIdPropiedad());
    }

    @Override
    public int eliminarTodas() {
        String sql = "DELETE FROM propiedad";
        return jdbcTemplate.update(sql);
    }

    @Override
    public List<Propiedad> getPropiedadesDeUsuario(Usuario u) {
        int id = u.getIdUsuario();
        List<Propiedad> listPropiedades;
        listPropiedades = jdbcTemplate.query("SELECT * FROM propiedad WHERE id_usuario = ?", mapeadorPropiedad, id);
        return listPropiedades;
    }

}
