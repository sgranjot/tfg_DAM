package servicios.interfaces;

import java.util.List;
import modelos.Propiedad;
import modelos.Usuario;

public interface I_servicioPropiedad {

    public int altaPropiedad(Propiedad p);

    public Propiedad getPropiedad(int id);

    public List<Propiedad> getAllPropiedades();

    public int actualizaPropiedad(Propiedad p);

    public int eliminaPropiedad(Propiedad p);

    public int eliminarTodas();

    public int getId(int idUsuario, int idDireccion, int idTipoPropiedad, boolean libre);

    public List<Propiedad> getPropiedadesDeUsuario(Usuario u);

}
