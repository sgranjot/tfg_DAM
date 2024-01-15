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
import modelos.Arrendatario;
import modelos.Usuario;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import servicios.implementacion.ServicioArrendatario;

/**
 *
 * @author sgranjot
 */
public class ServletEliminarArrendatario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuarioSession");

        ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
        ServicioArrendatario servicioArrendatario = (ServicioArrendatario) contenedor.getBean("servicioArrendatario");

        String nombre = (String) request.getParameter("nombreH");
        String apellidos = (String) request.getParameter("apellidosH");
        String dni = (String) request.getParameter("dniH");
        String telefono = (String) request.getParameter("telefonoH");
        String email = (String) request.getParameter("emailH");

        int idUsuario = usuarioSesion.getIdUsuario();

        int idArrendatario = servicioArrendatario.getId(idUsuario, nombre, apellidos, dni, telefono, email);

        Arrendatario arrendatario = servicioArrendatario.getArrendatario(idArrendatario);

        servicioArrendatario.eliminaArrendatario(arrendatario);

        RequestDispatcher rd = request.getRequestDispatcher("servletArrendatarios");
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
