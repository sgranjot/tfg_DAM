/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import configuration.jdbc_Template_javaBased.AppConfig;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Direccion;
import modelos.Usuario;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import servicios.implementacion.ServicioDireccion;

/**
 *
 * @author sgranjot
 */
public class ServletConfirmarActualizarPropiedad extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuarioSession");

        ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
        ServicioDireccion servicioDireccion = (ServicioDireccion) contenedor.getBean("servicioDireccion");

        String calle = (String) request.getParameter("calle");
        request.setAttribute("calle", calle);
        String numero = (String) request.getParameter("numero");
        request.setAttribute("numero", numero);
        String piso = (String) request.getParameter("piso");
        request.setAttribute("piso", piso);
        String puerta = (String) request.getParameter("puerta");
        request.setAttribute("puerta", puerta);
        String poblacion = (String) request.getParameter("poblacion");
        request.setAttribute("poblacion", poblacion);
        String codigoPostal = (String) request.getParameter("codigoPostal");
        request.setAttribute("codigoPostal", codigoPostal);
        String provincia = (String) request.getParameter("provincia");
        request.setAttribute("provincia", provincia);
        String pais = (String) request.getParameter("pais");
        request.setAttribute("pais", pais);
        String plaza = (String) request.getParameter("numPlaza");
        request.setAttribute("plaza", plaza);

        int idDireccion = Integer.valueOf(request.getParameter("idDireccion"));

        Direccion direccion = new Direccion(idDireccion, calle, numero, piso, puerta, codigoPostal, poblacion, provincia, pais, plaza);

        servicioDireccion.actualizaDireccion(direccion);

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
