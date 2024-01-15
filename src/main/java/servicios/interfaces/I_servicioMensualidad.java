package servicios.interfaces;

import java.sql.Date;
import java.util.List;
import modelos.Mensualidad;
import modelos.Propiedad;
import modelos.Usuario;

public interface I_servicioMensualidad {

    public int altaMensualidad(Mensualidad m);

    public Mensualidad getMensualidad(int id);

    public List<Mensualidad> getAllMensualidades();

    public int actualizaMensualidad(Mensualidad m);

    public int eliminaMensualidad(Mensualidad m);

    public int eliminarTodas();

    public int getId(int idUsuario, int idContrato, int cantidad, Date fecha, boolean estado);

    public List<Mensualidad> getMensualidadesDeUsuario(Usuario u);

    public int getMensualidadesDeUsuarioPendientes(Usuario u);

    public int getMensualidadesCobradasDePropiedad(Propiedad p);
}
