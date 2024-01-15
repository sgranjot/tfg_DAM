/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import configuration.jdbc_Template_javaBased.AppConfig;
import servicios.implementacion.ServicioDireccion;
import servicios.implementacion.ServicioPropiedad;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import modelos.Direccion;
import modelos.Propiedad;
import modelos.Usuario;

/**
 *
 * @author sgranjot
 */
public class ServletAniadirPropiedad extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuarioSession");

        ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
        ServicioPropiedad servicioPropiedad = (ServicioPropiedad) contenedor.getBean("servicioPropiedad");
        ServicioDireccion servicioDireccion = (ServicioDireccion) contenedor.getBean("servicioDireccion");

        String calle = request.getParameter("calle");
        String numero = request.getParameter("numero");
        String piso = request.getParameter("piso");
        String puerta = request.getParameter("puerta");
        String codigoPostal = request.getParameter("codigoPostal");
        String poblacion = request.getParameter("poblacion");
        String provincia = request.getParameter("provincia");
        String pais = request.getParameter("pais");
        String tipoPropiedad = request.getParameter("tipoPropiedad");
        String numPlaza = null;
        boolean libre;
        int idDireccion;
        if (request.getParameter("libre").equals("si")) {
            libre = true;
        } else {
            libre = false;
        }
        int idTipoPropiedad = 0;
        switch (tipoPropiedad) {
            case "vivienda":
                idTipoPropiedad = 1;
                break;
            case "local":
                idTipoPropiedad = 2;
                break;
            case "oficina":
                idTipoPropiedad = 3;
                break;
            case "nave":
                idTipoPropiedad = 4;
                break;
            case "garaje":
                idTipoPropiedad = 5;
        }
        if (idTipoPropiedad == 5) {
            numPlaza = request.getParameter("numPlaza");
            servicioDireccion.altaDireccion(new Direccion(0, calle, numero, piso, puerta, codigoPostal, poblacion, provincia, pais, numPlaza));
            idDireccion = servicioDireccion.getId(calle, numero, piso, puerta, codigoPostal, poblacion, provincia, pais, numPlaza);
        } else {
            servicioDireccion.altaDireccion(new Direccion(0, calle, numero, piso, puerta, codigoPostal, poblacion, provincia, pais, "No"));
            idDireccion = servicioDireccion.getId(calle, numero, piso, puerta, codigoPostal, poblacion, provincia, pais, "No");
        }
        int idUsuario = usuarioSesion.getIdUsuario();

        servicioPropiedad.altaPropiedad(new Propiedad(0, idUsuario, idDireccion, idTipoPropiedad, libre));

        RequestDispatcher rd = request.getRequestDispatcher("servletPropiedades");
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
