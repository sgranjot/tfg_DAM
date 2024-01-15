package servicios.implementacion;

import java.time.LocalDate;
import java.util.List;
import repositorios.implementacion.ContratoDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import modelos.Contrato;
import modelos.Usuario;
import servicios.interfaces.I_servicioContrato;

public class ServicioContrato implements I_servicioContrato {

    private ContratoDao contratoDao;
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Contrato> mapeadorContrato;

    public ServicioContrato() {
    }

    public ServicioContrato(ContratoDao contratoDao) {
        this.contratoDao = contratoDao;
    }

    public ServicioContrato(ContratoDao contratoDao, JdbcTemplate jdbcTemplate, RowMapper<Contrato> mapeadorContrato) {
        this.contratoDao = contratoDao;
        this.jdbcTemplate = jdbcTemplate;
        this.mapeadorContrato = mapeadorContrato;
    }

    @Override
    public int altaContrato(Contrato c) {
        return contratoDao.altaContrato(c);
    }

    @Override
    public Contrato getContrato(int id) {
        return contratoDao.getContrato(id);
    }

    @Override
    public List<Contrato> getAllContratos() {
        return contratoDao.getAllContratos();
    }

    @Override
    public int actualizaContrato(Contrato c) {
        return contratoDao.actualizaContrato(c);
    }

    @Override
    public int eliminaContrato(Contrato c) {
        return contratoDao.eliminaContrato(c);
    }

    @Override
    public int eliminarTodos() {
        return contratoDao.eliminarTodos();
    }

    @Override
    public int getId(int idUsuario, int idPropiedad, int idArrendatario, LocalDate fechaInicio, LocalDate fechaFin, int fianza, int mensualidad) {
        return contratoDao.getId(idUsuario, idPropiedad, idArrendatario, fechaInicio, fechaFin, fianza, mensualidad);
    }

    @Override
    public List<Contrato> getContratosDeUsuario(Usuario u) {
        return contratoDao.getContratosDeUsuario(u);
    }

}
