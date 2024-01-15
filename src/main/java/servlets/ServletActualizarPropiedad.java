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
public class ServletActualizarPropiedad extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String calle = (String) request.getParameter("calleH");
        request.setAttribute("calle", calle);
        String numero = (String) request.getParameter("numeroH");
        request.setAttribute("numero", numero);
        String piso = (String) request.getParameter("pisoH");
        request.setAttribute("piso", piso);
        String puerta = (String) request.getParameter("puertaH");
        request.setAttribute("puerta", puerta);
        String poblacion = (String) request.getParameter("poblacionH");
        request.setAttribute("poblacion", poblacion);
        String codigoPostal = (String) request.getParameter("codigoPostalH");
        request.setAttribute("codigoPostal", codigoPostal);
        String provincia = (String) request.getParameter("provinciaH");
        request.setAttribute("provincia", provincia);
        String pais = (String) request.getParameter("paisH");
        request.setAttribute("pais", pais);
        String plaza = (String) request.getParameter("plazaH");
        String numPlaza = null;
        if (plaza.contains("vivienda") || plaza.contains("local") || plaza.contains("oficina") || plaza.contains("nave")) {
            request.setAttribute("numPlaza", "No");
        } else {
            String[] plazaGaraje = plaza.split("garaje plaza: ");
            numPlaza = plazaGaraje[1].trim();
            request.setAttribute("numPlaza", numPlaza);
        }

        RequestDispatcher rd = request.getRequestDispatcher("actualizarPropiedad.jsp");
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
