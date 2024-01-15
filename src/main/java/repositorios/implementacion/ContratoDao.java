package repositorios.implementacion;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import modelos.Contrato;
import modelos.Usuario;
import repositorios.interfaces.I_contratoDao;

public class ContratoDao implements I_contratoDao {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Contrato> mapeadorContrato;

    public ContratoDao() {
    }

    public ContratoDao(JdbcTemplate jdbcTemplate, RowMapper<Contrato> mapeadorContrato) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapeadorContrato = mapeadorContrato;
    }

    @Override
    public int altaContrato(Contrato c) {
        try {
            String sql = "Insert into contrato values (null,?,?,?,?,?,?,?)";
            return jdbcTemplate.update(sql, c.getIdUsuario(), c.getIdPropiedad(), c.getIdArrendatario(),
                    Date.valueOf(c.getFechaInicio()), Date.valueOf(c.getFechaFin()), c.getFianza(), c.getMensualidad());
        } catch (DataAccessException ex) {
            return 0;
        }
    }

    @Override
    public Contrato getContrato(int id) {
        List<Contrato> contList;
        contList = jdbcTemplate.query("select * from contrato where id_contrato = ?", mapeadorContrato, id);
        Contrato contrato = new Contrato();
        for (Contrato c : contList) {

            contrato.setIdContrato(c.getIdContrato());
            contrato.setIdUsuario(c.getIdUsuario());
            contrato.setIdPropiedad(c.getIdPropiedad());
            contrato.setIdArrendatario(c.getIdArrendatario());
            contrato.setFechaInicio(c.getFechaInicio());
            contrato.setFechaFin(c.getFechaFin());
            contrato.setFianza(c.getFianza());
            contrato.setMensualidad(c.getMensualidad());
            return contrato;
        }
        return null;
    }

    @Override
    public List<Contrato> getAllContratos() {
        return jdbcTemplate.query("select * from contrato", mapeadorContrato);
    }

    @Override
    public int getId(int idUsuario, int idPropiedad, int idArrendatario, LocalDate fechaInicio, LocalDate fechaFin, int fianza, int mensualidad) {
        for (Contrato c : getAllContratos()) {
            if (c.getIdUsuario() == idUsuario && c.getIdPropiedad() == idPropiedad && c.getIdArrendatario() == idArrendatario && c.getFechaInicio().equals(fechaInicio)
                    && c.getFechaFin().equals(fechaFin) && c.getFianza() == fianza && c.getMensualidad() == mensualidad) {
                return c.getIdContrato();
            }
        }
        return 0;
    }

    @Override
    public int actualizaContrato(Contrato c) {
        String sql = "UPDATE contrato SET id_usuario = ?, id_propiedad = ?, id_arrendatario = ?, fecha_inicio = ?, fecha_fin = ?,"
                + " fianza = ?, mensualidad = ? WHERE id_contrato = ?";
        return jdbcTemplate.update(sql, c.getIdUsuario(), c.getIdPropiedad(), c.getIdArrendatario(), c.getFechaInicio(), c.getFechaFin(),
                c.getFianza(), c.getMensualidad(), c.getIdContrato());
    }

    @Override
    public int eliminaContrato(Contrato c) {
        String sql = "DELETE FROM contrato WHERE id_contrato = ?";
        return jdbcTemplate.update(sql, c.getIdContrato());
    }

    @Override
    public int eliminarTodos() {
        String sql = "DELETE FROM contrato";
        return jdbcTemplate.update(sql);
    }

    @Override
    public List<Contrato> getContratosDeUsuario(Usuario u) {
        int id = u.getIdUsuario();
        List<Contrato> listContratos;
        listContratos = jdbcTemplate.query("SELECT * FROM contrato WHERE id_usuario = ?", mapeadorContrato, id);
        return listContratos;
    }

}
