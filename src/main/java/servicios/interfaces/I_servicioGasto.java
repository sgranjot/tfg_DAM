package servicios.interfaces;

import java.time.LocalDate;
import java.util.List;
import modelos.Gasto;
import modelos.Propiedad;
import modelos.Usuario;

public interface I_servicioGasto {

    public int altaGasto(Gasto g);

    public Gasto getGasto(int id);

    public List<Gasto> getAllGastos();

    public int actualizaGasto(Gasto g);

    public int eliminaGasto(Gasto g);

    public int eliminarTodos();

    public int getId(int idUsuario, int idTipoGasto, int idPropiedad, double importe, LocalDate fecha, String descripcion);

    public List<Gasto> getGastosDeUsuario(Usuario u);

    public List<Gasto> getGastosDePropiedad(Propiedad p);
}
