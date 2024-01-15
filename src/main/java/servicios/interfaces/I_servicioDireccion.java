package servicios.interfaces;

import java.util.List;
import modelos.Direccion;

public interface I_servicioDireccion {

    public int altaDireccion(Direccion d);

    public Direccion getDireccion(int id);

    public List<Direccion> getAllDirecciones();

    public int actualizaDireccion(Direccion d);

    public int eliminaDireccion(Direccion d);

    public int eliminarTodos();

    public int getId(String calle, String numero, String piso, String puerta, String codigoPostal,
            String poblacion, String provincia, String pais, String numeroPlaza);

}
