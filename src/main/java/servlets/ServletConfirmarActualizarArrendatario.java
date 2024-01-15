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
public class ServletConfirmarActualizarArrendatario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuarioSession");

        ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
        ServicioArrendatario servicioArrendatario = (ServicioArrendatario) contenedor.getBean("servicioArrendatario");

        String nombre = (String) request.getParameter("nombre");
        request.setAttribute("nombre", nombre);
        String apellidos = (String) request.getParameter("apellidos");
        request.setAttribute("apellidos", apellidos);
        String dni = (String) request.getParameter("dni");
        request.setAttribute("dni", dni);
        String telefono = (String) request.getParameter("telefono");
        request.setAttribute("telefono", telefono);
        String email = (String) request.getParameter("email");
        request.setAttribute("email", email);

        int idArrendatario = Integer.valueOf(request.getParameter("idArrendatario"));

        int idUsuario = usuarioSesion.getIdUsuario();

        Arrendatario arrendatario = new Arrendatario(idArrendatario, idUsuario, nombre, apellidos, dni, telefono, email);

        servicioArrendatario.actualizaArrendatario(arrendatario);

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
