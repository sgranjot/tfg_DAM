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
import modelos.Usuario;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import servicios.implementacion.ServicioUsuario;

/**
 *
 * @author sgranjot
 */
public class ServletDarBajaUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuarioSession");

        ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
        ServicioUsuario servicioUsuario = (ServicioUsuario) contenedor.getBean("servicioUsuario");

        Usuario usuario = servicioUsuario.recuperarUsuario(usuarioSesion.getNombre());
        servicioUsuario.darDeBajaUsuario(usuario);

        String dispatcherString = request.getParameter("dispatcher");
        RequestDispatcher rd = request.getRequestDispatcher(dispatcherString);
        session.removeAttribute("usuarioSession");
        response.sendRedirect("index.html");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
