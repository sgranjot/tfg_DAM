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
import modelos.Gasto;
import modelos.Usuario;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import servicios.implementacion.ServicioGasto;

/**
 *
 * @author sgranjot
 */
public class ServletEliminarGasto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuarioSession");

        ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
        ServicioGasto servicioGasto = (ServicioGasto) contenedor.getBean("servicioGasto");

        int idGasto = Integer.parseInt(request.getParameter("gastoId"));
        request.setAttribute("idGasto", idGasto);

        Gasto gasto = servicioGasto.getGasto(idGasto);
        servicioGasto.eliminaGasto(gasto);

        RequestDispatcher rd = request.getRequestDispatcher("servletGastos");
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
