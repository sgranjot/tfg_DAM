package repositorios.implementacion;

import java.util.List;
import repositorios.interfaces.I_direccionDao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import modelos.Direccion;

public class DireccionDao implements I_direccionDao {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Direccion> mapeadorDireccion;

    public DireccionDao() {
    }

    public DireccionDao(JdbcTemplate jdbcTemplate, RowMapper<Direccion> mapeadorDireccion) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapeadorDireccion = mapeadorDireccion;
    }

    @Override
    public int altaDireccion(Direccion d) {
        try {
            String sql = "Insert into direccion values (null,?,?,?,?,?,?,?,?,?)";
            return jdbcTemplate.update(sql, d.getCalle(), d.getNumero(), d.getPiso(),
                    d.getPuerta(), d.getCodigoPostal(), d.getPoblacion(), d.getProvincia(),
                    d.getPais(), d.getNumeroPlaza());
        } catch (DataAccessException ex) {
            return 0;
        }

    }

    @Override
    public Direccion getDireccion(int id) {
        List<Direccion> direcList;
        direcList = jdbcTemplate.query("select * from direccion where id_direccion = ?", mapeadorDireccion, id);
        Direccion direccion = new Direccion();
        for (Direccion d : direcList) {

            direccion.setIdDireccion(d.getIdDireccion());
            direccion.setCalle(d.getCalle());
            direccion.setNumero(d.getNumero());
            direccion.setPiso(d.getPiso());
            direccion.setPuerta(d.getPuerta());
            direccion.setCodigoPostal(d.getCodigoPostal());
            direccion.setPoblacion(d.getPoblacion());
            direccion.setProvincia(d.getProvincia());
            direccion.setPais(d.getPais());
            direccion.setNumeroPlaza(d.getNumeroPlaza());
            return direccion;
        }
        return null;
    }

    @Override
    public List<Direccion> getAllDirecciones() {
        return jdbcTemplate.query("select * from direccion", mapeadorDireccion);
    }

    //hay que programar la base de datos para que en caso de plaza de garaje null ponga 'no'
    @Override
    public int getId(String calle, String numero, String piso, String puerta, String codigoPostal,
            String poblacion, String provincia, String pais, String numeroPlaza) {
        for (Direccion d : getAllDirecciones()) {
            if (d.getCalle().equals(calle) && d.getNumero().equals(numero) && d.getPiso().equals(piso) && d.getPuerta().equals(puerta)
                    && d.getCodigoPostal().equals(codigoPostal) && d.getPoblacion().equals(poblacion) && d.getProvincia().equals(provincia)
                    && d.getPais().equals(pais) && d.getNumeroPlaza().equals(numeroPlaza)) {
                return d.getIdDireccion();
            }
        }
        return 0;
    }

    @Override
    public int actualizaDireccion(Direccion d) {
        String sql = "UPDATE direccion SET calle = ?, numero = ?, piso = ?, puerta = ?, codigo_postal = ?,"
                + "poblacion = ?, provincia = ?, pais = ?, numero_plaza = ? WHERE id_direccion = ?";
        return jdbcTemplate.update(sql, d.getCalle(), d.getNumero(), d.getPiso(), d.getPuerta(), d.getCodigoPostal(),
                d.getPoblacion(), d.getProvincia(), d.getPais(), d.getNumeroPlaza(), d.getIdDireccion());
    }

    @Override
    public int eliminaDireccion(Direccion d) {
        String sql = "DELETE FROM direccion WHERE id_direccion = ?";
        return jdbcTemplate.update(sql, d.getIdDireccion());
    }

    @Override
    public int eliminarTodos() {
        String sql = "DELETE FROM direccion";
        return jdbcTemplate.update(sql);
    }

}
