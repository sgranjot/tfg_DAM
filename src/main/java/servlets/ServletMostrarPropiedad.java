/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import configuration.jdbc_Template_javaBased.AppConfig;
import servicios.implementacion.ServicioDireccion;
import servicios.implementacion.ServicioPropiedad;
import servicios.implementacion.ServicioTipoPropiedad;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Direccion;
import modelos.Propiedad;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import modelos.Usuario;

/**
 *
 * @author sgranjot
 */
public class ServletMostrarPropiedad extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuarioSession");

        ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
        ServicioPropiedad servicioPropiedad = (ServicioPropiedad) contenedor.getBean("servicioPropiedad");
        ServicioDireccion servicioDireccion = (ServicioDireccion) contenedor.getBean("servicioDireccion");
        ServicioTipoPropiedad servicioTipoPropiedad = (ServicioTipoPropiedad) contenedor.getBean("servicioTipoPropiedad");

        int idp = Integer.parseInt(request.getParameter("propiedadId"));

        Propiedad propiedad = servicioPropiedad.getPropiedad(idp);
        int idDireccion = propiedad.getIdDireccion();
        Direccion direccion = servicioDireccion.getDireccion(idDireccion);
        String calle = direccion.getCalle();
        String numero = direccion.getNumero();
        String piso = direccion.getPiso();
        String puerta = direccion.getPuerta();
        String poblacion = direccion.getPoblacion();
        String codigoPostal = direccion.getCodigoPostal();
        String provincia = direccion.getProvincia();
        String pais = direccion.getPais();
        String plaza = direccion.getNumeroPlaza();
        int idTipo = propiedad.getIdTipoPropiedad();
        String tipo = servicioTipoPropiedad.getTipoPropiedad(idTipo);

        request.setAttribute("calle", calle);
        request.setAttribute("numero", numero);
        request.setAttribute("piso", piso);
        request.setAttribute("puerta", puerta);
        request.setAttribute("poblacion", poblacion);
        request.setAttribute("codigoPostal", codigoPostal);
        request.setAttribute("provincia", provincia);
        request.setAttribute("pais", pais);
        request.setAttribute("tipo", tipo);
        request.setAttribute("plaza", plaza);
        request.setAttribute("idPropiedad", idp);

        RequestDispatcher rd = request.getRequestDispatcher("mostrarPropiedad.jsp");
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
