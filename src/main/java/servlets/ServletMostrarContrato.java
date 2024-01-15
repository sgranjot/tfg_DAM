/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import configuration.jdbc_Template_javaBased.AppConfig;
import java.io.IOException;
import java.time.LocalDate;
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
public class ServletMostrarContrato extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuarioSession");

        ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
        ServicioContrato servicioContrato = (ServicioContrato) contenedor.getBean("servicioContrato");
        ServicioPropiedad servicioPropiedad = (ServicioPropiedad) contenedor.getBean("servicioPropiedad");
        ServicioDireccion servicioDireccion = (ServicioDireccion) contenedor.getBean("servicioDireccion");
        ServicioTipoPropiedad servicioTipoPropiedad = (ServicioTipoPropiedad) contenedor.getBean("servicioTipoPropiedad");
        ServicioArrendatario servicioArrendatario = (ServicioArrendatario) contenedor.getBean("servicioArrendatario");

        int idc = Integer.parseInt(request.getParameter("contratoId"));

        Contrato contrato = servicioContrato.getContrato(idc);
        int idPropiedad = contrato.getIdPropiedad();
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
            tipo = "garaje plaza nº " + plaza;
        }

        int idArrendatario = contrato.getIdArrendatario();

        String nombre = servicioArrendatario.getArrendatario(idArrendatario).getNombre();
        String apellidos = servicioArrendatario.getArrendatario(idArrendatario).getApellidos();

        String fechaInicio = contrato.getFechaInicio().toString();
        String fechaFin = contrato.getFechaFin().toString();

        String fianza = String.valueOf(contrato.getFianza());
        String mensualidad = String.valueOf(contrato.getMensualidad());

        String vigor = null;
        if (contrato.getFechaFin().isBefore(LocalDate.now()) || contrato.getFechaFin().isEqual(LocalDate.now())) {
            vigor = "No";
        } else {
            vigor = "Si";
        }

        request.setAttribute("idContrato", String.valueOf(idc));
        request.setAttribute("tipo", tipo);
        request.setAttribute("calle", calle);
        request.setAttribute("numero", numero);
        request.setAttribute("poblacion", poblacion);
        request.setAttribute("nombre", nombre);
        request.setAttribute("apellidos", apellidos);
        request.setAttribute("fechaInicio", fechaInicio);
        request.setAttribute("fechaFin", fechaFin);
        request.setAttribute("fianza", fianza);
        request.setAttribute("mensualidad", mensualidad);
        request.setAttribute("vigor", vigor);

        RequestDispatcher rd = request.getRequestDispatcher("mostrarContrato.jsp");
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
