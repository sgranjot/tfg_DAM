/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sgranjot
 */
public class ServletActualizarArrendatario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String nombre = (String) request.getParameter("nombreH");
        request.setAttribute("nombre", nombre);
        String apellidos = (String) request.getParameter("apellidosH");
        request.setAttribute("apellidos", apellidos);
        String dni = (String) request.getParameter("dniH");
        request.setAttribute("dni", dni);
        String telefono = (String) request.getParameter("telefonoH");
        request.setAttribute("telefono", telefono);
        String email = (String) request.getParameter("emailH");
        request.setAttribute("email", email);

        RequestDispatcher rd = request.getRequestDispatcher("actualizarArrendatario.jsp");
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
