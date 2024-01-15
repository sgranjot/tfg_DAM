package repositorios.implementacion;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import repositorios.interfaces.I_gastoDao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import modelos.Gasto;
import modelos.Propiedad;
import modelos.Usuario;

public class GastoDao implements I_gastoDao {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Gasto> mapeadorGasto;

    public GastoDao() {
    }

    public GastoDao(JdbcTemplate jdbcTemplate, RowMapper<Gasto> mapeadorGasto) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapeadorGasto = mapeadorGasto;
    }

    @Override
    public int altaGasto(Gasto g) {
        try {
            String sql = "Insert into gasto values (null,?,?,?,?,?,?)";
            return jdbcTemplate.update(sql, g.getIdUsuario(), g.getIdTipoGasto(), g.getIdPropiedad(),
                    g.getImporte(), Date.valueOf(g.getFecha()), g.getDescripcion());
        } catch (DataAccessException ex) {
            return 0;
        }
    }

    @Override
    public Gasto getGasto(int id) {
        List<Gasto> gastList;
        gastList = jdbcTemplate.query("select * from gasto where id_gasto = ?", mapeadorGasto, id);
        Gasto gasto = new Gasto();
        for (Gasto g : gastList) {

            gasto.setIdUsuario(g.getIdUsuario());
            gasto.setIdGasto(g.getIdGasto());
            gasto.setIdTipoGasto(g.getIdTipoGasto());
            gasto.setIdPropiedad(g.getIdPropiedad());
            gasto.setImporte(g.getImporte());
            gasto.setFecha(g.getFecha());
            gasto.setDescripcion(g.getDescripcion());
            return gasto;
        }
        return null;
    }

    @Override
    public List<Gasto> getAllGastos() {
        return jdbcTemplate.query("select * from gasto", mapeadorGasto);
    }

    @Override
    public int getId(int idUsuario, int idTipoGasto, int idPropiedad, double importe, LocalDate fecha, String descripcion) {
        for (Gasto g : getAllGastos()) {
            if (g.getIdUsuario() == idUsuario && g.getIdTipoGasto() == idTipoGasto && g.getIdPropiedad() == idPropiedad && g.getImporte() == importe
                    && g.getFecha().equals(fecha) && g.getDescripcion().equals(descripcion)) {
                return g.getIdGasto();
            }
        }
        return 0;
    }

    @Override
    public int actualizaGasto(Gasto g) {
        String sql = "UPDATE gasto SET id_usuario = ?, id_tipo_gasto = ?, id_propiedad = ?, importe = ?, fecha = ?,"
                + "descripcion = ? where id_gasto = ?";
        return jdbcTemplate.update(sql, g.getIdUsuario(), g.getIdTipoGasto(), g.getIdPropiedad(), g.getImporte(), g.getFecha(),
                g.getDescripcion(), g.getIdGasto());
    }

    @Override
    public int eliminaGasto(Gasto g) {
        String sql = "DELETE FROM gasto WHERE id_gasto = ?";
        return jdbcTemplate.update(sql, g.getIdGasto());
    }

    @Override
    public int eliminarTodos() {
        String sql = "DELETE FROM gasto";
        return jdbcTemplate.update(sql);
    }

    @Override
    public List<Gasto> getGastosDeUsuario(Usuario u) {
        int id = u.getIdUsuario();
        List<Gasto> listGastos;
        listGastos = jdbcTemplate.query("SELECT * FROM gasto WHERE id_usuario = ?", mapeadorGasto, id);
        return listGastos;
    }

    @Override
    public List<Gasto> getGastosDePropiedad(Propiedad p) {
        int id = p.getIdPropiedad();
        List<Gasto> listGastos;
        listGastos = jdbcTemplate.query("SELECT * FROM gasto WHERE id_propiedad = ?", mapeadorGasto, id);
        return listGastos;
    }

}
