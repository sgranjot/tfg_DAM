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
import modelos.Direccion;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import servicios.implementacion.ServicioDireccion;

/**
 *
 * @author sgranjot
 */
public class ServletEliminarPropiedad extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
        ServicioDireccion servicioDireccion = (ServicioDireccion) contenedor.getBean("servicioDireccion");

        String calle = (String) request.getParameter("calleH");
        String numero = (String) request.getParameter("numeroH");
        String piso = (String) request.getParameter("pisoH");
        String puerta = (String) request.getParameter("puertaH");
        String poblacion = (String) request.getParameter("poblacionH");
        String codigoPostal = (String) request.getParameter("codigoPostalH");
        String provincia = (String) request.getParameter("provinciaH");
        String pais = (String) request.getParameter("paisH");
        String plaza = (String) request.getParameter("plazaH");
        String numPlaza = null;

        if (plaza.contains("vivienda") || plaza.contains("local") || plaza.contains("oficina") || plaza.contains("nave")) {
            numPlaza = "No";
        } else {
            String[] plazaGaraje = plaza.split("garaje plaza: ");
            numPlaza = plazaGaraje[1].trim();
        }

        int idDireccion = servicioDireccion.getId(calle, numero, piso, puerta, codigoPostal, poblacion, provincia, pais, numPlaza);

        Direccion direccion = servicioDireccion.getDireccion(idDireccion);

        servicioDireccion.eliminaDireccion(direccion);

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
