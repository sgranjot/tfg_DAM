/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import adapter.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import configuration.jdbc_Template_javaBased.AppConfig;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Contrato;
import modelos.Direccion;
import modelos.Propiedad;
import modelos.Usuario;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import servicios.implementacion.ServicioArrendatario;
import servicios.implementacion.ServicioContrato;
import servicios.implementacion.ServicioDireccion;
import servicios.implementacion.ServicioPropiedad;
import servicios.implementacion.ServicioTipoPropiedad;

/**
 *
 * @author sgranjot
 */
public class ServletContratos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuarioSession");

        ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
        ServicioContrato servicioContrato = (ServicioContrato) contenedor.getBean("servicioContrato");
        ServicioPropiedad servicioPropiedad = (ServicioPropiedad) contenedor.getBean("servicioPropiedad");
        ServicioArrendatario servicioArrendatario = (ServicioArrendatario) contenedor.getBean("servicioArrendatario");
        ServicioDireccion servicioDireccion = (ServicioDireccion) contenedor.getBean("servicioDireccion");
        ServicioTipoPropiedad servicioTipoPropiedad = (ServicioTipoPropiedad) contenedor.getBean("servicioTipoPropiedad");

        Gson gson =new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        .create();

        //lista de contratos
        List<Contrato> listContratos = servicioContrato.getContratosDeUsuario(usuarioSesion);
        String contratosJson = gson.toJson(listContratos);
        request.setAttribute("contratosJson", contratosJson);

        //lista de propiedades
        List<String> listPropiedades = new ArrayList();
        for (Contrato c : listContratos) {
            int idPropiedad = c.getIdPropiedad();
            Propiedad propiedad = servicioPropiedad.getPropiedad(idPropiedad);
            int idDireccion = propiedad.getIdDireccion();
            Direccion direccion = servicioDireccion.getDireccion(idDireccion);
            int idTipoPropiedad = propiedad.getIdTipoPropiedad();
            String tipo = servicioTipoPropiedad.getTipoPropiedad(idTipoPropiedad);
            String calle = direccion.getCalle();
            String numero = direccion.getNumero();
            String poblacion = direccion.getPoblacion();
            String plaza = direccion.getNumeroPlaza();
            if (tipo.equalsIgnoreCase("garaje")) {
                listPropiedades.add(tipo + " plaza nº " + plaza + " en " + calle + " " + numero + ", " + poblacion);
            } else {
                listPropiedades.add(tipo + " en " + calle + " " + numero + ", " + poblacion);
            }

        }
        String propiedadesJson = gson.toJson(listPropiedades);
        request.setAttribute("propiedadesJson", propiedadesJson);

        //lista de arrendatarios
        List<String> listArrendatarios = new ArrayList();
        for (Contrato c : listContratos) {
            int idArrendatario = c.getIdArrendatario();
            String nombre = servicioArrendatario.getArrendatario(idArrendatario).getNombre();
            String apellidos = servicioArrendatario.getArrendatario(idArrendatario).getApellidos();
            listArrendatarios.add(apellidos + ", " + nombre);
        }
        String arrendatariosJson = gson.toJson(listArrendatarios);
        request.setAttribute("arrendatariosJson", arrendatariosJson);

        //lista de atributos vigor de contratos
        List<String> listVigor = new ArrayList();
        for (Contrato c : listContratos) {
            LocalDate actual = LocalDate.now();
            LocalDate fechaFin = c.getFechaFin();
            if (fechaFin.isBefore(actual) || fechaFin.isEqual(actual)) {
                listVigor.add("No");
            } else {
                listVigor.add("Si");
            }
        }
        String vigorJson = gson.toJson(listVigor);
        request.setAttribute("vigorJson", vigorJson);

        RequestDispatcher rd = request.getRequestDispatcher("contratos.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
