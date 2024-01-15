package repositorios.implementacion;

import java.sql.Date;
import java.util.List;
import repositorios.interfaces.I_mensualidadDao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import modelos.Mensualidad;
import modelos.Propiedad;
import modelos.Usuario;

public class MensualidadDao implements I_mensualidadDao {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Mensualidad> mapeadorMensualidad;

    public MensualidadDao() {
    }

    public MensualidadDao(JdbcTemplate jdbcTemplate, RowMapper<Mensualidad> mapeadorMensualidad) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapeadorMensualidad = mapeadorMensualidad;
    }

    @Override
    public int altaMensualidad(Mensualidad m) {
        try {
            String sql = "Insert into mensualidad values (null,?,?,?,?,?)";
            return jdbcTemplate.update(sql, m.getIdUsuario(), m.getIdContrato(), m.getCantidad(), m.getFecha(), m.isEstado());
        } catch (DataAccessException ex) {
            return 0;
        }
    }

    @Override
    public Mensualidad getMensualidad(int id) {
        List<Mensualidad> mensList;
        mensList = jdbcTemplate.query("select * from mensualidad where id_mensualidad = ?", mapeadorMensualidad, id);
        Mensualidad mensualidad = new Mensualidad();
        for (Mensualidad m : mensList) {

            mensualidad.setIdUsuario(m.getIdUsuario());
            mensualidad.setIdMensualidad(m.getIdMensualidad());
            mensualidad.setIdContrato(m.getIdContrato());
            mensualidad.setCantidad(m.getCantidad());
            mensualidad.setFecha(m.getFecha());
            mensualidad.setEstado(m.isEstado());
            return mensualidad;
        }
        return null;
    }

    @Override
    public List<Mensualidad> getAllMensualidades() {
        return jdbcTemplate.query("select * from mensualidad", mapeadorMensualidad);
    }

    @Override
    public int getId(int idUsuario, int idContrato, int cantidad, Date fecha, boolean estado) {
        for (Mensualidad m : getAllMensualidades()) {
            if (m.getIdUsuario() == idUsuario && m.getIdContrato() == idContrato && m.getCantidad() == cantidad && m.getFecha().equals(fecha)
                    && m.isEstado() == estado) {
                return m.getIdMensualidad();
            }
        }
        return 0;
    }

    @Override
    public int actualizaMensualidad(Mensualidad m) {
        String sql = "UPDATE mensualidad SET id_usuario = ?, id_contrato = ?, cantidad = ?, fecha = ?, estado = ? WHERE id_mensualidad = ?";
        return jdbcTemplate.update(sql, m.getIdUsuario(), m.getIdContrato(), m.getCantidad(), m.getFecha(), m.isEstado(),
                m.getIdMensualidad());
    }

    @Override
    public int eliminaMensualidad(Mensualidad m) {
        String sql = "DELETE FROM mensualidad WHERE id_mensualidad = ?";
        return jdbcTemplate.update(sql, m.getIdMensualidad());
    }

    @Override
    public int eliminarTodas() {
        String sql = "DELETE FROM mensualidad";
        return jdbcTemplate.update(sql);
    }

    @Override
    public List<Mensualidad> getMensualidadesDeUsuario(Usuario u) {
        int id = u.getIdUsuario();
        List<Mensualidad> listMensualidades;
        listMensualidades = jdbcTemplate.query("SELECT * FROM mensualidad WHERE id_usuario = ?", mapeadorMensualidad, id);
        return listMensualidades;
    }

    @Override
    public int getMensualidadesDeUsuarioPendientes(Usuario u) {
        int id = u.getIdUsuario();
        List<Mensualidad> listMensualidades;
        listMensualidades = jdbcTemplate.query("SELECT * FROM mensualidad WHERE id_usuario = ? and estado = 0", mapeadorMensualidad, id);
        return listMensualidades.size();
    }

    @Override
    public int getMensualidadesCobradasDePropiedad(Propiedad p) {
        int idPropiedad = p.getIdPropiedad();
        List<Mensualidad> listMensualidades;
        int idUsuario = p.getIdUsuario();
        listMensualidades = jdbcTemplate.query("SELECT * FROM mensualidad, contrato WHERE mensualidad.id_usuario= ? AND contrato.id_contrato=mensualidad.id_contrato AND contrato.id_propiedad= ? AND mensualidad.estado = 1", mapeadorMensualidad, idUsuario, idPropiedad);
        int total = 0;
        for (Mensualidad m : listMensualidades) {
            total += m.getCantidad();
        }
        return total;
    }

}
