package repositorios.interfaces;

import java.time.LocalDate;
import java.util.List;
import modelos.Contrato;
import modelos.Usuario;

public interface I_contratoDao {

    public int altaContrato(Contrato c);

    public Contrato getContrato(int id);

    public List<Contrato> getAllContratos();

    public int actualizaContrato(Contrato c);

    public int eliminaContrato(Contrato c);

    public int eliminarTodos();

    public int getId(int idUsuario, int idPropiedad, int idArrendatario, LocalDate fechaInicio, LocalDate fechaFin, int fianza, int mensualidad);

    public List<Contrato> getContratosDeUsuario(Usuario u);

}
