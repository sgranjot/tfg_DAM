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
import modelos.Arrendatario;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import servicios.implementacion.ServicioArrendatario;

/**
 *
 * @author sgranjot
 */
public class ServletMostrarArrendatario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
        ServicioArrendatario servicioArrendatario = (ServicioArrendatario) contenedor.getBean("servicioArrendatario");

        int ida = Integer.valueOf(request.getParameter("arrendatarioId"));

        Arrendatario arrendatario = servicioArrendatario.getArrendatario(ida);
        String nombre = arrendatario.getNombre();
        String apellidos = arrendatario.getApellidos();
        String dni = arrendatario.getDni();
        String telefono = arrendatario.getTelefono();
        String email = arrendatario.getEmail();

        request.setAttribute("nombre", nombre);
        request.setAttribute("apellidos", apellidos);
        request.setAttribute("dni", dni);
        request.setAttribute("telefono", telefono);
        request.setAttribute("email", email);

        RequestDispatcher rd = request.getRequestDispatcher("mostrarArrendatario.jsp");
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
